/*
 * This work is licensed under the Creative Commons Attribution License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/1.0/ 
 * or send a letter to Creative Commons, 559 Nathan Abbott Way, Stanford, California 94305, USA. 
 */
package stirred.protocol;

/**
 * Default implementation of a <code>ConfigurableProtocolFactory</code>.
 * Use this implementation unless you need to write one from scratch.
 * @author <a href="mailto:kevin@electricanvil.com">Kevin A. Smith</a>
 * @version 0.1
 * @since 2003/05/12
 * @see stirred.protocol.ConfigurableProtocolFactory
 */
public class DefaultProtocolFactory implements ConfigurableProtocolFactory {
    private Class _protocolClass;
    
    /* (non-Javadoc)
     * @see stirred.protocol.factory.ConfigurableProtocolFactory#setProtocolClassName(java.lang.String)
     */
    public void setProtocolClassName(String className) throws ClassNotFoundException {
        _protocolClass = getClass().getClassLoader().loadClass(className);
    }

    /* (non-Javadoc)
     * @see stirred.protocol.factory.ConfigurableProtocolFactory#getProtocolClassName()
     */
    public String getProtocolClassName() {
        String retval = null;
        if(_protocolClass != null)
            retval = _protocolClass.getName();
        return retval;
    }

    /* (non-Javadoc)
     * @see stirred.handler.factory.ProtocolFactory#createProtocol()
     */
    public Protocol createProtocol() {
        if(_protocolClass == null)
            throw new IllegalStateException("Protocol clas is not set");
        Protocol retval = null;
        try {
            retval = (Protocol) _protocolClass.newInstance();
        }
        catch(IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return retval;
    }

    /* (non-Javadoc)
     * @see stirred.protocol.ProtocolFactory#destroyProtocol(stirred.protocol.Protocol)
     */
    public void destroyProtocol(Protocol protocol) {

    }

}
