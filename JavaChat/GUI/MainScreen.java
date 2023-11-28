package SI400.JavaChat.GUI;

import SI400.JavaChat.Connection.TCPClient;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainScreen extends JFrame {
    private JLabel statusLabel;
    private JTextArea conversationTextArea;
    private JTextField messageField;
    private JButton sendButton;
    
    private String serverIP;
    private int serverPort;
    private TCPClient tcpClient;

    private void chatArea() {
        JPanel chatPanel = new JPanel(new BorderLayout());
        conversationTextArea = new JTextArea();
        conversationTextArea.setEditable(false);
        conversationTextArea.setLineWrap(true);
        conversationTextArea.setWrapStyleWord(true);

        messageField = new JTextField(70);
        sendButton = new JButton("Enviar");

        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        chatPanel.add(new JScrollPane(conversationTextArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Mensagem: "));
        inputPanel.add(messageField);
        inputPanel.add(sendButton);

        chatPanel.add(inputPanel, BorderLayout.SOUTH);

        add(chatPanel);
    }

    private void showConnectionDialog() {
        ConnectionDialog connectionDialog = new ConnectionDialog(this);
        connectionDialog.setVisible(true);
        tcpClient = connectionDialog.getClientConnection();

        if (tcpClient.isConnected()) {
            statusLabel.setText("Status: Conectado ao servidor");
            new Thread(() -> tcpClient.receiveMessages(this::appendMessage)).start();
        } else {
            statusLabel.setText("Status: Erro de conexão");
            JOptionPane.showMessageDialog(this, "Não foi possível estabelecer a conexão. Verifique o endereço IP e a porta.", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void sendMessage() {
        if (tcpClient != null && tcpClient.isConnected()) {
            String message = messageField.getText();
            if (!message.isEmpty()) {
                tcpClient.sendMessage(message);
                appendMessage("Você: " + message);
                messageField.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Você não está conectado ao servidor.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void appendMessage(String message) {
        SwingUtilities.invokeLater(() -> conversationTextArea.append(message + "\n"));
    }


    public MainScreen() {
        setTitle("Chat Program");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final boolean[] connectionStatus = {false};
        
        JMenu fileMenu = new JMenu("Arquivo");
        JMenuItem connectItem = new JMenuItem("Conectar");
        connectItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                showConnectionDialog();
            }
        });
        fileMenu.add(connectItem);

        JMenuItem exitItem = new JMenuItem("Sair");
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Adicione o código para fechar a conexão antes de sair
                if (tcpClient != null) {
                    tcpClient.closeConnection();
                }
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);

        JMenu helpMenu = new JMenu("Ajuda");
        JMenuItem helpItem = new JMenuItem("Ajuda");
        helpItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HelpDialog helpDialog = new HelpDialog(MainScreen.this);
                helpDialog.setVisible(true);
            }
        });
        helpMenu.add(helpItem);

        JMenuItem aboutItem = new JMenuItem("Sobre");
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AboutDialog aboutDialog = new AboutDialog(MainScreen.this);
                aboutDialog.setVisible(true);
            }
        });
        helpMenu.add(aboutItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        chatArea();

        statusLabel = new JLabel("Status da conexão: " + connectionStatus[0], JLabel.CENTER);
        statusLabel.setLayout(new GridLayout(5, 10, 0, 2));
        add(statusLabel, BorderLayout.PAGE_END);

        setVisible(true);
    }
}