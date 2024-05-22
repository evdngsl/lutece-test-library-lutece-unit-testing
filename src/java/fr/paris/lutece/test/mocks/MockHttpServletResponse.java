package fr.paris.lutece.test.mocks;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Collection;
import java.util.Locale;

import fr.paris.lutece.test.TestLogService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class MockHttpServletResponse implements HttpServletResponse
{
    private String contentType;
    private String characterEncoding = "ISO-8859-1";

    public String getContentType()
    {
        return contentType;
    }

    public void setContentType(String contentType)
    {
        this.contentType = contentType;
    }

    private final ByteArrayOutputStream content = new ByteArrayOutputStream(1024);

    private final ServletOutputStream outputStream = new ResponseServletOutputStream(this.content);

    public String getContentAsString() throws UnsupportedEncodingException
    {
        return this.content.toString(getCharacterEncoding());
    }

    @Override
    public String getCharacterEncoding()
    {
        return characterEncoding;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException
    {
        return outputStream;
    }

    private PrintWriter writer;

    @Override
    public PrintWriter getWriter() throws UnsupportedEncodingException
    {
        if (this.writer == null)
        {
            Writer targetWriter = new OutputStreamWriter(this.content, getCharacterEncoding());
            this.writer = new ResponsePrintWriter(targetWriter);
        }
        return this.writer;
    }

    @Override
    public void setCharacterEncoding(String charset)
    {
        characterEncoding = charset;
    }

    @Override
    public void setContentLength(int len)
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
    }

    @Override
    public void setContentLengthLong(long len)
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub

    }

    @Override
    public void setBufferSize(int size)
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
    }

    @Override
    public int getBufferSize()
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub

    }

    @Override
    public void resetBuffer()
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
    }

    @Override
    public boolean isCommitted()
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void reset()
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
    }

    @Override
    public void setLocale(Locale loc)
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
    }

    @Override
    public Locale getLocale()
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addCookie(Cookie cookie)
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
    }

    @Override
    public boolean containsHeader(String name)
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String encodeURL(String url)
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String encodeRedirectURL(String url)
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void sendError(int sc, String msg) throws IOException
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
    }

    @Override
    public void sendError(int sc) throws IOException
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
    }

    @Override
    public void sendRedirect(String location) throws IOException
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
    }

    @Override
    public void setDateHeader(String name, long date)
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
    }

    @Override
    public void addDateHeader(String name, long date)
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
    }

    @Override
    public void setHeader(String name, String value)
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
    }

    @Override
    public void addHeader(String name, String value)
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
    }

    @Override
    public void setIntHeader(String name, int value)
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
    }

    @Override
    public void addIntHeader(String name, int value)
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
    }

    @Override
    public void setStatus(int sc)
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
    }

    @Override
    public int getStatus()
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getHeader(String name)
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<String> getHeaders(String name)
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<String> getHeaderNames()
    {
        TestLogService.info("MockHttpServletResponse." + new Object()
        {
        }.getClass().getEnclosingMethod().getName());
        // TODO Auto-generated method stub
        return null;
    }

// force flush every time we can
    private class ResponseServletOutputStream extends MockServletOutputStream
    {
        public ResponseServletOutputStream(OutputStream out)
        {
            super(out);
        }

        @Override
        public void write(int b) throws IOException
        {
            super.write(b);
            super.flush();
        }
    }

    private class ResponsePrintWriter extends PrintWriter
    {
        public ResponsePrintWriter(Writer out)
        {
            super(out, true);
        }

        @Override
        public void write(char[] buf, int off, int len)
        {
            super.write(buf, off, len);
            super.flush();
        }

        @Override
        public void write(String s, int off, int len)
        {
            super.write(s, off, len);
            super.flush();
        }

        @Override
        public void write(int c)
        {
            super.write(c);
            super.flush();
        }

        @Override
        public void close()
        {
            super.flush();
            super.close();
        }
    }
}
