package personalExamples.threadLocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSafeFormatter {
    // Anonymous Class
    public static ThreadLocal<SimpleDateFormat> df = new ThreadLocal<>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        };

        @Override
        public SimpleDateFormat get() {
            return super.get();
        }

    };

    public static ThreadLocal<SimpleDateFormat> dfFactory = 
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    // Anonymous Class
    public static ThreadLocal<Random> bdayGenerator = new ThreadLocal<>(){
        @Override
        protected Random initialValue() {
            return new Random();
        };

        @Override
        public Random get() {
            return super.get();
        };
    };

    // Factory Method
    public static ThreadLocal<Random> bdayGeneratorFactory = 
            ThreadLocal.withInitial(() -> new Random());
    
    public static class UserService {

        static ExecutorService threadPool = Executors.newFixedThreadPool(10);

        public static void main(String[] args) throws InterruptedException, ExecutionException {
            for (int i = 0; i < 10000; i++) {
                var future = threadPool.submit(() -> {
                    String birthDate = UserService.birthDate();
                    System.out.println(birthDate);
                });
                future.get();
            }
            threadPool.shutdown();

        }

        public static String birthDate() {
            long bdayUnixTime;
            var generator = bdayGeneratorFactory.get();
            bdayUnixTime = generator.nextLong() % System.currentTimeMillis();
            Date bday = new Date(bdayUnixTime);
            return dfFactory.get().format(bday);

        }
    }

}
