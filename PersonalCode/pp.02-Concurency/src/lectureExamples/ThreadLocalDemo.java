package lectureExamples;

public class ThreadLocalDemo {
    public static class Runner implements Runnable {

        private static ThreadLocal<Integer> mem = new ThreadLocal<>(){
            @Override
            protected Integer initialValue(){
                return Integer.valueOf(1);
            }
        };

        @Override
        public void run() {
            while(true){
                
                mem.set(mem.get()+1);
                System.out.println(mem.get());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        }

    }
    public static void main(String[] args) {
        final var runnable = new Runner();
        new Thread(runnable,"runner1" ).start();
        new Thread(runnable,"runner2" ).start();
    }
}