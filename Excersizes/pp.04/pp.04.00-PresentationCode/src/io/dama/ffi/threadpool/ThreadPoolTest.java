package io.dama.ffi.threadpool;

import java.util.concurrent.Executors;

class ThreadPoolTest {

    public static void main(final String[] args) {
        final var pool = Executors.newFixedThreadPool(20);
        try {
            for (var i = 0; i <= 100; i++) {
                pool.execute(() -> System.out.println(Thread.currentThread().getName()));
            }
        } finally {
            pool.shutdown();
        }
    }
}
