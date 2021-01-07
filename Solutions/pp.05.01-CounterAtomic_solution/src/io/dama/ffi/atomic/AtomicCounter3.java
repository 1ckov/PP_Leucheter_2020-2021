package io.dama.ffi.atomic;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicCounter3 implements ICounter {
    private final AtomicInteger c;

    public AtomicCounter3(final int init) {
        this.c = new AtomicInteger(init);
    }

    @Override
    public void increment() {
        for (;;) {
            var current = this.c.get();
            var next = current + 1;
            if (this.c.compareAndSet(current, next)) {
                break;
            }
        }
    }

    @Override
    public void decrement() {
        for (;;) {
            var current = this.c.get();
            var next = current - 1;
            if (this.c.compareAndSet(current, next)) {
                break;
            }
        }
    }

    @Override
    public int value() {
        return this.c.get();
    }
}
