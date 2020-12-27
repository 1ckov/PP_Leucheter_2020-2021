package io.dama.ffi.future;

public class RunnableWithResult1<T> extends RunnableWithResult<T> {
    private boolean finished;

    public RunnableWithResult1(final Expression<T> expr) {
        super(expr);
    }

    @Override
    public void run() {
        synchronized (this) { // Memorybarrier
            this.finished = false;
        }
        this.result = this.expr.eval();
        synchronized (this) {
            this.finished = true;
        } // Memorybarrier
    }

    @Override
    public synchronized Boolean isAvailable() {
        return this.finished;
    }

}
