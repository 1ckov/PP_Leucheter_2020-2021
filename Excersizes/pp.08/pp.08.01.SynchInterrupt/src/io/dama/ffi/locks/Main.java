package io.dama.ffi.locks;

public class Main {
    public static void main(final String... args) throws InterruptedException {
        var w1 = new Worker();
        var w2 = new Worker();
        var t1 = new Thread(w1, "T1");
        var t2 = new Thread(w2, "T2");
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println("shutting down t2");
        w2.cancel();
        System.out.println("shutting down t1");
        w1.cancel();
        t1.join();
        t2.join();
    }
}
