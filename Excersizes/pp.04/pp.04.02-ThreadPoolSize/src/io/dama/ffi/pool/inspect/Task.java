package io.dama.ffi.pool.inspect;

import io.dama.ffi.pool.run.cached.Runner;

public class Task implements Runnable {

    private static final int NUMBER_OF_TASKS = 1;

    @Override
    public void run() {
        // TODO Auto-generated method stub
    }

    public static void main(final String[] args) {
        final var pool = Runner.test(new Task(), Task.NUMBER_OF_TASKS);
    }

}
