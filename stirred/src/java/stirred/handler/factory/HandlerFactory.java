/*
 * This work is licensed under the Creative Commons Attribution License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/1.0/ 
 * or send a letter to Creative Commons, 559 Nathan Abbott Way, Stanford, California 94305, USA. 
 */
package stirred.handler.factory;

import stirred.handler.Handler;

/**
 * Interface describing the factory mechanism for creating handlers
 * @author <a href="mailto:kevin@electricanvil.com">Kevin A. Smith</a>
 * @version 0.1
 * @since 2003/05/12
 * @see stirred.handler.Handler
 */
public interface HandlerFactory {
    public Handler createHandler();
    public void destroyHandler(Handler handler);
}
