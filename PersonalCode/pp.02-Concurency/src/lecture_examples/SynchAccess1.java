package lecture_examples;

public class SynchAccess1 {
    //Our counter which we will double
    private int counter = 1;
    //Our method which will double our counter from outside
    public synchronized void doubler() {
    //As soon as we enter the synchronized method, 
    //any other Thread wanting to use this 
    //will have to wait until we are Finished
        this.counter = this.counter * 2;
            System.out.printf("%s: counter==%d\n ",Thread.currentThread().getName(),this.counter);
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.err.println(e);
        }
    } 
    public synchronized void decreaser() {
    //As soon as we enter the synchronized method, 
    //any other Thread wanting to use this 
    //will have to wait until we are Finished
        this.counter = this.counter - 2;
            System.out.printf("%s: counter==%d\n ",Thread.currentThread().getName(),this.counter);
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.err.println(e);
        }
    } 

    public static void main(String[] args) {
        var counter = new SynchAccess1();
        new Thread( () -> {
            while(true){
                counter.doubler();
            }
        },"doubler").start();
        new Thread( () -> {
            while(true){
                counter.decreaser();
            }
        },"decreaser").start();
    }
}
