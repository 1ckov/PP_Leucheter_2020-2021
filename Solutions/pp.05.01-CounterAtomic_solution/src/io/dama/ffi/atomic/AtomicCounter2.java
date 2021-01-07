package io.dama.ffi.atomic;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicCounter2 implements ICounter {
    private final AtomicInteger c;

    public AtomicCounter2(final int init) {
        this.c = new AtomicInteger(init);
    }

    @Override
    public void increment() {
        this.c.incrementAndGet();
    }

    @Override
    public void decrement() {
        this.c.decrementAndGet();
    }

    @Override
    public int value() {
        return this.c.get();
    }
}
