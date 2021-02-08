package io.dama.ffi.cfapi.completablefuture;

import java.util.concurrent.CompletableFuture;

public interface ILib extends io.dama.ffi.cfapi.ILib {
    public CompletableFuture<Integer> calcAsync() throws Exception;
}
