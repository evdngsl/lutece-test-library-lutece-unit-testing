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
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getId()
    {
        // TODO Auto-generated method stub
        return "" + id;
    }

    public String changeSessionId()
    {
        return "" + (id = sessionId++);
    }

    @Override
    public long getLastAccessedTime()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ServletContext getServletContext()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setMaxInactiveInterval(int interval)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public int getMaxInactiveInterval()
    {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isNew()
    {
        // TODO Auto-generated method stub
        return false;
    }

}
