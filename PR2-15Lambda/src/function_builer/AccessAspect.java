package src.function_builer;

public class AccessAspect {
    public static Function addAdccess(Predicate<Object> p, Function f){
        return () ->{ 
            if(p.test(null)){
            f.apply();
        }
        };
    }    
}
