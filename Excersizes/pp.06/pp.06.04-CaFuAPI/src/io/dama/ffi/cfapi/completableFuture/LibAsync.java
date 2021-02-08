package io.dama.ffi.cfapi.completableFuture;

import java.util.concurrent.CompletableFuture;

public class LibAsync extends io.dama.ffi.cfapi.Lib implements ILibAsync {

    @Override
    public CompletableFuture<Integer> calcAsync() throws Exception {
        return CompletableFuture.supplyAsync(() -> calcSync());
    }
    
}
