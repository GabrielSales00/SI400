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
            System.out.println("Esperando um cliete.");
            //ficará preso aqui até aceitar
            this.socket = serverSocket.accept();
            System.out.println("Cliente aceitou a conexão.");
            System.out.println("Escreva uma mensagem qualquer. Aperte '.' para encerrar a conexão.");
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    //recebe input do clientSocket
    public void inputFromClient() {
        try {
            this.inputStream =  new DataInputStream(
                new BufferedInputStream(this.socket.getInputStream())
            );

            String line = "";
            while(!line.equals(".")) {
                try
                {
                    line = this.inputStream.readUTF();
                    System.out.println("Cliente >> " + line);
 
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

    public void sendText(String text) {
        try {
            this.outputStream.writeUTF(text);
            System.out.println("Server >> " + text);
        } catch (IOException e) {
            System.out.println(e.getMessage());
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

    public void receiveFile(String savePath) {
        try {
            String fileName = inputStream.readUTF();
            System.out.println("Receiving file: " + fileName);

            try (FileOutputStream fileOutputStream = new FileOutputStream(new File(savePath, fileName))) {
                byte[] buffer = new byte[1024];
                int bytesRead;

                // Receive file content
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                System.out.println("File received successfully.");
            }
        } catch (IOException e) {
            System.out.println("Error receiving file: " + e.getMessage());
        }
    }

}
