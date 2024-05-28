package fr.paris.lutece.test.mocks;

import java.util.HashMap;
import java.util.Map;

import fr.paris.lutece.test.TestLogService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Status;
import jakarta.transaction.Synchronization;
import jakarta.transaction.TransactionSynchronizationRegistry;

/**
 * Stands for a container-provided TransactionSynchronizationRegistry when under java SE.
 * 
 */
@ApplicationScoped
public class MockTransactionSynchronizationRegistry implements TransactionSynchronizationRegistry
{
    private final ThreadLocal<TransactionObject> transactions = new ThreadLocal<>()
    {
        @Override
        protected TransactionObject initialValue()
        {
            return new TransactionObject();
        }
    };

    private TransactionObject transaction()
    {
        return transactions.get();
    }

    private static class TransactionObject
    {
        private final Map<Object, Object> resources = new HashMap<>();
        private int status = Status.STATUS_UNKNOWN;
    }

    @Override
    public Object getTransactionKey()
    {
        return transaction();
    }

    @Override
    public void putResource(Object key, Object value)
    {
        transaction().resources.put(key, value);
    }

    @Override
    public Object getResource(Object key)
    {
        return transaction().resources.get(key);
    }

    @Override
    public void registerInterposedSynchronization(Synchronization sync)
    {
        TestLogService.info("MockTransactionSynchronizationRegistry.registerInterposedSynchronization" + sync);
        // TODO Auto-generated method stub
    }

    @Override
    public int getTransactionStatus()
    {
        return transaction().status;
    }

    @Override
    public void setRollbackOnly()
    {
        TestLogService.info("MockTransactionSynchronizationRegistry.setRollbackOnly");
        // TODO Auto-generated method stub
    }

    @Override
    public boolean getRollbackOnly()
    {
        TestLogService.info("MockTransactionSynchronizationRegistry.getRollbackOnly");
        // TODO Auto-generated method stub
        return false;
    }
}
