package io.dama.ffi.threadpool;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class ScheduledThreadPoolTest {

    public static void main(final String[] args) throws ParseException {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(() -> System.out.println("beep"), 3, 3,
                SECONDS);
        scheduler.schedule(() -> beeperHandle.cancel(true), 5 * 3, SECONDS);
        scheduler.schedule(() -> System.exit(0), (5 * 3) + 5, SECONDS);
        System.out.println("all tasks scheduled");
        final Date date = new Date();
        scheduler.schedule(() -> System.out.println("Tatort fängt an"), date.getTime() - System.currentTimeMillis(),
                MILLISECONDS);
    }
}
