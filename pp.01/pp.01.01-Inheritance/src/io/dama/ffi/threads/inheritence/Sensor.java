package io.dama.ffi.threads.inheritence;

public class Sensor extends Thread {
    // eigentlich abstract

    private final long frequency;

    public Sensor(final long frequency) {
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
            try {
                Thread.sleep(this.frequency);
            } catch (final InterruptedException e) {
                // empty
            }
        }
    }

    public static void main(final String... args) {
        var s = new Sensor(1000);
    }
}
