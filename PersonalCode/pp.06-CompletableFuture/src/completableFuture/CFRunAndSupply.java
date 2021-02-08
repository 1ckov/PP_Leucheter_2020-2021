package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CFRunAndSupply {

    public static void main(String[] args) throws ExecutionException, InterruptedException{
        // Here we use the Functional Interface "Supplier" which 
        // takes no input and returns a result.
        CompletableFuture<String> futureSuplier = CompletableFuture.supplyAsync(() -> "Hello");
        System.out.print(futureSuplier.get());

        // Here we use the Functional Interface "Runner" which 
        // takes no input and returns no result.
        CompletableFuture<Void> futureRunner = CompletableFuture.runAsync(() -> System.out.print(" World !"));
    }

}
