package lecture_examples;

public class RamTest extends Thread{
    //Gets set through constructor
    private int i;

    //Constructor
    public RamTest (final int i) {
        this.i = i;
    }

    //Prints the square of i    
    public void print (final int i) {
        final var a = i*i;
        final var b = Integer.valueOf(a);
        System.out.println(b);
    }
    
    //Telling the Thread what it should do
    @Override
    public void run(){
        print(this.i);
    }
   
    public static void main(String[] args) {
        new RamTest(2).start();
        new RamTest(3).start();
    }
}

