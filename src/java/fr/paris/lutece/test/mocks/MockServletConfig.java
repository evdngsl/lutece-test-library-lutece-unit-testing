package fr.paris.lutece.test.mocks;

import java.util.Enumeration;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;

public class MockServletConfig implements ServletConfig
{

    @Override
    public String getServletName()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServletContext getServletContext()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getInitParameter(String name)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Enumeration<String> getInitParameterNames()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
