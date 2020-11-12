package higher_order_functions;
public class Calculation {
    public static int calculate(int input1, int input2, Function function){
        return function.apply(input1, input2);
    }
}
