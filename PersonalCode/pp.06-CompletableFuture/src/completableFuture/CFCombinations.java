package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CFCombinations {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Sequentually

        // * thenCompose

        // First we Supply the "Hello" String
        CompletableFuture<Void> completableFutureCompose = CompletableFuture.supplyAsync(() -> "Hello ")
                // Here we take the hello string as input and make a new string by concatinating
                // it
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "World!"))
                // Finally we print our new String
                .thenAccept(s -> System.out.println(s));

        // Parallelly

        // Here we supply the "Hello again" String
        CompletableFuture<Void> completableFutureCombine = CompletableFuture.supplyAsync(() -> "Hello again")
                // And at the same time supply a " World!" string and combine the two,
                // using the Functional Interface "BiFunction" which takes 2 inputs and returns
                // a result,
                // and get a new String.
                .thenCombine(CompletableFuture.supplyAsync(() -> " World!"), (s1, s2) -> s1 + s2)
                // which we then pass to a Consumer Function to print the value out
                .thenAccept(s -> System.out.println(s));

        // thenApply()
        // We start off with an Integer
        CompletableFuture<String> cfApplyer = CompletableFuture.supplyAsync(() -> 20 + 5)
                // But then we change the type to String
                .thenApply(i -> {
                    String result = "Hello world! ";
                    for (int j = 0; j < i; j++) {
                        result += "and again ";
                    }
                    return result;
                });
        System.out.println(cfApplyer.get());



    }
}
