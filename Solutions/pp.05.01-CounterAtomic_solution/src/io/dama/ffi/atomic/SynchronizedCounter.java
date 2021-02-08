package io.dama.ffi.atomic;

class SynchronizedCounter implements ICounter {
    private int c;

    public SynchronizedCounter(final int init) {
        this.c = init;
    }

    @Override
    public synchronized void increment() {
        this.c++;
    }

    @Override
    public synchronized void decrement() {
        this.c--;
    }

    @Override
    public synchronized int value() {
        return this.c;
    }
}
