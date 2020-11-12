package io.dama.ffi.concurrency.mem;

public class SynchAccess2 {
    private int counter = 0;

    public synchronized void doubler() {
        this.counter = this.counter * 2;
        System.out.printf("%s: counter==%d\n", Thread.currentThread().getName(), this.counter);
    }

    public synchronized void decreaser() {
        this.counter = this.counter - 2;
        System.out.printf("%s: counter==%d\n", Thread.currentThread().getName(), this.counter);
    }

    public static void main(final String[] args) {
        final var counter = new SynchAccess2();
        (new Thread(() -> {
            while (true) {
                counter.doubler();
            }
        }, "Doubler")).start();
        (new Thread(() -> {
            while (true) {
                counter.decreaser();
            }
        }, "Decreaser")).start();
    }
}
