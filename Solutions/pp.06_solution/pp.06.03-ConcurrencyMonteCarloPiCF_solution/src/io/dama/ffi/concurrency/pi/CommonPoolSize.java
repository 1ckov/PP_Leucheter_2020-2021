package io.dama.ffi.concurrency.pi;

import java.util.concurrent.ForkJoinPool;

public class CommonPoolSize {

    public static void main(final String... args) {
        System.out.println("Der commonPool hat die Größe " + ForkJoinPool.commonPool().getParallelism() + ".");
    }

}
