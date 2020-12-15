package io.dama.ffi.concurrency.mem.synch.model;

final public class Type {
    private static int counter = 0;
    private int serial = 0;
    private int number = 0;

    public static void prepare() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Type() {
        synchronized (Type.class) {
            Type.counter++;
            this.serial = Type.counter;
        }
    }

    public synchronized int getNumber() {
        return this.number;
    }

    public synchronized void setNumber(final int number) {
        this.number = number;
    }

    public synchronized int getSerial() {
        return this.serial;
    }
}
