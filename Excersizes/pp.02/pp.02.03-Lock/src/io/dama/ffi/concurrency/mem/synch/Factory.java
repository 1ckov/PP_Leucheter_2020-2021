package io.dama.ffi.concurrency.mem.synch;

import io.dama.ffi.concurrency.mem.synch.model.Type;

public class Factory {

    //Could Add volatile to instance for Thread safety
    private volatile static Type instance;
    private static Object lock = new Object();
    public static Type getInstance() {
        Type.prepare();
        //Highest Performance because we dont Enter 
        //the Syncronized block unless we have to
        if(Factory.instance == null){
            synchronized(lock){
                //A double check is needed incase it has been changed
                if (Factory.instance == null) {
                    Factory.instance = new Type();
                }
            }
        }
        return Factory.instance;
    }

    public static void main(final String[] args) throws InterruptedException {
        final var now = System.currentTimeMillis();
        final var threads = new Thread[100];
        for (var i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                final Type object = Factory.getInstance();
                System.out.println(Thread.currentThread().getName() + ": serial of instance = " + object.getSerial());
            }, String.format("InstanceGrabber-%02d", i));
            threads[i].start();
        }
        for (var i = 0; i < 100; i++) {
            threads[i].join();
        }
        final var time = System.currentTimeMillis() - now;
        System.out.println("Dauer: " + time + "ms");
    }

}
