package aufgabe_2;

class B extends Thread {
    private boolean sollAnhalten =false;
    private Thread self = Thread.currentThread();
    private synchronized boolean sollAnhalten() {
        return this.sollAnhalten;
    }
    private synchronized void anhalten() {
        this.sollAnhalten =true;
    }
    
    @Override
    public void run() {
        while(!sollAnhalten()) {
            //arbeiten
            try{
                Thread.sleep(100000);
            }catch(final InterruptedException e) { 

            }
        }//enden
    }
    public static void main (final String[] args) throws InterruptedException {
        final B t = new B();
        t.start();
        Thread.sleep(1000);
        t.anhalten();
        //Answer
        t.interrupt();
    }
}