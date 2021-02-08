package io.dama.ffi.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterAtomic implements ICounter{
    private AtomicInteger c;

    public CounterAtomic(final int init) {
        this.c = new AtomicInteger(init);
    }

    /** Compares the internal value of c pre and post the increment
     * if values != => Retry
     * else => Save changes 
     */
    @Override
    public void increment() {
        var temp = c.get();
        // Special Method of the class Atomic
        // returns false if real values before and after the Transaction differ
        // returns true if values are the ones they should be
        while(!this.c.compareAndSet(temp, temp+1)){
            temp = c.get();
        }
    }
    /** Compares the internal value of c pre and post the decrement
     * if values != => Retry
     * else => Save changes 
     */
    @Override
    public  void decrement() {
        var temp = c.get();
        while(!this.c.compareAndSet(temp, temp-1)){
            temp = c.get();
        }
    }

    @Override
    public int value() {
        return this.c.get();
    }
    
}
