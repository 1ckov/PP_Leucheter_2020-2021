package io.dama.ffi.concurrency.mem.jmm;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MyThreadLocalRandom4 implements Runnable {

    private static final int ROUNDS = 20;
    private static final int THREADS = 10;
    public Random rand = ThreadLocalRandom.current();

    @Override
    public void run() {
        var strBuf = new StringBuffer();
        strBuf.append(Thread.currentThread().getName() + ": ");
        for (var j = 0; j < MyThreadLocalRandom4.ROUNDS; j++) {
            synchronized (MyThreadLocalRandom4.class) {
                // Achtung: ThreadLocalRandom.current() wurde bei der Initialisierung abgerufen.
                // Alles Threads (werden erst später erzeugt) teilen sich denselben
                // ThreadLocalRandom-Zufallszahlengenerator. Dieser ist aber nicht threadsafe!
                strBuf.append(String.format("%2d ", this.rand.nextInt(100)));
            }
        }
        System.out.println(strBuf);
    }

    public static void main(final String... args) throws InterruptedException {
        var runner = new MyThreadLocalRandom4();
        var thread = new Thread[MyThreadLocalRandom4.THREADS];
        var now = System.currentTimeMillis();
        for (var i = 0; i < MyThreadLocalRandom4.THREADS; i++) {
            thread[i] = new Thread(runner, String.format("Runner-%02d", i));
            thread[i].start();
        }
        for (var i = 0; i < MyThreadLocalRandom4.THREADS; i++) {
            thread[i].join();
        }
        System.out.printf("Zeitdauer: %d ms\n", System.currentTimeMillis() - now);
    }
}
