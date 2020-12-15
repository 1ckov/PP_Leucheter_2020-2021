package src.using_existing_interfaces;

import java.util.function.BiFunction;
import src.functional_interface_example.Action;
//import java.util.function.IntBinaryOperator;

public class Main {
    public static void main(String[] args) {
        //IntBinaryOperator sub = (a,b) -> a + b;
        //IntBinaryOperator add = (a,b) -> a - b;

        BiFunction<Integer, Integer, Integer> sub = (a, b) -> a - b;
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

        //In order to use input 1 and 2 inside the lambda their values musnt change thruought the program,
        //in other words we either set them as final or we dont assign new values to them
        int input1 = 5;
        int input2 = 3;

        Action a = (s) -> {System.out.print("Value of input1 = "+input1+" and value of input2 = " + input2 + s);};
        a.run("\nAnd the Result is: "); 
        System.out.print(Calculator.calculate(input1, input2, sub)+"\n");


        //input1 = 1; Error because by changing the value of inputs 1 and 2 the variables arent effectevly final anymore 
        //input2 = 7; Error because by changing the value of inputs 1 and 2 the variables arent effectevly final anymore 
        a.run("\nAnd the Result is: "); 
        System.out.println(Calculator.calculate(input1, input2, add));
    }
}
