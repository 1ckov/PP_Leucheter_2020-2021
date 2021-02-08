package io.dama.ffi.threads.join;

class X extends Thread {
    @Override
    public void run() {
        // ...
    }

    public static void main(final String[] args) throws InterruptedException {
        final var t = new X();
        System.out.println("Thread t wird gestartet...");
        t.start();
        System.out.println("Thread t wurde gestartet.");
        // ab hier gibt es zwei nebenläufige Threads (den main-Thread und t, in dem ...
        // ausgeführt wird)
        // im main-Thread kann man nun warten (der main-Thread wird blockiert), bis t
        // (also ...) fertig ist
        t.join();
        System.out.println("Thread t ist fertig.");
    }
}