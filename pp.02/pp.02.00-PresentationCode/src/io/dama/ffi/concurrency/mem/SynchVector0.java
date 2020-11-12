package io.dama.ffi.concurrency.mem;

import java.util.Vector;

public class SynchVector0 {
    private static final int MAX = 100;
    private static Vector<Integer> vec = new Vector<>();

    public static void main(final String[] args) throws InterruptedException {
        for (var i = 0; i < SynchVector0.MAX; i++) {
            SynchVector0.vec.add(i);
        }
        final var remover = new Thread(() -> {
            for (var i = 0; i < SynchVector0.vec.size(); i++) {
                if ((SynchVector0.vec.get(i) % 2) == 1) {
                    SynchVector0.vec.remove(i);
                    try {
                        Thread.sleep(10);
                    } catch (final InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Odd-Remover");
        final var adder = new Thread(() -> {
            for (var i = 0; i < SynchVector0.MAX; i++) {
                SynchVector0.vec.add(i);
                try {
                    Thread.sleep(100);
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Odd-Adder");
        remover.start();
        adder.start();
        remover.join();
        adder.join();
        for (final Integer element : SynchVector0.vec) {
            if ((element % 2) == 1) {
                System.out.print(element + " ");
            }
        }
        System.out.println();
    }
}
