package fr.paris.lutece.test.mocks;

import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;

public class MockServletOutputStream extends ServletOutputStream
{
    private final OutputStream delegate;

    public MockServletOutputStream(OutputStream out)
    {
        delegate = out;
    }

    public final OutputStream getTargetStream()
    {
        return delegate;
    }

    @Override
    public void write(int b) throws IOException
    {
        delegate.write(b);
    }

    @Override
    public void flush() throws IOException
    {
        super.flush();
        delegate.flush();
    }

    @Override
    public void close() throws IOException
    {
        super.close();
        delegate.close();
    }

    @Override
    public boolean isReady()
    {
        return true;
    }

    @Override
    public void setWriteListener(WriteListener w)
    {
        throw new UnsupportedOperationException();
    }
}
