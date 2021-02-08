package io.dama.ffi.locks;

public abstract class Experiment {
    final static int WRITES = 1; // 50
    final static int READS = 9999; // 50
    final static int TRIALS = 100000000 / (WRITES + READS);

    protected int counter;

    public void experimentSingle() {
        for (var i = 0; i < TRIALS; i++) {
            for (var j = 0; j < WRITES; j++) {
                incCounter();
            }
            for (var j = 0; j < READS; j++) {
                getCounter();
            }
        }
    }

    public void experimentPar() throws InterruptedException {
        var t1 = new Thread(() -> experimentSingle());
        var t2 = new Thread(() -> experimentSingle());
        var now = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.printf("Lauf %s, Zeitdauer: %dms",
                (getCounter() - (2 * TRIALS * WRITES)) == 0 ? "korrekt" : "fehlerhaft",
                System.currentTimeMillis() - now);
    }

    public abstract void incCounter();

    public abstract int getCounter();

}
