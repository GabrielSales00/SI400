import java.io.*;
import java.net.*;

package ChatJava.Connection;


public class TCPClient {
    private Socket socket;
    private BufferedReader input;
    private DataOutputStream output;

    public void TCPClient(String host, int port) {
        try {
            socket = new Socket(host, port);
            input = new BufferedReader(new InputStreamReader(System.in));
            output = new DataOutputStream(socket.getOutputStream());
            System.out.println("Connected!");
        }
        catch (UnknownHostException u) {
            System.out.println(u);
            return;
        }
        catch (IOException i) {
            System.out.println(i);
            return;
        }
    }

    public void takesInput() {
        String line = "";
        while (!line.equals("\n")) {
            try {
                line = input.readLine();
                output.writeUTF(line);
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }
    }


}
