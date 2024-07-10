package fr.paris.lutece.test.mocks;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.atomic.AtomicLong;

import jakarta.enterprise.concurrent.ManagedThreadFactory;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Stands for a container-provided ManagedThreadFactory when under java SE.
 * 
 * Contrary to other (application defined) beans, this mock is @ApplicationScoped because it is always needed, and because it does not actually replace another
 * app bean.
 */
@ApplicationScoped
public class MockManagedThreadFactory implements ManagedThreadFactory
{
    private final ThreadGroup group = Thread.currentThread().getThreadGroup();
    private final AtomicLong c = new AtomicLong(0);

    @Override
    public Thread newThread(Runnable r)
    {
        return new Thread(group, r, "MockManagedThreadFactory-" + c.getAndIncrement());
    }

    @Override
    public ForkJoinWorkerThread newThread(ForkJoinPool pool)
    {
        return new MockForkJoinWorkerThread(pool);
    }

    public static class MockForkJoinWorkerThread extends ForkJoinWorkerThread
    {
        public MockForkJoinWorkerThread(ForkJoinPool pool)
        {
            super(pool);
        }
    }
}
