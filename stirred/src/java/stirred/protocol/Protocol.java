/*
 * This work is licensed under the Creative Commons Attribution License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/1.0/ 
 * or send a letter to Creative Commons, 559 Nathan Abbott Way, Stanford, California 94305, USA. 
 */
package stirred.protocol;

import stirred.handler.LineHandler;
import stirred.transport.Transport;

/**
 * Interface that defines the behavior expected of a protocol.
 * @author <a href="mailto:kevin@electricanvil.com">Kevin A. Smith</a>
 * @version 0.1
 * @since 2003/05/12
 * @see stirred.handler.Handler
 * @see stirred.handler.LineHandler
 */
public interface Protocol {
    /**
     * Callback method used to pass a <code>Handler</code> reference to a <code>Protocol</code>.
     * This is done so the Protocol can toggle things like dataMode, etc.
     * @param the <code>LineHandler</code> assigned to the <code>Protocol</code>
     */
    public void setHandler(LineHandler handler);
    
    /**
     * Getter method corresponding to <code>setHandler</code>
     * Added for symmetry
     * @return the <code>LineHandler</code> passed in to <code>setHandler</code>
     */
    public LineHandler getHandler();
    
    /**
     * Callback method used to notify the <code>Protocol</code> when
     * data has been received. This call is only trigged if the <code>LineHandler</code>
     * has been put in data mode via <code>LineHandler.setDataMode(true)</code>.
     * <param> transport - data conduit
     */
    public void dataReceived(Transport transport);
    
    /**
     * Callback method used to notify the <code>Protocol</code> when
     * a line of text has been received. The <code>Protocol</code> object
     * should set the line delimiter via <code>LineHandler.setLineDelimiter</code>
     * insure accurate line detection is performed.
     * @param line - line of text
     * @param transport - data conduit
     */
    public void lineReceived(String line, Transport transport);
    
    /**
     * Callback method used to notify the <code>Protocol</code> when to
     * destroy itself and initiate cleanup.
     */
    public void destroy();
}
