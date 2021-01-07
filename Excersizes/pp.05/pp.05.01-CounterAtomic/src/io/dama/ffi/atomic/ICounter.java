package io.dama.ffi.atomic;

interface ICounter {

    void increment();

    void decrement();

    int value();

}