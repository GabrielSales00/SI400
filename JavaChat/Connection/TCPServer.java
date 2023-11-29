package SI400.JavaChat.Connection;

import java.io.*;
import java.net.*;

public class TCPServer {
    private int port;
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;


    public TCPServer(int port) {
        try {
            this.port = port;
            this.serverSocket = new ServerSocket(this.port);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public void awaitConnection() {
            System.out.println("Esperando um cliete.");
            this.socket = serverSocket.accept();
            System.out.println("Cliente aceitou a conexão.");
            this.outputStream = new DataOutputStream(
                    new BufferedOutputStream(this.socket.getOutputStream())
            );
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

    public void sendData(String message) {
        try {
            outputStream.writeUTF(message);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
