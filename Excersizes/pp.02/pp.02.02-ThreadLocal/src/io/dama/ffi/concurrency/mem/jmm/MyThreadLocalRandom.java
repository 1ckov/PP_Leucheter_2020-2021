package io.dama.ffi.concurrency.mem.jmm;

import java.util.Random;

public class MyThreadLocalRandom implements Runnable {
    public static long now = System.currentTimeMillis();
    public static ThreadLocal<Random> memran = new ThreadLocal<>(){
        //public ;
        
        @Override
        protected Random initialValue(){
            return new Random(MyThreadLocalRandom.now);
        }
    };

    @Override
    public void run() {
        final var strBuf = new StringBuffer();
        strBuf.append(Thread.currentThread().getName() + ": ");
        for (var j = 0; j < 20; j++) {
            strBuf.append(String.format("%2d ", memran.get().nextInt(100)));
        }
        System.out.println(strBuf);
    }

    public static void main(final String[] args) {
        final var runner = new MyThreadLocalRandom();
        for (var i = 0; i < 10; i++) {
            new Thread(runner, String.format("Runner-%02d", i)).start();
        }
    }
}
