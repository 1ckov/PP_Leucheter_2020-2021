
package io.dama.ffi.atomic;

import java.util.concurrent.atomic.AtomicInteger;

class CounterAtomic2 implements ICounter {
    private AtomicInteger c;

    public CounterAtomic2(final int init) {
        this.c = new AtomicInteger(init);
    }

    @Override
    public void increment() {
        c.getAndIncrement();
    }

    @Override
    public  void decrement() {
        c.getAndDecrement();
    }

    @Override
    public int value() {
        return this.c.get();
    }

}
