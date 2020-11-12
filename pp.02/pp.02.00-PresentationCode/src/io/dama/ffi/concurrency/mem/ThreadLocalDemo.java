package io.dama.ffi.concurrency.mem;

import java.util.Random;

public class ThreadLocalDemo {
    public static class Runner implements Runnable {
        public static ThreadLocal<Integer> mem = new ThreadLocal<>() {
            @Override
            protected Integer initialValue() {
                return Integer.valueOf(1);
            }
        };

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep((new Random()).nextInt(2000));
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
                Runner.mem.set(Runner.mem.get() + 1);
                System.out.println(Thread.currentThread().getName() + ": " + Runner.mem.get());
            }
        }
    }

    public static void main(final String[] args) {
        final var runnable = new Runner();
        new Thread(runnable, "Runner-1").start();
        new Thread(runnable, "Runner-2").start();
    }
}
