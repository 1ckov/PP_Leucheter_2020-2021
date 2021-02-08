package io.dama.ffi.concurrency.mem.synch;

import io.dama.ffi.concurrency.mem.synch.model.Type;

public class Factory4 {

    private static volatile Type instance;

    public static Type getInstance() {
        Type.prepare();
        if (Factory4.instance == null) {
            synchronized (Factory4.class) {
                if (Factory4.instance == null) {
                    Factory4.instance = new Type();
                }
            }
        }
        return Factory4.instance;
    }

    public static void main(final String... args) throws InterruptedException {
        var now = System.currentTimeMillis();
        var threads = new Thread[100];
        for (var i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                var object = Factory4.getInstance();
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
