package io.dama.ffi.threads.inheritence;

public class Sensor extends Thread {
    // eigentlich abstract

    private final long frequency;
    private Runnable runner;

    public Sensor(final long frequency, Runnable runner) {
        super(runner);
        this.runner = runner;
        this.frequency = frequency;
        start();
    }

    /**
     * @return the frequency
     */
    public long getFrequency() {
        return this.frequency;
    }

    protected String reading() {
        // eigentlich abstract
        return null;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("reading: " + reading());
            runner.run();
        }
    }

    public static void main(final String... args) {
        Runnable runer = () -> {
            System.out.println("im running");
        };
        var s = new Sensor(1000, runer);
    }
}
