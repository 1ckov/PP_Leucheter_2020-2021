package higher_order_functions;

public class main{
    public static void main(String[] args) {
        var result = Calculation.calculate(5, 7, new Function(){
            @Override
            public int apply(int a, int b){
                return a + b;
            } 
        }
        );
        System.out.println(result);
    }}
    


