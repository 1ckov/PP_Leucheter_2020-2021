package src.higher_order_functions;

    public class Main{
//     public static void main(String[] args) {
//         var result = Calculation.calculate(5, 7, new Function(){
//             @Override
//             public int apply(int a, int b){
//                 return a + b;
//             } 
//         }
//         );
//         System.out.println(result);
//     }
        public static void main(String[] args) {
            /**This is the same as 
             * Here we give the Lamda as input to a function.
            */
            var result = Calculation.calculate(5,7, (x, y) -> x + y);
            System.out.println(result);
            /**This, 
             * Here we save the lambda and call it later with values. 
            */
            Function add = (x, y) -> x + y;
            result = add.apply(5, 7); 
            System.out.println(result);
            Function sub = (x, y) -> x + y;
            result = sub.apply(15, 2);
            System.out.println(result);
        }
}