package io.dama.ffi.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class Main {

    public static void main(final String... args) {
        var executor = Executors.newCachedThreadPool();
        // Lambda-Ausdruck, mehrere Statements, explizites return
        var f1 = executor.submit(() -> (1.0 + 2.0));
        // Callable als Inner Class
        var f2 = executor.submit(() -> 3.0 + 4.0);
        // Lambda-Ausdruck, knapp
        var f3 = executor.submit(() -> f1.get() + f2.get());
        try {
            // get() blockiert, bis etwas vorliegt (auch oben)
            System.out.println(f3.get());
        } catch (InterruptedException | ExecutionException e) {
            // Exceptions in f1 und f2 werden bis zum f3.get() verzögert
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

}
