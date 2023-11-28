package SI400.JavaChat.Connection;

import java.io.*;
import java.net.*;

public class TCPClient {
    private Socket socket;
    private BufferedReader input;
    private DataOutputStream output;
    private PrintWriter writer;
    private boolean connected = false;
    private String ipAddress;
    private int port;

    public TCPClient(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public void connect() throws IOException {
        try {socket = new Socket(ipAddress, port);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
        connected = true;
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println("Erro ao conectar ao servidor: " + i.getMessage());
        }
    }

    public void receiveMessages(MessageHandler messageHandler) {
        try {
            String message;
            while (connected && (message = input.readLine()) != null) {
                messageHandler.handleMessage("Servidor: " + message);
            }
        } catch (IOException e) {
            connected = false;
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        if (connected) {
            writer.println(message);
        }
    }

    public void closeConnection() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
    public void outputToServer() {
        String line = "";
        while (!line.equals(".")) {
            try {
                line = input.readLine();
                output.writeUTF(line);
            }
            connected = false; // added
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public boolean isConnected() {
        return connected;
    }
    
    public interface MessageHandler {
        void handleMessage(String message);
    }
}
