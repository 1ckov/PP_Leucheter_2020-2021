package io.dama.ffi.threads.inheritence;

import java.util.Random;

public class Thermometer extends Sensor {
    private final Random rand;

    public Thermometer(final long frequency) {
        super(frequency);
        this.rand = new Random();
    }

    @Override
    protected String reading() {
        return String.format("(%04d freq.): %3d°C", getFrequency(), this.rand.nextInt(100));
    }

    public static void main(final String... args) {
        var s1 = new Thermometer(1000);
        var s2 = new Thermometer(3000);
    }
}
