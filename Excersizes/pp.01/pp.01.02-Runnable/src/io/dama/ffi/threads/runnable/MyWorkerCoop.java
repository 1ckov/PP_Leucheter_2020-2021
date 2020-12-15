package io.dama.ffi.threads.runnable;

class MyWorkerCoop implements Runnable {
    private Thread self;

    @Override
    public void run() {
        this.self = Thread.currentThread();
        int i = 0;
        while (i < 5) {
            i++;
            System.out.println(this.self.getName() + ": ID => " + this.self.getId() + " i = " + i);
            Thread.yield();
        }
    }
}
