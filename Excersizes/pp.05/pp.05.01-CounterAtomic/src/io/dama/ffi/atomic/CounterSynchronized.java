package io.dama.ffi.atomic;

class CounterSyncronized implements ICounter {
    private int c;

    public CounterSyncronized(final int init) {
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