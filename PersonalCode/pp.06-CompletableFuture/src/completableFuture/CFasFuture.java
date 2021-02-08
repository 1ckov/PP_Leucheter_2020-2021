package completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CFasFuture {

    static public Future<String> calculateAsync() {
        CompletableFuture<String> cf = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {

            Thread.sleep(500);
            // In case we know the result of our Future we can 
            // just set as Completed via the complete(<result>) method
            // where we just hand over the result of the future as a argument
            
            cf.complete("Hello");
            return null;

        });

        return cf;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // We can hold our CompletableFuture Object as 
        // a Future and just as well call get() on it
        Future<String> cf = calculateAsync();
        System.out.println(cf.get());

        // We can use the static method completedFuture(<result>) 
        // to achive the same results, later when we call get() it will never block
        cf = CompletableFuture.completedFuture("Hello");
        System.out.println(cf.get());
        // We exit after all has been done
        System.exit(0);
    }
}
