package io.dama.ffi.concurrency.mem.synch;

public class MemoryBarrierTest5 extends Thread {

    public boolean stopped = false;
    private Thread stopper;

    public void startStopper() {
        this.stopper = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.stopped = true;
            System.out.println("Stopper thread set stopped on MemoryBarrierTest-Thread.");
        });
        this.stopper.start();
    }

    @Override
    public void run() {
        startStopper();
        while (!this.stopped) {
            if (this.stopper != null) {
                try {
                    // Synchronisierung von Stopper-Thread Variablenänderungen
                    // durch Warten auf das Ende von Stopper Thread
                    this.stopper.join(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("MemoryBarrierTest-Thread actually stopped.");
    }

    public static void main(final String... args) throws InterruptedException {
        var t = new MemoryBarrierTest5();
        t.start();

    }

}
