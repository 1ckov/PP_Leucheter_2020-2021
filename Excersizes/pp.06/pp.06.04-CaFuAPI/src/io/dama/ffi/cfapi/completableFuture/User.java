package io.dama.ffi.cfapi.completableFuture;

import java.util.concurrent.CompletableFuture;


public class User {
    public static void main(String[] args) throws Exception {
        LibAsync lib = new LibAsync();
        CompletableFuture.allOf(lib.calcAsync().thenAccept((i) -> System.out.printf("%2d: %d\n", 1, i)),
        lib.calcAsync().thenAccept((i) -> System.out.printf("%2d: %d\n", 2, i)),
        lib.calcAsync().thenAccept((i) -> System.out.printf("%2d: %d\n", 3, i)),
        lib.calcAsync().thenAccept((i) -> System.out.printf("%2d: %d\n", 4, i)),
        lib.calcAsync().thenAccept((i) -> System.out.printf("%2d: %d\n", 5, i)),
        lib.calcAsync().thenAccept((i) -> System.out.printf("%2d: %d\n", 6, i)),
        lib.calcAsync().thenAccept((i) -> System.out.printf("%2d: %d\n", 7, i)),
        lib.calcAsync().thenAccept((i) -> System.out.printf("%2d: %d\n", 8, i)),
        lib.calcAsync().thenAccept((i) -> System.out.printf("%2d: %d\n", 9, i)),
        lib.calcAsync().thenAccept((i) -> System.out.printf("%2d: %d\n", 10, i))
        ).join(); 
    }
    
}
