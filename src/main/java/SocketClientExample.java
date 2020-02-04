import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketClientExample {
    private final static String IPTOLISTEN = "192.168.31.223";

    public static void main(String[] args) throws IOException, InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    startClient();
                } catch (IOException e) {
                  throw   new RuntimeException(e);
                } catch (InterruptedException e) {
                 throw    new RuntimeException(e);
                }

            }
        }).start();
    }
 
    public static void startClient() throws IOException, InterruptedException {
        InetSocketAddress hostAddress = new InetSocketAddress(IPTOLISTEN , 8090);
        SocketChannel client = SocketChannel.open(hostAddress);
 
        System.out.println("Client... started");
        
        String threadName = Thread.currentThread().getName();
 
        // Send messages to server
        String [] messages = new String [] 
        		{"woqunimalegebidddddddd","seconde"};
 
        for (int i = 0; i < messages.length; i++) {
            byte [] message = new String(messages [i]).getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(message);
            client.write(buffer);
            System.out.println(messages [i]);
            buffer.clear();
//            Thread.sleep(5000);
        }
        client.close();            
    }
}

