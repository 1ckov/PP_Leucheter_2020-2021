package io.dama.ffi.cfapi.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LibAsync extends io.dama.ffi.cfapi.Lib implements ILibAsync {
    static ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()-1);
    
    @Override
    public Future<Integer> calcAsync() throws Exception {
        return pool.submit(() -> calcSync());
    }
    
}
