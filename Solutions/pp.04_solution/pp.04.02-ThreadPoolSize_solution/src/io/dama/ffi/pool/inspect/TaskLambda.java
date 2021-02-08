package io.dama.ffi.pool.inspect;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TaskLambda {

    private static final int NUMBER_OF_TASKS = 20;

    public static void main(final String... args) {
        var pool1 = io.dama.ffi.pool.run.fixed.Runner.test( //
                () -> System.out.println(Thread.currentThread().getName()), //
                TaskLambda.NUMBER_OF_TASKS);
        var pool2 = io.dama.ffi.pool.run.cached.Runner.test( //
                () -> System.out.println(Thread.currentThread().getName()), //
                TaskLambda.NUMBER_OF_TASKS);
        var scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> {
            pool1.shutdown();
            pool2.shutdown();
            scheduler.shutdown();
        }, 5, TimeUnit.SECONDS);
    }
}
