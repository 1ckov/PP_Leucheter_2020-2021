package aufgabe_1;
public class Main extends Thread {
    public void interact() {
        System.out.println(Thread.currentThread().getName());
    }

    @Override
    public void run() {
        while(true);    
    }

    public static void main(String[] args) {
        var t = new Main();
        t.setName("Daemon");
        //Deamon threads "Die" when no user threads remain active
        t.setDaemon(true);
        t.start();
        var actor = new Thread(() -> t.interact(), "Actor" );
        actor.start();
    }

    
}
