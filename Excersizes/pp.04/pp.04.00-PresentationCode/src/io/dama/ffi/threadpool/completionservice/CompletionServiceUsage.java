package io.dama.ffi.threadpool.completionservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

public class CompletionServiceUsage {

    public static void main(final String[] args) {
        final var pool = Executors.newCachedThreadPool();
        final List<Callable<String>> tasks = new ArrayList<>();
        tasks.add(() -> {
            Thread.sleep(3000);
            return "calc c1";
        });
        tasks.add(() -> {
            Thread.sleep(2000);
            return "calc c2";
        });
        tasks.add(() -> {
            Thread.sleep(1000);
            return "calc c3";
        });
        final CompletionService<String> completionService = new ExecutorCompletionService<>(pool);
        for (final var callableTask : tasks) {
            completionService.submit(callableTask);
        }
        try {
            for (var i = 0; i < tasks.size(); i++) {
                final var future = completionService.take();
                System.out.printf("Result %2d: %s\n", i, future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }
}
