package io.dama.ffi.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(final String[] args) {
        // 1. We call the static factory method to create the pool
        final var executor = Executors.newCachedThreadPool();

        // 2. We Create and pass our Callable or runnable Objects directly to the
        // executor Pool, and we save the Future Object that the method returns

        // ! Note That calling submits gives us back a Future Object which,
        // can be regarded as a promise that we are gonna get results in the end

        // 2.1 Callable that will simulate a long calculation
        // lambda style
        Future<Integer> expectedFuture1 = executor.submit(() -> {
            try {

                // We simulate a long Calculation
                Thread.sleep(10000);
            } catch (InterruptedException e) {

                System.err.println("Thread has been Interrupted.");
                System.err.println("Thread has been Interrupted.");
                Thread.currentThread().interrupt();
            }

            // Finally finishe the difficult calculation
            return 3 + 4;
        });

        // 2.2 Callable that will simulate a long calculation
        // annonyous Inner class style
        Future<Integer> expectedFuture2 = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                // We simulate a long Calculation
                try {

                    Thread.sleep(15000);
                } catch (InterruptedException e) {

                    System.err.println("Thread has been Interrupted.");
                    Thread.currentThread().interrupt();
                }

                // Finally finishe the difficult calculation
                return 1 + 2;
            }
        });

        // 2.3 Callable that will return the result of the two calculations
        Future<Integer> expectedFuture3 = executor.submit(() -> {
            return expectedFuture1.get() + expectedFuture2.get();
        });

        // 3. Finally we await the result of the calculation

        // !Note this is bad Style
        // Usuallly one would just call the get() Method
        // Although this causes the Thread calling it to block until the resul

        int result = 0;

        // While result is unchanged
        while (result == 0) {

            // Check if Calculation number 3 is Ready
            try {

                // If the calculation is ready change result
                if (expectedFuture3.isDone()) {

                    result = expectedFuture3.get();
                    System.out.println(result);
                }

                // Otherwise Sleep
                else {

                    // Verbose
                    System.out.println("Waiting on Results");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {

                System.err.println("Thread has been Interrupted.");
            } catch (ExecutionException e) {

                System.err.println("There has been an Execution Error");
            } finally {

                // Calling this will cause a shutdown after
                // all the currently working Threads have finished
                executor.shutdown();
            }
        }
    }

}
