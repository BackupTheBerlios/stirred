/*
 * This work is licensed under the Creative Commons Attribution License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/1.0/ 
 * or send a letter to Creative Commons, 559 Nathan Abbott Way, Stanford, California 94305, USA. 
 */
package stirred.handler;

import stirred.protocol.Protocol;
import stirred.transport.Transport;

/**
 * Default implementation for a TCP based line-oriented handler.
 * <code>DefaultTCPHandler</code> can also send notifications to a <code>Protocol</code>
 * if one is referenced.
 * @author <a href="mailto:kevin@electricanvil.com">Kevin A. Smith</a>
 * @version 0.1
 * @since 2003/05/08
 * @see stirred.protocol.Protocol
 */
public class DefaultTCPHandler implements TCPHandler, LineHandler {
    
    private StringBuffer _accumulator;
    private String _delim = "\r\n";
    private int _delimSize = _delim.length();
    private boolean _dataMode = false;
    private Protocol _protocol;
    /* 
     * @see stirred.handler.Handler#dataReceived(stirred.transport.Transport)
     */
    public void process(Transport transport) {
        // If not in data mode, then accumulate text
        // And look for lines
        if(!_dataMode) {
            if(_accumulator == null)
                _accumulator = new StringBuffer();
            _accumulator.append(transport.readString());
            String temp = _accumulator.toString();
            
            int start = 0, end = 0, prevEnd = 0;
            end = temp.indexOf(_delim);
            // Parse any lines and dispatch them
            // via lineReceived()
            while(end > -1) {
                String line = temp.substring(start, end);
                lineReceived(line, transport);
                prevEnd = end + _delimSize;
                start = end + _delimSize;
                end = temp.indexOf(_delim, start);
            }
            StringBuffer buf = new StringBuffer();
            buf.append(_accumulator.substring(prevEnd));
            _accumulator = buf;
        }
        // In data mode, so notify using raw data
        else
            dataReceived(transport);
    }

    /* 
     * @see stirred.handler.TCPHandler#openConnection(java.lang.String)
     */
    public boolean openConnection(String address) {
        if(_delim == null)
            throw new IllegalStateException("Line delimiter is not set");
        return true;
    }

    /* 
     * @see stirred.handler.TCPHandler#closeConnection()
     */
    public void closeConnection() {

    }

    /* 
     * @see stirred.handler.LineHandler#lineReceived(java.lang.String)
     */
    public void lineReceived(String line, Transport transport) {
        if(_protocol != null)
            _protocol.lineReceived(line, transport);
    }

    /* 
     * @see stirred.handler.LineHandler#setLineDelimiter(java.lang.String)
     */
    public void setLineDelimiter(String delim) {
        if(delim == null)
            throw new IllegalArgumentException("Line delimiter cannot be null");
        _delim = delim;
        _delimSize = _delim.length();
    }

    /* 
     * @see stirred.handler.LineHandler#getLineDelimiter()
     */
    public String getLineDelimiter() {
        return _delim;
    }

    /* 
     * @see stirred.handler.LineHandler#setDataMode(boolean)
     */
    public void setDataMode(boolean flag) {
        _dataMode = flag;
    }

    /* 
     * @see stirred.handler.LineHandler#getDataMode()
     */
    public boolean getDataMode() {
        return _dataMode;
    }

    /* 
     * @see stirred.handler.Handler#dataReceived(stirred.transport.Transport)
     */
    public void dataReceived(Transport transport) {
        if(_protocol != null)
            _protocol.dataReceived(transport);
    }
    /*
     * @see stirred.handler.Handler#getProtocol()
     */
    public Protocol getProtocol() {
        return _protocol;
    }

    /*
     * @see stirred.handler.Handler#setProtocol(stirred.protocol.Protocol)
     */
    public void setProtocol(Protocol protocol) {
        _protocol = protocol;
        _protocol.setHandler(this);
    }

}
