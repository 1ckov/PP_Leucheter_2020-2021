package io.dama.ffi.cfapi;

import java.util.Random;

public class Lib implements ILib {
    // The Lowest Thread id value 
    Integer lowestThread;

    public String tabs() {
        // Emtpy String
        var s = "";
        // If our only one Thread uses this then no tabs will be added
        // The lower the Thread the further to the left its output will be
        for (var i = 0; i < ((int) Thread.currentThread().getId() - this.lowestThread); i++) {
            s += "\t\t";
        }
        return s;
    }

    @Override
    public Integer calcSync() {
        if ((this.lowestThread == null) || ((int) Thread.currentThread().getId() < this.lowestThread)) {
            this.lowestThread = (int) Thread.currentThread().getId();
        }
        System.out.println(tabs() + "START calc()");
        var rand = new Random();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(tabs() + "END calc()");
        }
        return rand.nextInt();
    }
}
