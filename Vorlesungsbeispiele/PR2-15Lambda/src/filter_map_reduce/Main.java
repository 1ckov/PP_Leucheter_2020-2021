package src.filter_map_reduce;

public class Main {
    public static <T> int count(T[] array, Predicate<T> pred){
        var count = 0;
        for(var t : array){
            if(pred.test(t)){
                count++;
            }
        }
        return count;
    }
    public static <T,R> R[] transfer(T[] array, Function<T,R> func){
        var res = (R[]) new Object[array.length];
        for (var i=0; i < array.length; i++) {
            res[i] = func.apply(array[i]);
        } 
        return res;
    }
    public static <T,R> void transferAndConsume(T[] array, Function <T,R> func, Consumer<R> consumer){
        for(var i=0; i < array.length; i++) {
            var r = func.apply(array[i]);
            System.out.print("The String at pos " + i + " has ");
            consumer.accept(r);
            System.out.print(" characters\n");
        }
    }
    public static void main(String[] args) {
        String[] str = {"sad",
            "sad","das","seed","as",  
            "sad","das","seed","as",  
            "sad","das","seed","as",  
            "sad","das","seed","as"};


        int countOf = count(str, (s -> (s.length() == 3)));
        System.out.println("The number of Strings with a lenght of 3 in the array is: " + countOf);

        Object[] res = transfer(str, s -> s.length());
        int i = 0;
        for(var obj : res) {
            System.out.println("The String at pos " + i++ + " has "+ obj + " characters");
        }
        transferAndConsume(str, s -> s.length(), j -> System.out.print(j));



    }
}