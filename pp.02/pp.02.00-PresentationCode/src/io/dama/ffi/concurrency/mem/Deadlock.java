package io.dama.ffi.concurrency.mem;

public class Deadlock {
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void m1() {
        synchronized (lock1) {
            synchronized (lock2) {
                System.out.println("m1");
            }
        }
    }

    public static void m2() {
        synchronized (lock2) {
            synchronized (lock1) {
                System.out.println("m2");
            }
        }
    }

    public static void main(final String[] args) {
        (new Thread(() -> Deadlock.m1())).start();
        (new Thread(() -> Deadlock.m2())).start();
    }
}
