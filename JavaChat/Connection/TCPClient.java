
package SI400.JavaChat.Connection;

import java.io.*;
import java.net.*;



public class TCPClient {
    private Socket socket;
    private BufferedReader input;
    private DataOutputStream output;

    public TCPClient(String host, int port) {
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

    public void outputToServer() {
        String line = "";
        while (!line.equals(".")) {
            try {
                line = input.readLine();
                output.writeUTF(line);
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public void receiveText() {
        try {
            String line = "";
            while (!line.equals(".")) {

                line = Input.readUTF();
                System.out.println("Server >> " + line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendText(String text) {
        try {
            output.writeUTF(text);
            System.out.println("CLIENTE >> " + text);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendFile(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            // Send file name to the server
            output.writeUTF(new File(filePath).getName());

            // Send file content
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }

            System.out.println("File sent successfully.");
        } catch (IOException e) {
            System.out.println("Error sending file: " + e.getMessage());
        }
    }


}
