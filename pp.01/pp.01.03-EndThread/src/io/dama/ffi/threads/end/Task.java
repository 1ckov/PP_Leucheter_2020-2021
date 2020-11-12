package io.dama.ffi.threads.end;

public class Task implements Runnable {
    private volatile Thread self;
    private volatile boolean stopped = false;

    public void stopRequest() {
        this.stopped = true;
        if (this.self != null) {
            this.self.interrupt();
        }
    }

    public boolean isStopped() {
        return this.stopped;
    }

    @Override
    public void run() {
        this.self = Thread.currentThread();
        // 1. Initialisierungsphase
        var i = 10;
        while (!isStopped()) {
            // 2. Arbeitsphase
            System.out.println("i=" + i);
            try {
                Thread.sleep(1000 / i--);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 3. Aufräumphase
        System.out.println("fertig.");
    }
}
