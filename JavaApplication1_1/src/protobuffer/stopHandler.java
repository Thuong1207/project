package protobuffer;

import javaapplication1.protobuffer.HelloHandler;
import org.eclipse.jetty.server.Server;
import thuongdc.com.example.tutoria.AddressBookProtos;

public class stopHandler {

    public static void main(String[] args) throws Exception {
        Server server = new Server(9999);
        server.stop();
         
    }
}
