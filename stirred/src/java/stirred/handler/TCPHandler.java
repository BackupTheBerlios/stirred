/*
 * This work is licensed under the Creative Commons Attribution License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/1.0/ 
 * or send a letter to Creative Commons, 559 Nathan Abbott Way, Stanford, California 94305, USA. 
 */
package stirred.handler;


/**
 * Encapsulates the connection-oriented nature of TCP operations
 * @author <a href="mailto:kevin@electricanvil.com">Kevin A. Smith</a>
 * @version 0.1
 * @since 2003/05/08

 */
public interface TCPHandler {
    
    /**
     * Called by the listener whenever a client tries to connect
     * @param address dotted IP address of the client
     * @return boolean <code>true</code> to allow connection, <code>false</code> to disallow
     * @since 2003/05/08
     * @see stirred.handler.DefaultTCPHandler
     */
    public boolean openConnection(String address);
    
    /**
     * Called by the listener when a client initiates a disconnect
     * @since 2003/05/08
     * @see stirred.handler.DefaultTCPHandler
     */
    public void closeConnection();
}
