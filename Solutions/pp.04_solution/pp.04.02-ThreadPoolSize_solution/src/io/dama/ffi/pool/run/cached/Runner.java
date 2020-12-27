package io.dama.ffi.pool.run.cached;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {

    public static ExecutorService test(final Runnable task, final int tries) {
        var pool = Executors.newCachedThreadPool();
        for (var i = 1; i <= tries; i++) {
            pool.execute(task);
        }
        return pool;
    }

}
