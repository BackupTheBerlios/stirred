/*
 * This work is licensed under the Creative Commons Attribution License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/1.0/ 
 * or send a letter to Creative Commons, 559 Nathan Abbott Way, Stanford, California 94305, USA. 
 */
package stirred.handler.factory;

/**
 * Not really sure if this is needed, but when in doubt, write more code :)
 * @author <a href="mailto:kevin@electricanvil.com">Kevin A. Smith</a>
 * @version 0.1
 * @since 2003/05/12
 */
public interface ConfigurableHandlerFactory extends HandlerFactory {
    void setHandlerClassName(String className) throws ClassNotFoundException;
    String getHandlerClassName();
}
