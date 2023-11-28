
package SI400.JavaChat.Connection;

import java.io.*;
import java.net.*;


public class TCPtest{
    public static void main(String[] args) {
        // port aleatório
        int port = 11111;

        Thread serverThread = new Thread(() -> {
            TCPServer tcpServer = new TCPServer(port);
            tcpServer.inputFromClient(); 
            tcpServer.closeConnection();
        });
        serverThread.start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        TCPClient tcpClient = new TCPClient("localhost", port);
        tcpClient.outputToServer();


        try {
            serverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
