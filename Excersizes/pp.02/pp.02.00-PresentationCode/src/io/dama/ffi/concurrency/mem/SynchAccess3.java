package io.dama.ffi.concurrency.mem;

public class SynchAccess3 {
    private int counter = 0;
    private final Object lock = new Object();

    public void doubler() {
        synchronized (this.lock) {
            this.counter = this.counter * 2;
            System.out.printf("%s: counter==%d\n", Thread.currentThread().getName(), this.counter);
        }
    }

    public synchronized void decreaser() {
        synchronized (this.lock) {
            this.counter = this.counter - 2;
            System.out.printf("%s: counter==%d\n", Thread.currentThread().getName(), this.counter);
        }
    }

    public static void main(final String[] args) {
        final var counter = new SynchAccess3();
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
