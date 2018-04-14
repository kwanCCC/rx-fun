package org.dora.scoffld;

public abstract class MockBlocking {

    public static void block(long nanoSecond) {
        long l = System.nanoTime();
        do {

        } while (System.nanoTime() - l > nanoSecond);
    }
}
