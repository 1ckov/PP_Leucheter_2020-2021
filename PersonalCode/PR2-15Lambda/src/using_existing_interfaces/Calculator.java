package src.using_existing_interfaces;
//import java.util.function.IntBinaryOperator;
import java.util.function.BiFunction;

/**Done woithout generics */
//public class Calculator {

    //public static int calculate(int input1, int input2, IntBinaryOperator fun) {
        //return fun.applyAsInt(input1, input2);
    //}

//}
public class Calculator {
    public static int calculate(int input1, int input2, BiFunction<Integer, Integer, Integer> fun){
        return fun.apply(input1, input2);
    }
}