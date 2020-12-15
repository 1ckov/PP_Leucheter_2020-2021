package src.scope;

public class Scoping {
    public String toString(){
        return "Scoping";
    }
    /**
     * The lambda inside uses this, and it refers to the Scoping class.
     * @return
     */
    public Runnable createRunner(){
        return () -> System.out.println(this.toString());
    }
    public static void main(String... args){
        new Scoping().createRunner().run();
    }
    
}
