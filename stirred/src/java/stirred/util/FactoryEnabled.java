/*
 * This work is licensed under the Creative Commons Attribution License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/1.0/ 
 * or send a letter to Creative Commons, 559 Nathan Abbott Way, Stanford, California 94305, USA. 
 */
package stirred.util;

import stirred.handler.factory.HandlerFactory;
import stirred.protocol.ProtocolFactory;

/**
 * Defines the basic contract between various listener implementations
 * and protocols
 * @author <a href="mailto:kevin@electricanvil.com">Kevin A. Smith</a>
 * @version 0.1
 * @since 2003/05/08
 */
public interface FactoryEnabled extends Runnable {
    /*
     * Sets the handler factory to be used for this listener
     * @throws IllegalArgumentException - Denotes when the passed in
     * factory is not supported by the listener implementation
     */    
    public void setHandlerFactory(HandlerFactory factory) throws IllegalArgumentException;
    
    /**
     * Returns the factory set in <code>setHandlerFactory</code>
     */
    public HandlerFactory getHandlerFactory();

    /*
      * Sets the protocol factory to be used for this listener
      * @throws IllegalArgumentException - Denotes when the passed in
      * factory is not supported by the listener implementation
      */    
    public void setProtocolFactory(ProtocolFactory factory) throws IllegalArgumentException;
    
    /**
     * Returns the factory set in <code>setProtocolFactory</code>
     */
    public ProtocolFactory getProtocolFactory();
    
    /**
     * Signals the FactoryEnabled to stop listening and gracefully exit from its
     * main listening loop
     */
    public void stop();
    
}
