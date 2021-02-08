package io.dama.ffi.future;

public class RunnableWithResult<T> implements Runnable {
    //
    // Our Expression to be evaluated
    private final Expression<T> expr;
    // The result of the calculation
    private T result = null;
    // Is The calcuation done
    private boolean available;

    public RunnableWithResult(final Expression<T> expr) {
        // Here we pass the Expression to be evaluated
        this.expr = expr;
    }

    @Override
    public void run() {
        // Mem bariers so asyncronus access doesnt break our code
        synchronized (this) {
            available = false;
        }
        // We start the evaluation
        result = expr.eval();
        // Mem bariers so asyncronus access doesnt break our code
        synchronized (this) {
            available = true;
        }
    }

    /**
     * A method which tells us if the calculation has Compleated.
     * 
     * @return - A Boolean value which represent if the operation has finished.
     */
    public synchronized Boolean isAvailable() {
        return available;
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
