/*
 * This work is licensed under the Creative Commons Attribution License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/1.0/ 
 * or send a letter to Creative Commons, 559 Nathan Abbott Way, Stanford, California 94305, USA. 
 */
package stirred.example;

import stirred.handler.LineHandler;
import stirred.protocol.Protocol;
import stirred.transport.Transport;

/**
 * @author <a href="mailto:kevin@electricanvil.com">Kevin A. Smith</a>
 */
public class ExampleProtocol implements Protocol {
    
    private LineHandler _handler;
    
    /* (non-Javadoc)
     * @see stirred.handler.LineHandler#lineReceived(java.lang.String, stirred.transport.Transport)
     */
    public void lineReceived(String line, Transport transport) {
        System.err.println(line.length());
        transport.closeConnection();
    }

    /* (non-Javadoc)
     * @see stirred.handler.LineHandler#dataReceived(stirred.transport.Transport)
     */
    public void dataReceived(Transport transport) {

    }

    /* (non-Javadoc)
     * @see stirred.protocol.Protocol#setHandler(stirred.handler.LineHandler)
     */
    public void setHandler(LineHandler handler) {
        _handler = handler;
    }

    /* (non-Javadoc)
     * @see stirred.protocol.Protocol#getHandler()
     */
    public LineHandler getHandler() {
        return _handler;
    }

	/* (non-Javadoc)
	 * @see stirred.protocol.Protocol#destroy()
	 */
	public void destroy() {
		
	}
}
