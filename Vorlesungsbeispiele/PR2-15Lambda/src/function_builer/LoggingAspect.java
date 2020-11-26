package function_builer;

public class LoggingAspect {
    public static Function addLog(Function f) {
        return () -> {
            System.out.println("Function call: " + f);
            f.apply();
        };
    }    
}
