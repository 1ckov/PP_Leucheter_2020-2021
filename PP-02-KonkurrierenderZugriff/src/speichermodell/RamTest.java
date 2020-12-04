package speichermodell;

public class RamTest extends Thread{
    private int i;
    public RamTest (final int i) {
        this.i = i;
    }
    
    public void print (final int i) {
        final var a = i*i;
        final var b = new Integer(a);
        System.out.println(b);
    }

    @Override
    public void run(){
        print(this.i);
    }
}


public static void main(String[] args) {
    new RamTest(2).start();
    new RamTest(3).start();
    
}