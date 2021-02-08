package io.dama.ffi.future;

public class RunnableWithResult2<T> implements Runnable {
    // Our Expression to be evaluated
    private final Expression<T> expr;
    // The result of the calculation
    private T result = null;
    // Is The calcuation done
    private Thread self;

    public RunnableWithResult2(final Expression<T> expr) {
        // Here we pass the Expression to be evaluated
        this.expr = expr;
    }

    @Override
    public void run() {
        // Here we keep a poitner to our current Thread
        // so we can use it to check on the Condition of the Task
        this.self = Thread.currentThread();
        // We start the evaluation
        result = expr.eval();
    }

    /**
     * A method which tells us if the calculation has Compleated.
     * 
     * @return - A Boolean value which represent if the operation has finished.
     */
    public synchronized Boolean isAvailable() {
        return (!this.self.isAlive() && this.self != null);
    }

    /**
     * Returns the result fo the calculation
     * 
     * @return - If called without isAvailable given back "true" will return null.
     */
    public synchronized T get() {
        return this.result;
    }

}