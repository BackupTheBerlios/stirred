/*
 * This work is licensed under the Creative Commons Attribution License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/1.0/ 
 * or send a letter to Creative Commons, 559 Nathan Abbott Way, Stanford, California 94305, USA. 
 */
package stirred.transport;

import java.nio.ByteBuffer;

/**
 * Concrete implmentation of the <code>Transport</code> interface using
 * <code>java.nio.ByteBuffer</code> as a backing store.
 * @author <a href="mailto:kevin@electricanvil.com">Kevin A. Smith</a>
 * @version 0.1
 * @since 2003/05/12
 * @see java.nio.ByteBuffer
 * @see stirred.transport.Transport
 */
public final class BufferedTransport implements Transport {
    
    private static final int DEFAULT_BUFFER_SIZE = 65336; // 64K
    
    private ByteBuffer _buffer;
    private boolean _closed = false;
    
    public BufferedTransport() {
        _buffer = ByteBuffer.allocateDirect(DEFAULT_BUFFER_SIZE);
    }
    
    public BufferedTransport(int size) {
        _buffer = ByteBuffer.allocateDirect(size);
    }

    /* (non-Javadoc)
     * @see stirred.transport.Transport#writeString(java.lang.String)
     */
    public void writeString(String text) throws TransportWriteException {
        writeData(text.getBytes());
    }

    /* (non-Javadoc)
     * @see stirred.transport.Transport#writeData(byte[])
     */
    public void writeData(byte[] data) throws TransportWriteException {
        writeData(data, 0, data.length);
    }
    
    public void writeData(byte[] data, int start, int end) throws TransportWriteException {
        if(_buffer == null)
            throw new IllegalStateException("Backing buffer is null");
        _buffer.clear();
        _buffer.rewind();
        int amt = _buffer.capacity() - (end - start);
        if(amt < 0) {
            throw new TransportWriteException("Transport overflow :" + (amt * -1) + " bytes");
        }
        _buffer.put(data, start, end);
    }
    
    public String readString() {
        byte[] data = readData();
        return new String(data);
    }
    
    public byte[] readData() {
        if(_buffer == null)
            throw new IllegalStateException("Backing buffer is null");
        _buffer.flip();
        byte[] retval = new byte[_buffer.limit()];
        _buffer.get(retval);
        _buffer.flip();
        _buffer.rewind();
        return retval;
    }
    
    public ByteBuffer getBuffer() {
        return _buffer; 
    }


    /* (non-Javadoc)
     * @see stirred.transport.Transport#isEmpty()
     */
    public boolean isEmpty() {
        if(_buffer != null)
            return _buffer.position() == 0;
        else
            return true;
    }

    /* (non-Javadoc)
     * @see stirred.transport.Transport#destroy()
     */
    public void destroy() {
        _buffer = null;
        _closed = true;
        System.gc();
    }

    /* (non-Javadoc)
     * @see stirred.transport.Transport#remaining()
     */
    public int remaining() {
        return _buffer.remaining();
    }
    
    public void closeConnection() {
        _closed = true;
    }
    
    public boolean isClosed() {
        return _closed;
    }

}
