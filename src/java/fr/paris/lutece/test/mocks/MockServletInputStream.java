package fr.paris.lutece.test.mocks;

import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;

public class MockServletInputStream extends ServletInputStream
{
    private final InputStream delegate;
    private boolean finished = false;

    public MockServletInputStream(InputStream sourceStream)
    {
        delegate = sourceStream;
    }

    @Override
    public int read() throws IOException
    {
        int data = delegate.read();
        if (data == -1)
            this.finished = true;
        return data;
    }

    @Override
    public int available() throws IOException
    {
        return delegate.available();
    }

    @Override
    public void close() throws IOException
    {
        super.close();
        delegate.close();
    }

    @Override
    public boolean isFinished()
    {
        return finished;
    }

    @Override
    public boolean isReady()
    {
        return true;
    }

    @Override
    public void setReadListener(ReadListener readListener)
    {
        throw new UnsupportedOperationException();
    }
}