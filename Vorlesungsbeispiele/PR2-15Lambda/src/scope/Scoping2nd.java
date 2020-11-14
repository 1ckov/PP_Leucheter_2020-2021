package src.scope;

public class Scoping2nd {
    public String toString(){
        return "Scooping";
    }
    public Runnable createRunner(){
        return new Runnable(){
            public void run(){
                System.out.println(this.toString());
            }
        };
    }
    public static void main(String[] args) {
        new Scoping2nd().createRunner().run();
    }
    
}
