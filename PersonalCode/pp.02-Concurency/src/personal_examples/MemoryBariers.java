package personal_examples;

public class MemoryBariers {
    //MemBarier 1 - volatile
    //Forces all changes made to variable to be synched between Threads
    public volatile int i;

    //MemBarier 2 - synchronized/lock Objects
    //1.Once a thread enters this method all other threads have to wait
    //2.Performs operations on current Object 
    //3.After it has finished executing the method 
    //all changes are "synchronized" with other Threads and the Lock is removed 
    public synchronized int increase(final int factor){
        i += factor;
        return i;
    }

    public void print(String msg){
        System.out.println(msg + " i : " + i + " Dynamic");
    }
    //MemBarier 3 - Starting a Thread
    //All operation Preeceding the Start of the second Thread have finished
    public static void main(String[] args) {
        var in_heap = new MemoryBariers();

        System.out.println("Main Thread pre LambdaThread 1 i: " + in_heap.i + " Static");
       
        new Thread(()->{
            in_heap.increase(2);
            in_heap.print("Lambda Thread 1 post increase(2)");
            System.out.println("Lambda Thread 1 post increase(2) i: " + in_heap.i + " Static");
        }).start();    
        
        System.out.println("Main Thread post LambdaThread 1 i: " + in_heap.i + " Static");
          
        new Thread(()->{
            in_heap.increase(2);
            in_heap.print("Lambda Thread 2 post increase(2)");
            System.out.println("Lambda Thread 2 post increase(2) i  " + in_heap.i + " Static");
        }).start();     
        //MemBarier 4 Default Value Initialization (0, false or null) ? 


        //MemBarier 5 Join

        Thread t1 = new Thread(()->{
            in_heap.increase(2);
            in_heap.print("t1 Thread post increase(2)");
            System.out.println("t1 Thread post increase(2) i: " + in_heap.i + " Static");
        }); 
           
        Thread t2 = new Thread(()->{
            in_heap.increase(2);
            in_heap.print("t2 Thread post increase(2)");
            System.out.println("t2 Thread post increase(2) i: " + in_heap.i + " Static");
            try {
                System.out.println("Going to Sleep");;
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                System.err.println(e);

            }
        });  
        
        t2.start();
        System.out.println("Main Thread post t2.start i: " + in_heap.i + " Static");
        try {
            t2.join();
            
        } catch (Exception e) {
            System.err.println(e);
        }
        System.out.println("Main Thread post t2.join() i: " + in_heap.i + " Static");
        t1.start();

        //MemBarier 6 Interrupts
        //After one t2 calls an interrupt on t1 

        Thread t3 = new Thread(()-> {
            boolean isInterupted = false;
            while(!isInterupted){
                in_heap.print("t3 Thread pre increase(2)");
                in_heap.increase(2);
                in_heap.print("t3 Thread post increase(2)");
            }
        });

        Thread t4 = new Thread(()-> {
            try {
                t3.interrupt();
                in_heap.print("t4 Thread post interupt()");
            } catch (Exception e) {
                System.err.println(e);
            }
        });
        t3.start();
        t4.start();
        


    }



    
}
