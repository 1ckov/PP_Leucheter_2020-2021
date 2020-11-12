package io.dama.ffi.parcoord.await;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer<T> {
    final Lock lock = new ReentrantLock();
    final Condition notFull = this.lock.newCondition();
    final Condition notEmpty = this.lock.newCondition();

    final Object[] items = new Object[8];
    int putptr, takeptr, count;

    public void put(final T x) throws InterruptedException {
        // solange der Speicher voll ist, warten
        this.items[this.putptr] = x;
        if (++this.putptr == this.items.length) {
            this.putptr = 0;
        }
        ++this.count;
        // nun ist etwas neues im Speicher: Alle wartenden Threads benachrichtigen
    }

    public T take() throws InterruptedException {
        // solange der Speicher leer ist, mit der Bedingungsvariablen notEmpty warten
        @SuppressWarnings("unchecked")
        final var x = (T) this.items[this.takeptr];
        if (++this.takeptr == this.items.length) {
            this.takeptr = 0;
        }
        --this.count;
        // nun ist etwas Platz im Speicher: Alle für notFull wartenden Threads
        // benachrichtigen
        return x;
    }

    public static void main(final String[] args) throws InterruptedException {
        final var mem = new BoundedBuffer<Integer>();
        final var p1 = new Thread(() -> {
            try {
                for (var j = 1; j <= 10; j++) {
                    mem.put(j);
                    System.out.println(Thread.currentThread().getName() + ": put=" + j);
                    Thread.sleep(1000);
                }
            } catch (final InterruptedException e) {
            }
        }, "Producer-1");
        final var p2 = new Thread(() -> {
            try {
                for (var j = 1; j <= 10; j++) {
                    mem.put(j);
                    System.out.println(Thread.currentThread().getName() + ": put=" + j);
                    Thread.sleep(1000);
                }
            } catch (final InterruptedException e) {
            }
        }, "Producer-2");
        final var c1 = new Thread(() -> {
            try {
                for (var j = 1; j <= 20; j++) {
                    System.out.println(Thread.currentThread().getName() + ": taken=" + mem.take());
                }
            } catch (final InterruptedException e) {
            }
        }, "Consumer-1");
        final long now = System.currentTimeMillis();
        p1.start();
        p2.start();
        c1.start();
        p1.join();
        p2.join();
        c1.join();
        System.out.println("Runtime: " + (System.currentTimeMillis() - now) + "ms");

    }
}
