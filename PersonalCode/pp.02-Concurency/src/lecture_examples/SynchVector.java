package lecture_examples;

import java.util.Vector;

public class SynchVector {
    private static final int MAX = 100;
    private static Vector<Integer> vec = new Vector<>();
    public static void main(String[] args) throws InterruptedException {
        for(int i = 0;i < MAX ;i++){
            vec.add(i);
        }
        
        Thread remover = new Thread(()-> {
            for(int i = 0; i < vec.size(); i++){
                if((vec.get(i) % 2) == 1){
                    System.out.println(Thread.currentThread().getName()+" removed: " + vec.get(i));
                    vec.remove(i);
                }
            }
        },"Odd-remover");
        Thread adder = new Thread(()-> {
            for(int i = 0; i < MAX; i++){
                if(i % 2 == 1){
                    vec.add(i);
                    System.out.println(Thread.currentThread().getName()+" added: " + vec.get(i));
                }
            }
        },"Odd-adder");


        remover.start(); adder.start();
        remover.join();adder.join();
    

    }
}
