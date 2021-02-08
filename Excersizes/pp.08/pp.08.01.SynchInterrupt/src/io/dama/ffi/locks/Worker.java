package io.dama.ffi.locks;

public class Worker implements Runnable {
    private volatile boolean stop = false;
    private Thread self;

    public void cancel() {
        this.stop = true;
        this.self.interrupt();
    }

    @Override
    public void run() {
        this.self = Thread.currentThread();
        System.out.printf("%s: startup\n", this.self.getName());
        while (!this.stop) {
            try {
                synchronized (Worker.class) {
                    System.out.printf("%s: working\n", this.self.getName());
                    Thread.sleep(8000);
                }
            } catch (InterruptedException e) {
                System.err.printf("%s: interrupted\n", this.self.getName());
            }
        }
        System.out.printf("%s: shutdown\n", this.self.getName());
    }
}
