/*
 * This work is licensed under the Creative Commons Attribution License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/1.0/ 
 * or send a letter to Creative Commons, 559 Nathan Abbott Way, Stanford, California 94305, USA. 
 */
package stirred.util;

import stirred.transport.Transport;

/**
 * Models the association between a handler and its corresponding transport.
 * The handler is stored as a <code>java.lang.Object</code> since its type is 
 * unknown at compile time.
 * @author <a href="mailto:kevin@electricanvil.com">Kevin A. Smith</a>
 * @version 0.1
 * @since 2003/05/08
 */
public class HandlerAssociation {

    private Transport _transport;
    private Object _handler;
    
    public HandlerAssociation() {
    }

    public Transport getTransport() {
        return _transport;
    }

    public void setTransport(Transport transport) {
        _transport = transport;
    }

    public Object getHandler() {
        return _handler;
    }

    public void setHandler(Object object) {
        _handler = object;
    }

}
