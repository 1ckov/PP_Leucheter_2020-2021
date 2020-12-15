package lecture_examples;

public class SynchAccess3 {
    private int counter = 1;
    private Object lock = new Object();
    public void doubler(){
        synchronized(lock){
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
        synchronized(lock){
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
        final var counter = new SynchAccess3();
        new Thread (()-> {
            while(true){
                counter.doubler();
            }
        },"doubler").start();
        new Thread (()-> {
            while(true){
                counter.decreser();
            }
        },"decreaser").start();
    }
}
