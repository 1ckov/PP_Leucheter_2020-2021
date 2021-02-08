package io.dama.ffi.threads.run.synch;

import java.util.Random;

import io.dama.ffi.threads.model.ITask;

public class Runner {
    static final int MONITORS = 10;

    public static void test(final ITask[] tasks) {
        final var r = new Random();
        for (final var t : tasks) {
            for (var monitors = 0; monitors < Runner.MONITORS; monitors++) {
                (new Observer(t)).start();
            }
            final Runnable changer = () -> {
                while (true) {
                    t.setNicelevel(r.nextInt(100));
                    Thread.yield();
                }
            };
            (new Thread(changer)).start();
        }
    }
}
