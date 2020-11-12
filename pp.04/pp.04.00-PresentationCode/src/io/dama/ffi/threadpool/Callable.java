package io.dama.ffi.threadpool;

public interface Callable<V> {
    public V call() throws Exception;
}
