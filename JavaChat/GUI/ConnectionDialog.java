package SI400.JavaChat.GUI;

import SI400.JavaChat.Connection.TCPClient;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectionDialog extends JDialog {

    private static final long serialVersionUID = 942124112302349846L;                                    

    private JTextField fieldIP;
    private JTextField fieldPort;
    private JButton connectButton;

    private boolean isConnected;  // Adicionada para verificar se a conexão foi estabelecida
    private TCPClient clientConnection;

    public ConnectionDialog(Frame parent) {
        super(parent, "Conexão", true);
        setResizable(false);

        createUI();
        isConnected = false;
    }
    private void createUI() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel ipLabel = new JLabel("Endereço IP:");
        fieldIP = new JTextField(15);

        JLabel portLabel = new JLabel("Porta:");
        fieldPort = new JTextField(8);

        connectButton = new JButton("Conectar");
        connectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleConnection();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(ipLabel, gbc);

        gbc.gridx = 1;
        panel.add(fieldIP, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(portLabel, gbc);

        gbc.gridx = 1;
        panel.add(fieldPort, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(connectButton, gbc);

        getContentPane().add(panel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private void handleConnection() {
        String ipAddress = fieldIP.getText();
        int port;

        try {
            port = Integer.parseInt(fieldPort.getText());
            clientConnection = new TCPClient(ipAddress, port);
            clientConnection.connect();
            setVisible(false);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número válido para a porta.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Não foi possível estabelecer a conexão. Verifique o endereço IP e a porta.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public TCPClient getClientConnection() {
        return clientConnection;
    }
}



