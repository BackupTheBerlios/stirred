/*
 * This work is licensed under the Creative Commons Attribution License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/1.0/ 
 * or send a letter to Creative Commons, 559 Nathan Abbott Way, Stanford, California 94305, USA. 
 */
package stirred.server;

import java.nio.channels.Channel;

/**
 * Interface for port-based, ie TCP or UDP, network servers. This interface is normally
 * combined with <code>stirred.base.FactoryEnabled</code> to create a fully-functioning
 * network server.
 * @author <a href="mailto:kevin@electricanvil.com">Kevin A. Smith</a>
 * @version 0.1
 * @since 2003/05/08
 * @see stirred.base.FactoryEnabled
 */
public interface NetworkServer {

    public static final String RUNNING = "run";
    public static final String STOPPING = "stopping";
    public static final String STOPPED = "stop";
    
    /**
     * Setter for the listening port
     * @param port the port the server users to listen for incoming connections.
     */
    void setPort(int port);
    
    /**
     * Getter for the listening port
     * @return the port the server uses to listen for incoming connections.
     */
    int getPort();
    void setAddress(String hostAddr);
    String getAddress();
    Channel getChannel();
}