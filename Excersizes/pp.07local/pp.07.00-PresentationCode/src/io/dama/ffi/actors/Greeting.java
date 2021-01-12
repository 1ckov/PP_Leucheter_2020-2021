package io.dama.ffi.actors;

public class Greeting {
    private final String from;
    public Greeting(final String from) {
        this.from = from;
    }
    public String getGreeter() {
        return from;
    }
}

