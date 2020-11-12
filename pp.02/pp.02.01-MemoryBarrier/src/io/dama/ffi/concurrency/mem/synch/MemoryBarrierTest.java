package io.dama.ffi.concurrency.mem.synch;

public class MemoryBarrierTest extends Thread {

    public boolean stopped = false;

    @Override
    public void run() {
        while (!this.stopped) {
            // work
        }
        System.out.println("MemoryBarrierTest-Thread actually stopped.");
    }

    public static void main(final String... args) throws InterruptedException {
        var t = new MemoryBarrierTest();
        t.start();
        Thread.sleep(1000);
        t.stopped = true;
        System.out.println("Main thread set stopped on MemoryBarrierTest-Thread.");
    }

}
