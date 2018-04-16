package org.dora;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

public class Test {

    public static void t() {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.completedFuture("A : I will take some sugar").thenAcceptAsync(fn -> {
            System.out.println("B : I will take some water");
        });
        voidCompletableFuture.exceptionally()
        voidCompletableFuture.handle()
    }
}
