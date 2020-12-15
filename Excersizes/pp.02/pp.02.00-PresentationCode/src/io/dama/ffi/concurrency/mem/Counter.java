package io.dama.ffi.concurrency.mem;

public class Counter {
    public int counter = 0;

    public static void main(final String[] args) {
        while (true) {
            final var c = new Counter();

            new Thread(() -> {
                var a = c.counter;
                a++;
                c.counter = a;
            }).start();

            new Thread(() -> {
                var b = c.counter;
                b += 2;
                c.counter = b;
            }).start();

            System.out.println("counter: " + c.counter);
        }
    }
}
