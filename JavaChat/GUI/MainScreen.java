package SI400.JavaChat.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame {
    private JLabel statusLabel;
    private JTextArea conversationTextArea;
    private JTextField messageField;
    private JButton sendButton;

    private void chatArea() {
        JPanel chatPanel = new JPanel(new BorderLayout());
        conversationTextArea = new JTextArea();
        conversationTextArea.setEditable(false);
        conversationTextArea.setLineWrap(true);
        conversationTextArea.setWrapStyleWord(true);

        messageField = new JTextField(70);
        sendButton = new JButton("Enviar");

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();
                if (!message.isEmpty()) {
                    conversationTextArea.append("Você: " + message + "\n");
                    // Transferência de arquivos (Pessoa 4)
                    messageField.setText("");
                }
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


    public MainScreen() {
        setTitle("Chat Program");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenu fileMenu = new JMenu("Arquivo");
        JMenuItem connectItem = new JMenuItem("Conectar");
        connectItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConnectionDialog connectionDialog = new ConnectionDialog(MainScreen.this);
                connectionDialog.setVisible(true);
            }
        });
        fileMenu.add(connectItem);

        JMenuItem exitItem = new JMenuItem("Sair");
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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

        boolean connectionStatus = true;
        statusLabel = new JLabel("Status da conexão: " + connectionStatus, JLabel.CENTER);
        statusLabel.setLayout(new GridLayout(5, 10, 0, 2));
        add(statusLabel, BorderLayout.PAGE_END);

        setVisible(true);
    }
}