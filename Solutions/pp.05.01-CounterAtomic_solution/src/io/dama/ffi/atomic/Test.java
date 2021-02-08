package io.dama.ffi.atomic;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Test {
    private final static int RUNS = 50000;

    private static void testIncrement(final ICounter counter) {
        assertEquals(0, counter.value());
        var t1 = new Thread(() -> {
            for (var j = 0; j < RUNS; j++) {
                counter.increment();
            }
        });
        var t2 = new Thread(() -> {
            for (var j = 0; j < RUNS; j++) {
                counter.increment();
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
        }
        assertEquals(RUNS * 2, counter.value());
    }

    @org.junit.jupiter.api.Test
    void testCounterIncrement() throws InterruptedException {
        testIncrement(new Counter(0));
    }

    @org.junit.jupiter.api.Test
    void testSynchronizedCounterIncrement() throws InterruptedException {
        testIncrement(new SynchronizedCounter(0));
    }

    @org.junit.jupiter.api.Test
    void testAtomicCounter1Increment() throws InterruptedException {
        testIncrement(new AtomicCounter1(0));
    }

    @org.junit.jupiter.api.Test
    void testAtomicCounter2Increment() throws InterruptedException {
        testIncrement(new AtomicCounter2(0));
    }

    @org.junit.jupiter.api.Test
    void testAtomicCounter3Increment() throws InterruptedException {
        testIncrement(new AtomicCounter3(0));
    }

    @org.junit.jupiter.api.Test
    void testAtomicCounter4Increment() throws InterruptedException {
        testIncrement(new AtomicCounter4(0));
    }

    private static void testDecrement(final ICounter counter) {
        assertEquals(RUNS * 2, counter.value());
        var t1 = new Thread(() -> {
            for (var j = 0; j < RUNS; j++) {
                counter.decrement();
            }
        });
        var t2 = new Thread(() -> {
            for (var j = 0; j < RUNS; j++) {
                counter.decrement();
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
        }
        assertEquals(0, counter.value());
    }

    @org.junit.jupiter.api.Test
    void testCounterDecrement() throws InterruptedException {
        testDecrement(new Counter(RUNS * 2));
    }

    @org.junit.jupiter.api.Test
    void testSynchronizedCounterDecrement() throws InterruptedException {
        testDecrement(new SynchronizedCounter(RUNS * 2));
    }

    @org.junit.jupiter.api.Test
    void testAtomicCounter1Decrement() throws InterruptedException {
        testDecrement(new AtomicCounter1(RUNS * 2));
    }

    @org.junit.jupiter.api.Test
    void testAtomicCounter2Decrement() throws InterruptedException {
        testDecrement(new AtomicCounter2(RUNS * 2));
    }

    @org.junit.jupiter.api.Test
    void testAtomicCounter3Decrement() throws InterruptedException {
        testDecrement(new AtomicCounter3(RUNS * 2));
    }

    @org.junit.jupiter.api.Test
    void testAtomicCounter4Decrement() throws InterruptedException {
        testDecrement(new AtomicCounter4(RUNS * 2));
    }

}
