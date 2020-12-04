package io.dama.ffi.threads.end;

public class Task implements Runnable {
    //We have our thread here so we can later stop it 
    private volatile Thread self;
    private volatile boolean stopped = false;

    public void stopRequest() {
        this.stopped = true;
        //We cant call interrupt on an uninitialized Thread
        if (this.self != null) {
            //So thats why we surround this with an if
            this.self.interrupt();
            //If we dont Interupt it, it will wait for the sleep to finish and then stop.
        }
    }

    public boolean isStopped() {
        return this.stopped;
    }

    @Override
    public void run() {
        // Here we get save our executing thread so we can later stop it.
        this.self = Thread.currentThread();
        // 1. Initialisierungsphase
        //as soon as thisgets to 0 we will have a problem
        var i = 10;
        while (!isStopped()) {
            // 2. Arbeitsphase
            System.out.println("i=" + i);
            try {
                //100+111+125
                Thread.sleep(1000 / i--);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 3. Aufräumphase
        System.out.println("fertig.");
    }
}
