package io.dama.ffi.threads.end;

public class Runner {
    public static void main(final String[] args) {
        final var task = new Task();
        final var thread = new Thread(task);
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.err.println("Unhandled Exception: " + e.getMessage());
            System.err.println(" Thread: " + t.getId() + " - " + t.getName());
            System.err.println(" Thread State: " + t.getState());
            e.printStackTrace(System.err);
        });
        thread.start();
        // (new Thread(() -> thread.interrupt())).start(); // falsch: thread läuft
        // weiter
        (new Thread(() -> task.stopRequest())).start(); // richtig: thread wird
                                                        // beendet
    }
}
