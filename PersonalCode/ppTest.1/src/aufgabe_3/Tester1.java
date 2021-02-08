package aufgabe_3;

public class Tester1 {
    private int x = 0;
    private int y = 0;
    //safe now
    public synchronized void step1(final int n) {
        this.y = n * n;
        this.x++;
    }
    //safe as is
    public int step2(final int n) {
        return n * n;
    }
    public int step3(final int n) {
    //safe now
        synchronized(this){
            this.y += n;
        }
        //safe as is
        return n * 2;
    }
    //safe now
    public synchronized int getX() {
        return this.x;
    }
    //safe now
    public synchronized int getY() {
        return this.y;
    }

}

