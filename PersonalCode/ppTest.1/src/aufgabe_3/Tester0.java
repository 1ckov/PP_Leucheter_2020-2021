package aufgabe_3;

public class Tester0 {
    private int x = 0;
    private int y = 0;
    //unsafe
    public void step1(final int n) {
        this.y = n * n;
        this.x++;
    }
    //Thread safe
    public int step2(final int n) {
        return n * n;
    }

    public int step3(final int n) {
        //unsafe
        this.y += n;
        //Thread safe
        return n * 2;
    }
    //unsafe
    public int getX() {
        return this.x;
    }
    //unsafe
    public int getY() {
        return this.y;
    }

}
