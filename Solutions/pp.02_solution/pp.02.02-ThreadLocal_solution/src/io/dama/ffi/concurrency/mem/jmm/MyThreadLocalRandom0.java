package io.dama.ffi.concurrency.mem.jmm;

import java.util.Random;

public class MyThreadLocalRandom0 implements Runnable {
    public static long now = System.currentTimeMillis();
    public Random rand = new Random(MyThreadLocalRandom0.now);

    @Override
    public void run() {
        var strBuf = new StringBuffer();
        strBuf.append(Thread.currentThread().getName() + ": ");
        for (var j = 0; j < 20; j++) {
            strBuf.append(String.format("%2d ", this.rand.nextInt(100)));
        }
        System.out.println(strBuf);
    }

    public static void main(final String... args) {
        var runner = new MyThreadLocalRandom0();
        for (var i = 0; i < 10; i++) {
            new Thread(runner, String.format("Runner-%02d", i)).start();
        }
    }
}
