/*
 * This work is licensed under the Creative Commons Attribution License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/1.0/ 
 * or send a letter to Creative Commons, 559 Nathan Abbott Way, Stanford, California 94305, USA. 
 */
package stirred.handler;

import stirred.protocol.Protocol;
import stirred.transport.Transport;

/**
 * Base interface for all Handlers
 * <code>Handler</code> defines basic behaviors common across all Handlers
 * @author <a href="mailto:kevin@electricanvil.com">Kevin A. Smith</a>
 * @version 0.1
 * @since 2003/05/08
 */

public interface Handler {
    
    /**
     * Setter for the Protocol object.
     * @param protocol the target protocol for all callbacks.
     * @see stirred.protocol.Protocol
     * @since 2003/05/08
     */
    public void setProtocol(Protocol protocol);
    
    
    /**
     * Getter for the protocol object.
     * @return the target protocol for all callbacks used by a Handler
     * @see stirred.protocol.Protocol
     * @since 2003/05/08
     */
    public Protocol getProtocol();
    
    
    /**
     * Central point for notification of all listener events.
     * This method is called whenever a listener has data that 
     * needs to be handled.
     * @param transport data payload passed in from the listener
     * @see stirred.util.FactoryEnabled
     * @see stirred.server.NetworkServer
     * @since 2003/05/08
     */
    public void process(Transport transport);
}
