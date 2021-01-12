import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public interface Lock {
    public void lock();

    public void lockInterruptibly() throws InterruptedException;

    public boolean tryLock();

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException;

    public void unlock();

    public Condition newCondition();
}
