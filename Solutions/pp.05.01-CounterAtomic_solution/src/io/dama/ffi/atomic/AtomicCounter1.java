package io.dama.ffi.atomic;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicCounter1 implements ICounter {
    private final AtomicInteger c;

    public AtomicCounter1(final int init) {
        this.c = new AtomicInteger(init);
    }

    @Override
    public void increment() {
        var temp = this.c.get();
        while (!this.c.compareAndSet(temp, temp + 1)) {
            temp = this.c.get();
        }
    }

    @Override
    public void decrement() {
        var temp = this.c.get();
        while (!this.c.compareAndSet(temp, temp - 1)) {
            temp = this.c.get();
        }
    }

    @Override
    public int value() {
        return this.c.get();
    }
}
