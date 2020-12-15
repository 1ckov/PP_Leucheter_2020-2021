package io.dama.ffi.concurrency.mem;

public class RamTest extends Thread {
    private final int i;

    public RamTest(final int i) {
        this.i = i;
    }

    public void print(final int i) {
        final var a = i * i;
        final var b = Integer.valueOf(a);
        System.out.println(b);
    }

    @Override
    public void run() {
        print(this.i);
    }

    public static void main(final String[] args) {
        new Thread(new RamTest(2)).start();
        new Thread(new RamTest(3)).start();
    }
}
