package io.dama.ffi.cfapi.completablefuture;

import java.util.concurrent.CompletableFuture;

public class User {
    public static void main(final String... args) throws Exception {
        ILib lib = new Lib();
        CompletableFuture.allOf(lib.calcAsync().thenAccept((integer) -> System.out.printf("%2d: %d\n", 1, integer)),
                lib.calcAsync().thenAccept((integer) -> System.out.printf("%2d: %d\n", 2, integer)),
                lib.calcAsync().thenAccept((integer) -> System.out.printf("%2d: %d\n", 3, integer)),
                lib.calcAsync().thenAccept((integer) -> System.out.printf("%2d: %d\n", 4, integer)),
                lib.calcAsync().thenAccept((integer) -> System.out.printf("%2d: %d\n", 5, integer)),
                lib.calcAsync().thenAccept((integer) -> System.out.printf("%2d: %d\n", 6, integer)),
                lib.calcAsync().thenAccept((integer) -> System.out.printf("%2d: %d\n", 7, integer)),
                lib.calcAsync().thenAccept((integer) -> System.out.printf("%2d: %d\n", 8, integer)),
                lib.calcAsync().thenAccept((integer) -> System.out.printf("%2d: %d\n", 9, integer)),
                lib.calcAsync().thenAccept((integer) -> System.out.printf("%2d: %d\n", 10, integer))).join();
    }
}
