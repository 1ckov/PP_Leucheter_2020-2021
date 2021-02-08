package io.dama.ffi.concurrency.mem.synch;

import io.dama.ffi.concurrency.mem.synch.model.Type;

public class Factory3 {

    private static volatile Type instance;
    private static Object lock = new Object();

    public static Type getInstance() {
        Type.prepare();
        synchronized (Factory3.lock) {
            if (Factory3.instance == null) {
                Factory3.instance = new Type();
            }
        }
        return Factory3.instance;
    }

    public static void main(final String... args) throws InterruptedException {
        var now = System.currentTimeMillis();
        var threads = new Thread[100];
        for (var i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                var object = Factory3.getInstance();
                System.out.println(Thread.currentThread().getName() + ": serial of instance = " + object.getSerial());
            }, String.format("InstanceGrabber-%02d", i));
            threads[i].start();
        }
        for (var i = 0; i < 100; i++) {
            threads[i].join();
        }
        var time = System.currentTimeMillis() - now;
        System.out.println("Dauer: " + time + "ms");
    }

}
