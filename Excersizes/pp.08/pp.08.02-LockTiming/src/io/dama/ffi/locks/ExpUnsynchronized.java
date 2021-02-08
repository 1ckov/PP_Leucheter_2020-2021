package io.dama.ffi.locks;

public class ExpUnsynchronized extends Experiment {

    @Override
    public void incCounter() {
        this.counter++;
    }

    @Override
    public int getCounter() {
        return this.counter;
    }

    public static void main(final String... args) throws InterruptedException {
        (new ExpUnsynchronized()).experimentPar();
    }
}
