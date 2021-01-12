package io.dama.ffi.cfapi.future;

import java.util.concurrent.ExecutionException;

public class User {
    static final int RUN = 10;

    public static void main(final String... args) throws InterruptedException, ExecutionException {
        ILib lib = new Lib();
        for (var i = 0; i <= User.RUN; i++) {
            System.out.printf("%2d: %d\n", i, lib.calcAsync().get());
        }
    }
}
