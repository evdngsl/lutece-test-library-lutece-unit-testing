/*
 * Copyright (c) 2002-2024, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
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
    }

    @Override
    public boolean getRollbackOnly()
    {
        TestLogService.info("MockTransactionSynchronizationRegistry.getRollbackOnly");
        return false;
    }
}
