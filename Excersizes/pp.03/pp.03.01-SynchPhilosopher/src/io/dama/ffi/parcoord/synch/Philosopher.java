package io.dama.ffi.parcoord.synch;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Philosopher extends Thread {
    private final Chopstick left;
    private final Chopstick right;
    private final Random random;
    private Condition isEating;
    private Condition isThinking;
    private int eaten;
    private final int seat;
    private Lock table;

    private volatile boolean stop;

    private void log(final String message) {
        synchronized (Philosopher.class) {
            for (var i = 1; i <= this.seat; i++) {
                System.out.print("                         ");
            }
            System.out.println(getId() + " " + message);
        }
    }

    public void stopPhilosopher() {
        log("stopping");
        this.stop = true;
        interrupt();
    }

    public Philosopher(final int seat, final Chopstick left, 
            final Chopstick right, Lock table, Condition isEating, Condition isThinking) {
        this.stop = false;
        this.seat = seat;
        this.left = left;
        this.right = right;
        this.random = new Random();
        this.eaten = 0;
        this.table = table;
        this.isEating = isEating;
        this.isThinking = isThinking;
    }

    @Override
    public void run() {
        log("starting");
        try {
            while (!this.stop) {
                think();
                eat();
            }
        } catch (final InterruptedException e) {
        }
        log("stopped; eaten=" + this.eaten);
    }

    private void think() throws InterruptedException {
        Thread.sleep(this.random.nextInt(PhilosopherExperiment.MAX_THINKING_DURATION_MS));
    }

    private void eat() throws InterruptedException {
        log("try taking left");
        synchronized (this.left) {
            log("left acquired");
            Thread.sleep(this.random.nextInt(PhilosopherExperiment.MAX_TAKING_TIME_MS));
            log("try taking right");
            synchronized (this.right) {
                log("right acquired");
                log("eating");
                this.eaten++;
                Thread.sleep(PhilosopherExperiment.MAX_EATING_DURATION_MS);
            }
            log("right released");
        }
        log("left released");
    }
}
