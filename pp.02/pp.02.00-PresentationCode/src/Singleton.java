public class Singleton {

    private static Singleton instance;

    private Singleton() {
        super();
    }

    public static Singleton getInstance() {
        if (instance == null) {
            Singleton.instance = new Singleton();
        }
        return Singleton.instance;
    }

    public static void main(String[] args) {
        var s1 = Singleton.getInstance();
        var s2 = Singleton.getInstance();
        System.out.println(s1 == s2);
    }
}