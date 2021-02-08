package io.dama.ffi.future;

@FunctionalInterface
public interface Expression<T> {
    public T eval();
}
