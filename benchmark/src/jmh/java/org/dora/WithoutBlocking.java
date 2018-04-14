package org.dora;

import io.reactivex.Observable;
import org.apache.commons.lang.time.StopWatch;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Threads(8)
public class WithoutBlocking {

    @Fork(1)
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 3, time = 1)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void withoutBlockTest() {
        StopWatch watch = new StopWatch();
        watch.start();
        Observable.range(0, 1000).map(i -> {
//            System.out.println("First:" + Thread.currentThread().getName());
            return Math.max(ThreadLocalRandom.current().nextInt(1000), i);
        }).subscribe(integer -> Math.pow(integer, integer), error -> {
        }, () -> watch.stop());
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(WithoutBlocking.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

}
