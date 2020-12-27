package io.dama.ffi.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(final String[] args) {
        final var executor = Executors.newCachedThreadPool();
        // We pass a Lambda to the executor as our firs Callable
        // Important Diffrence here we cannot use var,
        // because it the Type of T gets Obscured
        Future<Integer> futureSeer1 = executor.submit(() -> {
            try {
                // We simulate a long Calculation
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.err.println("Thread has been Interrupted.");
            }
            // Finally finishe the difficult calculation
            return 3 + 4;
        });
        // Here we use an annonyous Inner class so we can see the diffrence
        var caller2 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                // We simulate a long Calculation
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    System.err.println("Thread has been Interrupted.");
                }
                // Finally finishe the difficult calculation
                return 1 + 2;
            }
        };
        // And here we pass our Second Caller Object to the Executor
        var futureSeer2 = executor.submit(caller2);

        Future<Integer> futureSeer3 = executor.submit(() -> {
            // This is actualy Irrelavent Because executor is
            // Implimented in such a way that the Thread just Blocks
            // until it gets the result
            // while (!futureSeer1.isDone() || !futureSeer2.isDone()) {
            //     try {
            //         // Verbose
            //         System.out.println("Waiting on a Variable");
            //         Thread.sleep(1000);
            //     } catch (InterruptedException e) {
            //         System.err.println("Thread has been Interrupted.");
            //     }
            // }
            // Verbose
            return futureSeer1.get() + futureSeer2.get();
        });
        // We have fun here
        int result = 0;
        // Bad style, should not repeat
        // While result is unchanged
        while (result == 0) {
            // Check if Calculation number 3 is Ready
            try {
                // If the calculation is ready change result
                if (futureSeer3.isDone()) {
                    result = futureSeer3.get();
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
                executor.shutdown();
            }
        }
    }

}
