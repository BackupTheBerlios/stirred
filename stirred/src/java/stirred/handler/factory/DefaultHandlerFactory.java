/*
 * This work is licensed under the Creative Commons Attribution License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/1.0/ 
 * or send a letter to Creative Commons, 559 Nathan Abbott Way, Stanford, California 94305, USA. 
 */
package stirred.handler.factory;

import stirred.handler.Handler;

/**
 * The Swiss Army Knife of Handler factories
 * @author <a href="mailto:kevin@electricanvil.com">Kevin A. Smith</a>
 * @version 0.1
 * @since 2003/05/12
 * @see stirred.handler.factory.ConfigurableHandlerFactory
 */
public class DefaultHandlerFactory implements ConfigurableHandlerFactory {
    
    private Class _handlerClass;
    

    /* (non-Javadoc)
     * @see stirred.handler.factory.ConfigurableHandlerFactory#setHandlerClassName(java.lang.String)
     */
    public void setHandlerClassName(String className) throws ClassNotFoundException {
        _handlerClass = getClass().getClassLoader().loadClass(className);
    }

    /* (non-Javadoc)
     * @see stirred.handler.factory.ConfigurableHandlerFactory#getHandlerClassName()
     */
    public String getHandlerClassName() {
        String retval = null;
        if(_handlerClass != null)
            retval = _handlerClass.getName();
        return retval;
    }



    /* (non-Javadoc)
     * @see stirred.handler.factory.HandlerFactory#createHandler()
     */
    public Handler createHandler() {
        Handler retval = null;
        try {
            retval = (Handler) _handlerClass.newInstance();
        }
        catch(InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        
        return retval;
    }

    /* (non-Javadoc)
     * @see stirred.handler.factory.HandlerFactory#destroyHandler(stirred.handler.Handler)
     */
    public void destroyHandler(Handler handler) {
    }

}
