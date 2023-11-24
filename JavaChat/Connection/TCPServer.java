import java.io.*;
import java.net.*;

package ChatJava.Connection;


public class TCPServer {
    private int port;
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream inputStream;


    public void TCPconnection(int port) {
        try {
            this.port = port;
            this.serverSocket = new ServerSocket(this.port);
            System.out.println("Esperando um cliete.");
            this.socket = serverSocket.accept();
            System.out.println("Cliente aceitou a conexão.");
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    //recebe input do clientSocket
    public void inputData() {
        try {
            this.inputStream =  new DataInputStream(
                new BufferedInputStream(this.socket.getInputStream())
            );

            String line = "";
            while(!line.equals("\n")) {
                try
                {
                    line = this.inputStream.readUTF();
                    System.out.println(line);
 
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public void closeConnection() {
        try {
            System.out.println("Fechando a conexão");
            this.socket.close();
            inputStream.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

}