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
    
    public static void main(String[] args) {
        String[] str = {"sad",
            "sad","das","seed","as",  
            "sad","das","seed","as",  
            "sad","das","seed","as",  
            "sad","das","seed","as"};


        int countOf = count(str, (s -> (s.length() == 3)));
        System.out.println(countOf);
    }
}