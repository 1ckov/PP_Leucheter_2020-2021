package io.dama.ffi.threads;
public class MainThread {
    public static void main(final String[] args) {
        // Anzahl der Prozessoren abfragen
        final int nr = Runtime.getRuntime().availableProcessors();
        System.out.println("Anzahl der Prozessoren " + nr);
        // Eigenschaften des main-Threads
        final Thread self = Thread.currentThread();
        System.out.println("Name : " + self.getName());
        System.out.println("Priorität : " + self.getPriority());
        System.out.println("ID : " + self.getId());
    }
}
