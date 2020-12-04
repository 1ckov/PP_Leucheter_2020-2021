package io.dama.ffi.threads.runnable;

public class StarterInner {
    static int WORKERS = 200;

    public static void main(final String[] args) {
        for (var i = 0; i < Starter.WORKERS; i++) {
            final var t = new Thread(new Runnable() {
                @Override
                public void run(){
                    Thread self = Thread.currentThread();
                    int j = 0;
                    while(j < 5){
                        j++;
                        System.out.println(self.getName() + ": ID => " + self.getId() + " j = " + j);
                        Thread.yield();
                    }
                } 
            }, String.format("Worker-%03d", i));
            t.start();
        }
    }
}


