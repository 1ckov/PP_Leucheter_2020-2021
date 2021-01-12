package io.dama.ffi.atomic;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tester {
    // Expected Test Results
    public static int EXPECTED_MAX = 50000;
    public static int EXPECTED_MIN = -50000;

    /**
     * A method to test ICounter implimentations through incrementing 
     * @param counter Type of ICounter
     * @return - An Integer Representing the final value of c.
     */
    public void increseTest(ICounter counter) {
        assertEquals(0, counter.value());

        Runnable task = () -> {
            for (int j = 0; j < 10000; j++) {
                counter.increment();
            }
            System.out.format("%s changed counter, new value is ==> %d\n", Thread.currentThread().getName(), counter.value());
        };

        int result = test(counter, task, "decreaser");
        assertEquals(EXPECTED_MAX, result);
    }

    /**
     * A method to test ICounter implimentations through decrementing 
     * @param counter Type of ICounter
     * @return - An Integer Representing the final value of c.
     */
    public void decreseTest(ICounter counter) {
        assertEquals(0, counter.value());
        Runnable task = () -> {
            for (int j = 0; j < 10000; j++) {
                counter.decrement();
            }
            System.out.format("%s changed counter, new value is ==> %d\n", Thread.currentThread().getName(), counter.value());
        };
        int result = test(counter, task, "decreaser");
        assertEquals(EXPECTED_MIN, result);
    }

    /** A test ment to apply a runnable method on a Counter of type ICounter */
    public int test(ICounter counter, Runnable task, String threadName) {
        // Storing our Counter instance locally
        ICounter c = counter;

        // Create "Thread Pool"
        Thread[] incrementers = new Thread[5];

        // Initialize, start and join Thread pool
        initializer(incrementers, task, threadName);
        starter(incrementers);
        joiner(incrementers);
        // Verbose output of c.value
        System.out.println("Final result of Increse : " + c.value());
        return c.value();

    }

    /**
     * Initialize Thread pools with runnable tasks
     * 
     * @param pool       - Array of Threads.
     * @param task       - Runnable Object representing the task all Threads have.
     * @param threadName - Thread Name Identifier
     */
    private static void initializer(Thread[] pool, Runnable task, String threadName) {
        for (int i = 0; i < pool.length; i++) {
            pool[i] = new Thread(task, threadName + i);
        }
    }

    /** Start "Thread pools" */
    private static void starter(Thread[] pool) {
        for (Thread t : pool) {
            t.start();
        }
    }

    /** Cast join on all "Thread Pools" */
    private static void joiner(Thread[] pool) {
        for (Thread t : pool) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.err.println(e);
                t.interrupt();
            }
        }
    }

    @org.junit.jupiter.api.Test
    void increseCounter() {
        increseTest(new Counter(0));
    }
    @org.junit.jupiter.api.Test
    void decreseCounter() {
        decreseTest(new Counter(0));
    }
    @org.junit.jupiter.api.Test
    void increseCounterSynchronized(){
        increseTest(new CounterSyncronized(0));
    }    
    @org.junit.jupiter.api.Test
    void decreseCounterSynchronized(){
        decreseTest(new CounterSyncronized(0));
    }   
    @org.junit.jupiter.api.Test
    void increseAtomic(){
        increseTest(new CounterAtomic(0));
    }    
    @org.junit.jupiter.api.Test
    void decreseAtomic(){
        decreseTest(new CounterAtomic(0));
    }   

}
