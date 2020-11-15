package io.dama.ffi.parcoord.dining.cond;

import java.util.concurrent.locks.Lock;
import java.util.Random;
import java.util.concurrent.locks.Condition;

public class Philosopher extends Thread implements IPhilosopher {

    Philosopher left;
    Philosopher right;
    Lock table;
    int seat;
    boolean eating = false;
    volatile boolean stopped = false;
    Condition finishedEating;
    Random rng = new Random();

    private void waitForFinishedEating() {
        table.lock();
        try {
            if (eating) finishedEating.await();
        } catch (InterruptedException e) { this.interrupt(); }
        table.unlock();
    }

    private void eat() {
        try {
            System.out.println("Philosoper " + seat + " is now eating");
            Thread.sleep(rng.nextInt(PhilosopherExperiment.MAX_EATING_DURATION_MS));
        } catch (InterruptedException e) { this.interrupt(); }

        table.lock();
        this.eating = false;
        finishedEating.signalAll();
        table.unlock();
    }

    private void think() {
        try {
            System.out.println("Philosoper " + seat + " is now thinking");
            Thread.sleep(rng.nextInt(PhilosopherExperiment.MAX_THINKING_DURATION_MS));
        } catch (InterruptedException e) { this.interrupt(); }
    }

    @Override
    public void run() {
        while (!stopped) {
            table.lock();
            if (left.eating || right.eating) {
                table.unlock();
                left.waitForFinishedEating();
                right.waitForFinishedEating();
            } else {
                this.eating = true;
                table.unlock();
                eat();
                think();
            }
        }
    }

    @Override
    public void setLeft(final IPhilosopher left) {
        this.left = (Philosopher)left;
    }

    @Override
    public void setRight(final IPhilosopher right) {
        this.right = (Philosopher)right;
    }

    @Override
    public void setSeat(final int seat) {
        this.seat = seat;
    }

    @Override
    public void setTable(final Lock table) {
        this.table = table;
        finishedEating = table.newCondition();
    }

    @Override
    public void stopPhilosopher() {
        this.stopped = true;
        this.interrupt();
    }
}
