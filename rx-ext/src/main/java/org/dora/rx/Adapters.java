package org.dora.rx;

import io.reactivex.Flowable;
import io.reactivex.processors.ReplayProcessor;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class Adapters {


    public static <T> Flowable<T> completableStage(CompletableFuture<T> completedStage) {
        final ReplayProcessor<T> replayProcessor = ReplayProcessor.create(1);
        completedStage.whenComplete((t, throwable) -> {
            if (Objects.nonNull(throwable)) {
                replayProcessor.onError(throwable);
            } else if (Objects.isNull(t)) {
                replayProcessor.onError(new NullPointerException("completed stage return null"));
            } else {
                replayProcessor.onNext(t);
                replayProcessor.onComplete();
            }

        });
        return replayProcessor;
    }
}
