package io.dama.ffi.parcoord.synch;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PhilosopherExperiment {
    static final int MAX_THINKING_DURATION_MS = 1000;
    static final int MAX_EATING_DURATION_MS = 3000;
    static final int MAX_TAKING_TIME_MS = 1000;
    static final int PHILOSOPHER_NUM = 10;
    static final int EXP_DURATION_MS = 100000;

    public static void main(final String[] args) throws InterruptedException {
        Lock table = new ReentrantLock();
        Condition isEating = table.newCondition();
        Condition isThinking = table.newCondition();
        final var chopsticks = new Chopstick[PhilosopherExperiment.PHILOSOPHER_NUM];
        final var philosophers = new Philosopher[PhilosopherExperiment.PHILOSOPHER_NUM];
        for (var i = 0; i < PhilosopherExperiment.PHILOSOPHER_NUM; i++) {
            chopsticks[i] = new Chopstick();
        }
        for (var i = 0; i < PhilosopherExperiment.PHILOSOPHER_NUM; i++) {
            philosophers[i] = new Philosopher(i, chopsticks[i],
                    chopsticks[(i + 1) % PhilosopherExperiment.PHILOSOPHER_NUM], table, isEating, isThinking);
        }
        for (var i = 0; i < PhilosopherExperiment.PHILOSOPHER_NUM; i++) {
            philosophers[i].start();
        }
        Thread.sleep(PhilosopherExperiment.EXP_DURATION_MS);
        for (var i = 0; i < PhilosopherExperiment.PHILOSOPHER_NUM; i++) {
            philosophers[i].stopPhilosopher();
        }
    }
}
