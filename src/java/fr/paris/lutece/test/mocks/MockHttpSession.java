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

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

public class MockHttpSession implements HttpSession
{
    private static int sessionId = 0;
    private int id = sessionId++;

    @Override
    public long getCreationTime()
    {
        return 0;
    }

    @Override
    public String getId()
    {
        return "" + id;
    }

    public String changeSessionId()
    {
        return "" + (id = sessionId++);
    }

    @Override
    public long getLastAccessedTime()
    {
        return 0;
    }

    @Override
    public ServletContext getServletContext()
    {
        return null;
    }

    @Override
    public void setMaxInactiveInterval(int interval)
    {
    }

    @Override
    public int getMaxInactiveInterval()
    {
        return 0;
    }

    private final Map<String, Object> attributes = new HashMap<>();

    @Override
    public Object getAttribute(String name)
    {
        return this.attributes.get(name);
    }

    @Override
    public Enumeration<String> getAttributeNames()
    {
        return Collections.enumeration(new LinkedHashSet<>(this.attributes.keySet()));
    }

    @Override
    public void setAttribute(String name, Object value)
    {
        if (value != null)
        {
            Object oldValue = this.attributes.put(name, value);
            if (value != oldValue)
            {
                if (oldValue instanceof HttpSessionBindingListener listener)
                    listener.valueUnbound(new HttpSessionBindingEvent(this, name, oldValue));
                if (value instanceof HttpSessionBindingListener listener)
                    listener.valueBound(new HttpSessionBindingEvent(this, name, value));
            }
        } else
            removeAttribute(name);
    }

    @Override
    public void removeAttribute(String name)
    {
        Object value = this.attributes.remove(name);
        if (value instanceof HttpSessionBindingListener listener)
            listener.valueUnbound(new HttpSessionBindingEvent(this, name, value));
    }

    @Override
    public void invalidate()
    {
    }

    @Override
    public boolean isNew()
    {
        return false;
    }

}
