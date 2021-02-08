package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CFStages {
    // CF = Completable Future
    // CS = Completion Stage

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Here we can see the typical chain of Completion stages:
        
        // Stage 1: We supply the values
        // We supply a value and we get a "Completion Stage".
        CompletableFuture<String> futureSuplier = CompletableFuture.supplyAsync(() -> "Hello");

        // Stage 2: We do some work on the values
        // Then we call the thenApply() method with a 
        // Functional Interface "Function" (which takes one input and gives us one output),
        // on our "CS" to get a new CF Object.
        CompletableFuture<String> futureFunction = futureSuplier.thenApply(s -> s + " World!");
        
        // Stage 3: We Evaluate the values
        // Finally we can use the thenAccept() method and pass it a
        // Functional Interface "Consumer" which takes one argument and returns no result;
        CompletableFuture<Void> futureConsumer = futureFunction.thenAccept(s -> System.out.println(s));


    }
    
}
