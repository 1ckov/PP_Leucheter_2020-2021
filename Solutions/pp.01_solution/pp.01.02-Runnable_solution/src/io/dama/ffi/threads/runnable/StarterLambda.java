package io.dama.ffi.threads.runnable;

public class StarterLambda {
    static int WORKERS = 200;

    public static void main(final String[] args) {
        for (var i = 0; i < StarterLambda.WORKERS; i++) {
            final var t = new Thread(() -> {
                final var self = Thread.currentThread();
                while (true) {
                    System.out.println(self.getName() + ": ID => " + self.getId());
                }
            }, String.format("Worker-%03d", i));
            t.start();
        }
    }
}
