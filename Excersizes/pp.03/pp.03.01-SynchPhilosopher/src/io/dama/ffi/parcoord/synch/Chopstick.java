package io.dama.ffi.parcoord.synch;

public class Chopstick {
    private boolean isTaken = false;

    public synchronized boolean getState(){
        return this.isTaken;
    }
    public synchronized void setState(boolean isTaken){
        this.isTaken = isTaken;
    }
}
