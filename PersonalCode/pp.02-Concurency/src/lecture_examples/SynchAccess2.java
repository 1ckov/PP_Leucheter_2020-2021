package lecture_examples;

public class SynchAccess2 {
    //Our counter which we will double
    private int counter = 1;
    //Our method which will double our counter from outside
    public void doubler() {
    //As soon as we enter the synchronized block, 
    //any other Thread wanting to use this 
    //will have to wait until we are Finished
        synchronized(this){
            this.counter = this.counter * 2;
            System.out.printf("%s: counter==%d\n ",Thread.currentThread().getName(),this.counter);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    public void decreser(){
        synchronized(this){
            this.counter = this.counter - 2;
            System.out.printf("%s: counter==%d\n ",Thread.currentThread().getName(),this.counter);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    public static void main(String[] args) {
        var counter = new SynchAccess2();
        new Thread(() -> {
            while(true){
                counter.doubler();
            }
        },"doubler").start();
        new Thread(() -> {
            while(true){
                counter.decreser();
            }
        },"decreaser").start();
    }
}
