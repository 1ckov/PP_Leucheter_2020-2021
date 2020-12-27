package io.dama.ffi.future;

public class Main {

    public static void main(final String[] args) throws InterruptedException {
        // First Runnable Object
        RunnableWithResult<Integer> runner1 = new RunnableWithResult<>(() -> {
            try {
                // Here we simulate a long calculation
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.err.println("Sleep has been Interrupted");
            }
            // We Finally give the result back
            return (1 / 0);
        });
        // A second runnable with a short calcucation
        RunnableWithResult<Integer> runner2 = new RunnableWithResult<>(() -> (3 + 4));
        // Our third runner who waits for the ersults from 1 and 2 and only then gives
        // us the result back
        RunnableWithResult<Integer> runner3 = new RunnableWithResult<>(() -> {
            while (!runner1.isAvailable() || !runner2.isAvailable()) {
                try {
                    // Here we wait between every time we ask if the variable is ready
                    Thread.sleep(1000);
                    System.out.println("Waiting on a Variable");
                } catch (Exception e) {
                    System.err.println("Sleep has been Interrupted");
                }
            }
            // Verbose Purposes
            int result = runner1.get() + runner2.get();
            System.out.println("Result is: " + result);
            return result;
        });
        // Normally just create the Threads
        var t1 = new Thread(runner1, "runner1");
        var t2 = new Thread(runner2, "runner2");
        var t3 = new Thread(runner3, "runner3");
        // And we start Them
        t1.start();
        t2.start();
        t3.start();

    }

}
