package org.dora.rx;

import io.reactivex.Flowable;

import java.util.concurrent.CompletableFuture;

public class Adapters {


    public static <T> Flowable<T> completableStage(CompletableFuture<T> completedStage) {
        return null;
    }
}
