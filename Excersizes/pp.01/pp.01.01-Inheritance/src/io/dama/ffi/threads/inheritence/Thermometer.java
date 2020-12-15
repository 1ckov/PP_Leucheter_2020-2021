package io.dama.ffi.threads.inheritence;

import java.util.Random;

public class Thermometer extends Sensor {
    private final Random rand;

    public Thermometer(final long frequency) {
        super(frequency) ;
        this.rand = new Random();
    }

    @Override
    protected String reading() {
        return String.format("(%04d freq.): %3d°C, ID:%d", getFrequency(), this.rand.nextInt(100), this.getId());
    }

    public static void main(final String... args) {

        var self = Thread.currentThread();
        System.out.println("Priority : " + self.getPriority());
        System.out.println("ID : " + self.getId());
        var s1 = new Thermometer(1000);
        s1.start();
        var s2 = new Thermometer(3000);
        s2.start();
    }   
}
