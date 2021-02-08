package io.dama.ffi.future;

public class Main {

    public static void main(final String... args) throws InterruptedException {
        RunnableWithResult<Integer> r1 = new RunnableWithResult2<>(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1 + 2;
        });
        RunnableWithResult<Integer> r2 = new RunnableWithResult2<>(() -> 3 + 4);
        RunnableWithResult<Integer> r3 = new RunnableWithResult2<>(() -> {
            while (!r1.isAvailable() || !r2.isAvailable()) {
                System.out.println("waiting on r1 or r2");
            }
            return r1.get() + r2.get();
        });
        var thread1 = new Thread(r1);
        var thread2 = new Thread(r2);
        var thread3 = new Thread(r3);

        thread1.start();
        thread2.start();
        thread3.start();

        while (!r3.isAvailable()) {
            System.out.println("waiting on r3");
        }
        System.out.println("result: " + r3.get());
    }

}
