package io.dama.ffi.future;

import java.util.concurrent.Executors;

public class Main {

    public static void main(final String[] args) {
        final var executor = Executors.newCachedThreadPool();
        // hier programmieren
        executor.shutdown();
    }

}
