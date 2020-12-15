package io.dama.ffi.threads.join;

class Y extends Thread {
    @Override
    public void run() {
        // ...
    }

    public static void main(final String[] args) throws InterruptedException {
        final var t = new Y();
        System.out.println("Thread t wird gestartet...");
        t.start();
        System.out.println("Thread t wurde gestartet.");
        // ab hier gibt es zwei nebenläufige Threads (den main-Thread und t, in dem ...
        // ausgeführt wird)
        // im main-Thread kann man nun warten (der main-Thread wird blockiert), bis t
        // (also ...) fertig ist
        while (t.isAlive()) {
            Thread.sleep(10);
        }
        System.out.println("Thread t ist fertig.");
    }
}