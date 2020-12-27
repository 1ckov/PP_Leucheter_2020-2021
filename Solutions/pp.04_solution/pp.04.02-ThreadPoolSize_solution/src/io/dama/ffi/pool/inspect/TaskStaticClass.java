package io.dama.ffi.pool.inspect;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskStaticClass implements Runnable {
    static class Shutdowner implements Runnable {

        @Override
        public void run() {
            TaskStaticClass.pool1.shutdown();
            TaskStaticClass.pool2.shutdown();
            TaskStaticClass.scheduler.shutdown();
        }

    }

    private static final int NUMBER_OF_TASKS = 20;
    private static ExecutorService pool1;
    private static ExecutorService pool2;
    private static ScheduledExecutorService scheduler;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(final String... args) {
        TaskStaticClass.pool1 = io.dama.ffi.pool.run.fixed.Runner.test( //
                new TaskStaticClass(), //
                TaskStaticClass.NUMBER_OF_TASKS);
        TaskStaticClass.pool2 = io.dama.ffi.pool.run.cached.Runner.test( //
                new TaskStaticClass(), //
                TaskStaticClass.NUMBER_OF_TASKS);
        TaskStaticClass.scheduler = Executors.newSingleThreadScheduledExecutor();
        TaskStaticClass.scheduler.schedule(new Shutdowner(), 5, TimeUnit.SECONDS);
    }

}
