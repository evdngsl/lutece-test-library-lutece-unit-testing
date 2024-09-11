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

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Map;
import java.util.Set;

import fr.paris.lutece.test.TestLogService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterRegistration;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.ServletRegistration.Dynamic;
import jakarta.servlet.SessionCookieConfig;
import jakarta.servlet.SessionTrackingMode;
import jakarta.servlet.descriptor.JspConfigDescriptor;

@ApplicationScoped
public class MockServletContext implements ServletContext
{
    @Override
    public String getContextPath()
    {
        return "lutece-path-mock";
    }

    @Override
    public ServletContext getContext(String uripath)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public int getMajorVersion()
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return 0;
    }

    @Override
    public int getMinorVersion()
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return 0;
    }

    @Override
    public int getEffectiveMajorVersion()
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return 0;
    }

    @Override
    public int getEffectiveMinorVersion()
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return 0;
    }

    @Override
    public String getMimeType(String file)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public Set<String> getResourcePaths(String path)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public URL getResource(String path) throws MalformedURLException
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public InputStream getResourceAsStream(String path)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public RequestDispatcher getNamedDispatcher(String name)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public void log(String msg)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
    }

    @Override
    public void log(String message, Throwable throwable)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
    }

    @Override
    public String getRealPath(String path)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public String getServerInfo()
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public String getInitParameter(String name)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public Enumeration<String> getInitParameterNames()
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public boolean setInitParameter(String name, String value)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return false;
    }

    @Override
    public Object getAttribute(String name)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public Enumeration<String> getAttributeNames()
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public void setAttribute(String name, Object object)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
    }

    @Override
    public void removeAttribute(String name)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
    }

    @Override
    public String getServletContextName()
    {
        return "lutece-servlet-context-mock";
    }

    @Override
    public Dynamic addServlet(String servletName, String className)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public Dynamic addServlet(String servletName, Servlet servlet)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public Dynamic addServlet(String servletName, Class<? extends Servlet> servletClass)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public Dynamic addJspFile(String servletName, String jspFile)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public <T extends Servlet> T createServlet(Class<T> clazz) throws ServletException
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public ServletRegistration getServletRegistration(String servletName)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public Map<String, ? extends ServletRegistration> getServletRegistrations()
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public jakarta.servlet.FilterRegistration.Dynamic addFilter(String filterName, String className)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public jakarta.servlet.FilterRegistration.Dynamic addFilter(String filterName, Filter filter)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public jakarta.servlet.FilterRegistration.Dynamic addFilter(String filterName, Class<? extends Filter> filterClass)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public <T extends Filter> T createFilter(Class<T> clazz) throws ServletException
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public FilterRegistration getFilterRegistration(String filterName)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public Map<String, ? extends FilterRegistration> getFilterRegistrations()
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public SessionCookieConfig getSessionCookieConfig()
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public void setSessionTrackingModes(Set<SessionTrackingMode> sessionTrackingModes)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
    }

    @Override
    public Set<SessionTrackingMode> getDefaultSessionTrackingModes()
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public Set<SessionTrackingMode> getEffectiveSessionTrackingModes()
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public void addListener(String className)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
    }

    @Override
    public <T extends EventListener> void addListener(T t)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
    }

    @Override
    public void addListener(Class<? extends EventListener> listenerClass)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
    }

    @Override
    public <T extends EventListener> T createListener(Class<T> clazz) throws ServletException
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public JspConfigDescriptor getJspConfigDescriptor()
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public ClassLoader getClassLoader()
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public void declareRoles(String... roleNames)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
    }

    @Override
    public String getVirtualServerName()
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public int getSessionTimeout()
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return 0;
    }

    @Override
    public void setSessionTimeout(int sessionTimeout)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
    }

    @Override
    public String getRequestCharacterEncoding()
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public void setRequestCharacterEncoding(String encoding)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
    }

    @Override
    public String getResponseCharacterEncoding()
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        return null;
    }

    @Override
    public void setResponseCharacterEncoding(String encoding)
    {
        TestLogService.info("MockServletContext." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
    }
}
