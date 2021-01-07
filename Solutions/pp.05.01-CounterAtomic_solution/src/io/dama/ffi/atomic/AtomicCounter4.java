package io.dama.ffi.atomic;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicCounter4 implements ICounter {
    private final AtomicInteger c;

    public AtomicCounter4(final int init) {
        this.c = new AtomicInteger(init);
    }

    @Override
    public void increment() {
        this.c.updateAndGet(i -> i + 1);
    }

    @Override
    public void decrement() {
        this.c.updateAndGet(i -> i - 1);
    }

    @Override
    public int value() {
        return this.c.get();
    }
}
