package io.dama.ffi.future;

public class RunnableWithResult<T> implements Runnable {
    protected final Expression<T> expr;
    protected T result;

    public RunnableWithResult(final Expression<T> expr) {
        this.expr = expr;
    }

    @Override
    public void run() {
        // hier programmieren
    }

    public synchronized Boolean isAvailable() {
        // hier programmieren
        return null;
    }

    public synchronized T get() {
        return this.result;
    }
}
