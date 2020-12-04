package io.dama.ffi.threads.end;

public class Runner {
    public static void main(final String[] args) {
        final var task = new Task();
        final var thread = new Thread(task);
        //We set the exception Handler in the java Thread class
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.err.println("Unhandled Exception: " + e.getMessage());
            System.err.println(" Thread: " + t.getId() + " - " + t.getName());
            System.err.println(" Thread State: " + t.getState());
            e.printStackTrace(System.err);
        });
        
        thread.start();
        (new Thread(() -> task.stopRequest())).start();

    }
}
