/*
 * This work is licensed under the Creative Commons Attribution License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/1.0/ 
 * or send a letter to Creative Commons, 559 Nathan Abbott Way, Stanford, California 94305, USA. 
 */
package stirred.protocol;


/**
 * Defines the mechanism used to create new <code>Protocol</code>s.
 * @author <a href="mailto:kevin@electricanvil.com">Kevin A. Smith</a>
 * @version 0.1
 * @since 2003/05/12
 * @see stirred.protocol.Protocol
 */
public interface ProtocolFactory {
    /**
     * Method used to create and initialize <code>Protocol</code> objects.
     * @return newly create <code>Protocol</code>
     */
    Protocol createProtocol();
    
    /**
     * Method used to destroy <code>Protocol</code> objects.
     * Most likely a no-op.
     * @param protocol - the protocol to be destroyed.
     */
    void destroyProtocol(Protocol protocol);
}
