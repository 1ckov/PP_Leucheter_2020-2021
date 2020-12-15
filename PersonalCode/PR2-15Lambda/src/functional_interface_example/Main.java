package src.functional_interface_example;

public class Main {
    
    public static void executor(Action a){
        a.run("Hello World! from up here");
    }

    public static void main(String[] args) {
        /**Two ways of doing the same thing  */
        Action a = s -> System.out.println(s);
        a.run("Hello World! from down here");
        executor(s -> System.out.println(s));
        /** Possible Lambda syntax
         *(a, b) -> a + b 
         *(int a, int b) -> a + b 
         *(a, b) -> {int s = a + b; return s;} 
         * a -> a * a 
         *(a) -> a * a 
         *() -> {System.out.println("Hello World!");} 
         */

    }
}
