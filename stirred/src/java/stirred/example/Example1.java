/*
 * This work is licensed under the Creative Commons Attribution License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by/1.0/ 
 * or send a letter to Creative Commons, 559 Nathan Abbott Way, Stanford, California 94305, USA. 
 */
package stirred.example;

import stirred.handler.factory.DefaultHandlerFactory;
import stirred.protocol.DefaultProtocolFactory;
import stirred.server.DefaultTCPServer;

/**
 * @author <a href="mailto:kevin@electricanvil.com">Kevin A. Smith</a>
 */
public class Example1 {

    public static void main(String[] argv) throws Exception {
        DefaultTCPServer listener = new DefaultTCPServer();
        DefaultHandlerFactory handlerFactory = new DefaultHandlerFactory();
        handlerFactory.setHandlerClassName("stirred.handler.DefaultTCPHandler");
        DefaultProtocolFactory protocolFactory = new DefaultProtocolFactory();
        protocolFactory.setProtocolClassName("stirred.example.ExampleProtocol");
        listener.setHandlerFactory(handlerFactory);
        listener.setProtocolFactory(protocolFactory);
        listener.setPort(9090);
        listener.run();
    }

}
