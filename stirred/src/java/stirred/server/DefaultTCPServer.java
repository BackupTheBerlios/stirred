/*
 * This work is licensed under the Creative Commons Attribution License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/1.0/ 
 * or send a letter to Creative Commons, 559 Nathan Abbott Way, Stanford, California 94305, USA. 
 */
package stirred.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import stirred.base.FactoryEnabled;
import stirred.base.HandlerAssociation;
import stirred.handler.DefaultTCPHandler;
import stirred.handler.factory.HandlerFactory;
import stirred.protocol.Protocol;
import stirred.protocol.ProtocolFactory;
import stirred.transport.BufferedTransport;

/**
 * Implements a basic TCP server.
 * @author <a href="mailto:kevin@electricanvil.com">Kevin A. Smith</a>
 * @version 0.1
 * @since 2003/05/08
 */
public class DefaultTCPServer implements FactoryEnabled, NetworkServer {
    
    private int _port;
    private String _hostAddr = "127.0.0.1";
    private Selector _selector;
    private SelectionKey _key;
    private ServerSocketChannel _channel;
    private HandlerFactory _handlerFactory;
    private ProtocolFactory _protocolFactory;
    private String _state = NetworkServer.RUNNING;
    /* (non-Javadoc)
     * @see stirred.listeners.FactoryEnabled#setHandlerFactory(java.lang.Object)
     */
    public void setHandlerFactory(HandlerFactory factory) throws IllegalArgumentException {
        _handlerFactory = factory;
    }

    public HandlerFactory getHandlerFactory() {
        return _handlerFactory;
    }
    
    public void setPort(int port) {
        _port = port;
    }
    
    public int getPort() {
        return _port;
    }
    
    public void setAddress(String hostAddr) {
        _hostAddr = hostAddr;
    }
    
    public String getAddress() {
        return _hostAddr;
    }
    
    public void stop() {
        _state = NetworkServer.STOPPING;
    }


    public void run() {
        try {
            init();
            eventLoop();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    private void init() throws IOException {
        _selector = Selector.open();
        _channel = ServerSocketChannel.open();
        InetSocketAddress isa = new InetSocketAddress(_hostAddr, _port);
        _channel.socket().bind(isa);
        _channel.configureBlocking(false);
        _key = _channel.register(_selector, SelectionKey.OP_ACCEPT);
    }
    
    private void eventLoop() throws IOException {
        while(_state.equals(NetworkServer.RUNNING)) {
            if(_selector.select(100) > 0) {
                Iterator iter = _selector.selectedKeys().iterator();
                while(iter.hasNext()) {
                    SelectionKey current = (SelectionKey) iter.next();
                    iter.remove();
                    if(current.isAcceptable()) {
                        accept(current);
                    }
                    if(current.isReadable()) {
                        read(current);
                    }
                    if(current.isWritable()) {
                        write(current);
                    }
                }
            }
        }
        _state = NetworkServer.STOPPED;
    }
    
    private void accept(SelectionKey client) throws IOException {
        Protocol protocol = null;
        DefaultTCPHandler handler = (DefaultTCPHandler) _handlerFactory.createHandler();
        if(handler == null) {
            client.channel().close();
            return;
        }
        if(_protocolFactory != null) {
            protocol = _protocolFactory.createProtocol();
            if(protocol != null)
                handler.setProtocol(protocol);
        }
        
        ServerSocketChannel chan = (ServerSocketChannel) client.channel();
        // Have to accept the incoming connection so we can find out 
        // who is connecting and pass that on to the handler
        SocketChannel clientChannel = chan.accept();
        String clientAddress = obtainAddress(clientChannel);
        if(handler.openConnection(clientAddress)) {
            clientChannel.configureBlocking(false);
            HandlerAssociation assoc = new HandlerAssociation();
            assoc.setHandler(handler);
            assoc.setTransport(new BufferedTransport());
            SelectionKey k = clientChannel.register(client.selector(), 
                SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            k.attach(assoc);
        }
        else
            clientChannel.close();
    }
    
    private void read(SelectionKey client) throws IOException {
        HandlerAssociation assoc = (HandlerAssociation) client.attachment();
        DefaultTCPHandler handler = (DefaultTCPHandler) assoc.getHandler();
        SocketChannel chan = (SocketChannel) client.channel();
        BufferedTransport trans = (BufferedTransport) assoc.getTransport();
        if(trans.isClosed()) {
            chan.close();
            return;
        }
        trans.getBuffer().flip();
        trans.getBuffer().clear();
        if(chan.read(trans.getBuffer()) > -1)
            handler.process(trans);
        else {
            handler.closeConnection();
            assoc.getTransport().destroy();
        }
    }
    
    private void write(SelectionKey client) throws IOException {
        HandlerAssociation assoc = (HandlerAssociation) client.attachment();
        BufferedTransport trans = (BufferedTransport) assoc.getTransport();
        DefaultTCPHandler handler = (DefaultTCPHandler) assoc.getHandler();
        if(!trans.isEmpty()) {
            SocketChannel chan = (SocketChannel) client.channel();
            trans = (BufferedTransport) assoc.getTransport();
            ByteBuffer buffer = trans.getBuffer();
            buffer.flip();
            buffer.rewind();
            try {
                chan.write(buffer);
            }
            catch(IOException e) {
                handler.closeConnection();
                assoc.getTransport().destroy();
            }
            buffer.clear();
        }
        if(trans.isClosed()) {
            client.channel().close();
            return;
        }
    }
    
    private String obtainAddress(SocketChannel chan) {
        InetSocketAddress addr = (InetSocketAddress) chan.socket().getRemoteSocketAddress();
        return addr.getAddress().getHostAddress();
    }

    /* (non-Javadoc)
     * @see stirred.base.FactoryEnabled#setProtocolFactory(stirred.protocol.ProtocolFactory)
     */
    public void setProtocolFactory(ProtocolFactory factory) throws IllegalArgumentException {
        _protocolFactory = factory;
    }

    /* (non-Javadoc)
     * @see stirred.base.FactoryEnabled#getProtocolFactory()
     */
    public ProtocolFactory getProtocolFactory() {
        return _protocolFactory;
    }

    /* (non-Javadoc)
     * @see stirred.server.NetworkServer#getChannel()
     */
    public Channel getChannel() {
        return _channel;
    }
}
