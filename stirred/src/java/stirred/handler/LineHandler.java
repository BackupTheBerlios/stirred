/*
 * This work is licensed under the Creative Commons Attribution License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/1.0/ 
 * or send a letter to Creative Commons, 559 Nathan Abbott Way, Stanford, California 94305, USA. 
 */
package stirred.handler;

import stirred.transport.Transport;


/**
 * Extends the basic behavior of <code>Handler</code> to include line detection
 * for text based messaging and a callback method for per-line processing
 * @author <a href="mailto:kevin@electricanvil.com">Kevin A. Smith</a>
 * @version 0.1
 * @since 2003/05/08
 * @see stirred.handler.Handler
 */
public interface LineHandler extends Handler {
    /**
     * Sets the string that will be used as the line delimiter.
     * This value can be changed at run time.
     * @param delim line delimiter
     * @since 2003/05/08
     */
    public void setLineDelimiter(String delim);
    
    /**
     * Gets the line delimiter in use.
     * @return line delimiter in use
     * @since 2003/05/08
     */
    public String getLineDelimiter();
    
    /**
     * Notification when data is received.
     * This method will only be called when the handler is in data mode.
     * @param transport data payload passed in from FactoryEnabled
     * @since 2003/05/08
     * @see stirred.base.FactoryEnabled
     * @see stirred.server.NetworkServer
     * @see stirred.transport.Transport
     */
    public void dataReceived(Transport transport);
    
    /**
     * Notification when a complete line has been received
     * This method will only called when the handler is not in data mode.
     * @param line line of text received
     * @param transport data payload passed in from listener
     * @since 2003/05/08
     * @see stirred.base.FactoryEnabled
     * @see stirred.server.NetworkServer
     * @see stirred.transport.Transport
     */
    public void lineReceived(String line, Transport transport);
    
    /**
     * Sets/unsets data mode via boolean
     * @param flag boolean describing the current data mode
     * @since 2003/05/08
     */
    public void setDataMode(boolean flag);
    
    /**
     * Gets the current data mode setting
     * @return boolean describing the current data mode
     * @since 2003/05/08
     */
    public boolean getDataMode();
}
