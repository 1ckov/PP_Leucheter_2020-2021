package io.dama.ffi.parcoord.dining.cond;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Philosopher extends Thread implements IPhilosopher {

    private Philosopher left;
    private Philosopher right;
    private int seat;
    private Lock table;
    private volatile boolean stopped = false;
    private volatile boolean isEating = false;
    private Thread self;
    private Condition doneEating;
    private Random rng = new Random();

    public void waitForDoneEating(Philosopher p) {
        // We aquire the lock
        table.lock();
        try {
            // Check if the given Philosopher is done
            if (p.isEating)
                // if now we await a signal
                p.doneEating.await();
        } catch (InterruptedException e) {
            System.err.println(e);
            this.interrupt();
        }
        // We return the lock
        table.unlock();
    }

    public void eating() {
        // Verbose
        System.out.println("Phiolosopher " + seat + " is now eating");
        // Simulate teh philosophers "Eating" habits
        try {
            Thread.sleep(rng.nextInt(PhilosopherExperiment.MAX_EATING_DURATION_MS));
        } catch (InterruptedException e) {
            System.err.println(e);
            this.interrupt();
        }
        // We aquire the lock
        table.lock();
        // Change the value of isEating
        this.isEating = false;
        // And finally signall all threads awaiting the finish eating signal
        this.doneEating.signalAll();
        // We return the lock
        table.unlock();
    }

    public void thinking() {
        // Verbose
        System.out.println("Phiolosopher " + seat + " is now thinking");
        // Simulate the Philosopher "Thinking" habits
        try {
            Thread.sleep(rng.nextInt(PhilosopherExperiment.MAX_THINKING_DURATION_MS));
        } catch (InterruptedException e) {
            System.err.println(e);
            this.interrupt();
        }
    }

    @Override
    public void run() {
        // Run while the stopped flag is inacative
        while (!stopped) {
            // We aquire the lock
            table.lock();
            while (left.isEating || right.isEating) {
                // We return the lock
                table.unlock();
                // Waiting for the left Philosopher
                waitForDoneEating(left);
                // Waiting for the right Philosopher
                waitForDoneEating(right);
                // We aquire the lock
                table.lock();
            }
            this.isEating = true;
            // We return the lock
            table.unlock();
            eating();
            thinking();
        }

    }

    @Override
    public void setLeft(final IPhilosopher left) {
        this.left = (Philosopher) left;

    }

    @Override
    public void setRight(final IPhilosopher right) {
        this.right = (Philosopher) right;
    }

    @Override
    public void setSeat(final int seat) {
        this.seat = seat;
    }

    @Override
    public void setTable(final Lock table) {
        this.table = table;
        doneEating = table.newCondition();

    }

    @Override
    public void stopPhilosopher() {
        this.stopped = true;
        this.interrupt();

    }
}
