/*
 * This work is licensed under the Creative Commons Attribution License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/1.0/ 
 * or send a letter to Creative Commons, 559 Nathan Abbott Way, Stanford, California 94305, USA. 
 */
package stirred.transport;

/**
 * Abstracts the write operation between Handlers and Listeners.
 * @author <a href="mailto:kevin@electricanvil.com">Kevin A. Smith</a>
 * @see stirred.handler.Handler
 * @see stirred.base.FactoryEnabled
 */
public interface Transport {
    public void writeString(String text) throws TransportWriteException;
    public void writeData(byte[] data) throws TransportWriteException;
    public void writeData(byte[] data, int start, int end) throws TransportWriteException;
    public String readString();
    public byte[] readData();
    public boolean isEmpty();
    public int remaining();
    public void destroy();
    public void closeConnection();
}
