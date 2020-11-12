package io.dama.ffi.concurrency.mem;

public class SynchAccess1 {
    private int counter = 0;

    public void doubler() {
        synchronized (this) {
            this.counter = this.counter * 2;
            System.out.printf("%s: counter==%d\n", Thread.currentThread().getName(), this.counter);
        }
    }

    public void decreaser() {
        synchronized (this) {
            this.counter = this.counter - 2;
            System.out.printf("%s: counter==%d\n", Thread.currentThread().getName(), this.counter);
        }
    }

    public static void main(final String[] args) {
        final var counter = new SynchAccess1();
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
