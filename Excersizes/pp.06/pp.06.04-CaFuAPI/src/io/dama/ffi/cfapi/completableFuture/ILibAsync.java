package io.dama.ffi.cfapi.completableFuture;

import java.util.concurrent.CompletableFuture;

public interface ILibAsync {

    public CompletableFuture<Integer> calcAsync() throws Exception;

}