package personalExamples.threadLocal;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UserContextHolder {

    public static void processCreateUser(String userName) {
        UserContextHolder.holder.set(new User(userName));
    }

    public static User processGetUser() {
        return UserContextHolder.holder.get();
        
    }

    static ThreadLocal<User> holder = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println();
        ExecutorService pool = Executors.newFixedThreadPool(4);
        LinkedList<Future<User>> futures = new LinkedList<>();
        
        for (int i = 0; i < 50; i++) {
            final String username = "User" + i;
            futures.add(pool.submit(() -> {
                processCreateUser(username);
                return processGetUser();
            }));
        }

        pool.shutdown();

        for(Future<User>future : futures){
            System.out.println(future.get().getUsername());
        }

    }

}
