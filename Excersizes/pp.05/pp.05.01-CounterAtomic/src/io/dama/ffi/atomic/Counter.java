package io.dama.ffi.atomic;

class Counter implements ICounter {
    private int c;

    public Counter(final int init) {
        this.c = init;
    }

    @Override
    public void increment() {
        this.c++;
    }

    @Override
    public  void decrement() {
        this.c--;
    }

    @Override
    public int value() {
        return this.c;
    }

}
