package io.dama.ffi.pool.inspect;


import io.dama.ffi.pool.run.fixed.*;

public class Task implements Runnable {

    private static final int NUMBER_OF_TASKS = 12;

    @Override
    public void run() {
        System.out.println("Hi my name is: " + Thread.currentThread().getName());
        try {
            System.out.println("Imma Sleep now for a sec");
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            System.err.println("There was na Interruption");
        }
    }

    public static void main(final String[] args) {
        final var pool = Runner.test(new Task(), Task.NUMBER_OF_TASKS);
        int sec = 0;
        while(!pool.isShutdown()){
            try {
                System.out.printf("%d seconds have passed\n", sec++);
                Thread.sleep(1000);
                if(sec == 25){
                    pool.shutdown();
                }
            } catch (InterruptedException e) {
            }
        }
    }

}
