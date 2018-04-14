package org.dora.scoffld;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MockHttpServer {


    private static ExecutorService netty_boot = Executors.newFixedThreadPool(1, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            thread.setName("netty boot");
            return thread;
        }
    });

    public static void setup() {
        netty_boot.submit(() -> {
            try {
                HttpHelloWorldServer.setup();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}