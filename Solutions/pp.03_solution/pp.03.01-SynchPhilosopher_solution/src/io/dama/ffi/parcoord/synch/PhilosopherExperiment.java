package io.dama.ffi.parcoord.synch;

public class PhilosopherExperiment {
    static final int MAX_THINKING_DURATION_MS = 3000;
    static final int MAX_EATING_DURATION_MS = 3000;
    static final int MAX_TAKING_TIME_MS = 100;
    static final int PHILOSOPHER_NUM = 5;
    static final int EXP_DURATION_MS = 20000;

    public static void main(final String... args) throws InterruptedException {
        var chopsticks = new Chopstick[PhilosopherExperiment.PHILOSOPHER_NUM];
        var philosophers = new Philosopher[PhilosopherExperiment.PHILOSOPHER_NUM];
        for (int i = 0; i < PhilosopherExperiment.PHILOSOPHER_NUM; i++) {
            chopsticks[i] = new Chopstick();
        }
        // first n-1 philosophers
        for (var i = 0; i < (PhilosopherExperiment.PHILOSOPHER_NUM - 1); i++) {
            philosophers[i] = new Philosopher(i, chopsticks[i],
                    chopsticks[(i + 1) % PhilosopherExperiment.PHILOSOPHER_NUM]);
        }
        // nth philosophers: initialize with right+left instead of left+right
        philosophers[PhilosopherExperiment.PHILOSOPHER_NUM - 1] = new Philosopher(
                PhilosopherExperiment.PHILOSOPHER_NUM - 1, chopsticks[0],
                chopsticks[PhilosopherExperiment.PHILOSOPHER_NUM - 1]);
        for (var i = 0; i < PhilosopherExperiment.PHILOSOPHER_NUM; i++) {
            philosophers[i].start();
        }
        Thread.sleep(PhilosopherExperiment.EXP_DURATION_MS);
        for (var i = 0; i < PhilosopherExperiment.PHILOSOPHER_NUM; i++) {
            philosophers[i].stopPhilosopher();
        }
    }
}
