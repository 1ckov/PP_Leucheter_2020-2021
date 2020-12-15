package io.dama.ffi.concurrency.mem.synch;

public class MemoryBarrierTest2 extends Thread {

    public boolean stopped = false;

    @Override
    public void run() {
        while (!this.stopped) {
            // jedes synchronized wirkt als Memory-Barrier
            synchronized (this) {
                //
            }
        }
        System.out.println("MemoryBarrierTest-Thread actually stopped.");
    }

    public static void main(final String... args) throws InterruptedException {
        var t = new MemoryBarrierTest2();
        t.start();
        Thread.sleep(1000);
        t.stopped = true;
        System.out.println("Main thread set stopped on MemoryBarrierTest-Thread.");
    }

}
