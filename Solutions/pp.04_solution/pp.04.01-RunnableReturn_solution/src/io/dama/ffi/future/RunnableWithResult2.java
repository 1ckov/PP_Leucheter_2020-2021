package io.dama.ffi.future;

public class RunnableWithResult2<T> extends RunnableWithResult<T> {
    private volatile Thread self;

    public RunnableWithResult2(final Expression<T> expr) {
        super(expr);
    }

    @Override
    public void run() {
        this.self = Thread.currentThread();
        this.result = this.expr.eval();
    }

    @Override
    public synchronized Boolean isAvailable() {
        return (this.self != null) && !this.self.isAlive();
    }

}
