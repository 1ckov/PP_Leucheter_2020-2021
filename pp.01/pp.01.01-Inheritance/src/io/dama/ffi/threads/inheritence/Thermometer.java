package io.dama.ffi.threads.inheritence;

import java.util.Random;

public class Thermometer extends Sensor {
    private final Random rand;

    public Thermometer(final long frequency , Runnable runner) {
        super(frequency, runner);
        this.rand = new Random();
    }

    @Override
    protected String reading() {
        return String.format("(%04d freq.): %3d°C, ID:%d", getFrequency(), this.rand.nextInt(100), this.getId());
    }

    public static void main(final String... args) {

        var self = Thread.currentThread();
        System.out.println("Name : "+ self.getName());
        System.out.println("Priority : " + self.getPriority());
        System.out.println("ID : " + self.getId());
        Runnable runer = () -> {
            System.out.println("im running");
        };
        var s1 = new Thermometer(1000, runer);
        
        Runnable runer2 = () -> {
            System.out.println("im running faster");
        };
        var s2 = new Thermometer(3000, runer2);
    }
}
