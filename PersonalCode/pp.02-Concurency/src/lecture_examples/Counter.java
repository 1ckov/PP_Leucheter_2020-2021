package lecture_examples;

public class Counter {
    public int counter = 0;
    public static void main(String[] args) {
        //Creating an Instance of Counter in the Heap
        //final is used so Closure is assured
        final Counter c = new Counter();
        //using lambda to Overwrite Thread.run()
        new Thread(() -> {
            //Saving the value of counter in a local Stack variable
            var a = c.counter;
            //Incrementing the value of counter by 1 (counter = 1?)
            a++;
            //Setting counter again
            c.counter = a;
            //Executing thread
        }).start();
        
        new Thread(() -> {
            //Saving the value of counter in a local Stack variable
            var b = c.counter;
            //Incrementing the value of counter by 2 (counter = 2?)
            b+=2;
            //Setting counter again
            c.counter = b;
            //Executing thread
        }).start();
        //Possibilities are 1 2 and 3 
        System.out.println("counter: " + c.counter);
        //Java console gave => 3
        //Zsh console gave => 1

        //
    }

}
