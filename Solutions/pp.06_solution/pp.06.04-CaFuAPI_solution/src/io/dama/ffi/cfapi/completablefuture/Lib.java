package io.dama.ffi.cfapi.completablefuture;

import java.util.concurrent.CompletableFuture;

public class Lib extends io.dama.ffi.cfapi.Lib implements ILib {

    @Override
    public CompletableFuture<Integer> calcAsync() {
        return CompletableFuture.supplyAsync(() -> calcSync());
    }
}
