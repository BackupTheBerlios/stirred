/*
 * This work is licensed under the Creative Commons Attribution License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/1.0/ 
 * or send a letter to Creative Commons, 559 Nathan Abbott Way, Stanford, California 94305, USA. 
 */
package stirred.transport;

/**
 * Thrown whenever an error occurs writing to a <code>Transport</code>.
 * @author <a href="mailto:kevin@electricanvil.com">Kevin A. Smith</a>
 * @version 0.1
 * @since 2003/05/12
 * @see stirred.transport.Transport
 */
public class TransportWriteException extends Exception {

    /**
     * 
     */
    public TransportWriteException() {
        super();
    }

    /**
     * @param arg0
     */
    public TransportWriteException(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public TransportWriteException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * @param arg0
     */
    public TransportWriteException(Throwable arg0) {
        super(arg0);
    }

}
