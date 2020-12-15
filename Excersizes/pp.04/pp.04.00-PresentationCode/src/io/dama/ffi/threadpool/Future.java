package io.dama.ffi.threadpool;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public interface Future<V> {
    public boolean cancel(boolean mayInterruptIfRunning);

    public V get() throws InterruptedException, ExecutionException;

    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;

    public boolean isCancelled();

    public boolean isDone();

}
