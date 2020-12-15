package io.dama.ffi.concurrency.mem.synch;

public class MemoryBarrierTest1 extends Thread {

    // Markierung von stopped als volatile => Jede Änderung wirkt als
    // Memory-Barrier
    public volatile boolean stopped = false;

    @Override
    public void run() {
        while (!this.stopped) {
            // work
        }
        System.out.println("MemoryBarrierTest-Thread actually stopped.");
    }

    public static void main(final String... args) throws InterruptedException {
        var t = new MemoryBarrierTest1();
        t.start();
        Thread.sleep(1000);
        t.stopped = true;
        System.out.println("Main thread set stopped on MemoryBarrierTest-Thread.");
    }

}
