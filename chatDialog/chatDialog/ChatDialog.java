package SI400;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatDialog extends JFrame {

    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;

    public ChatDialog() {
        setTitle("Chat entre Máquinas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);

        initComponents();
    }

    private void initComponents() {
        JPanel chatPanel = new JPanel(new BorderLayout());
        chatArea = new JTextArea();
        chatArea.setEditable(false);

        messageField = new JTextField(20);
        sendButton = new JButton("Enviar");

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();
                if (!message.isEmpty()) {
                    chatArea.append("Você: " + message + "\n");
                    // Transferência de arquivos (Pessoa 4)
                    messageField.setText("");
                }
            }
        });

        chatPanel.add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Mensagem: "));
        inputPanel.add(messageField);
        inputPanel.add(sendButton);

        chatPanel.add(inputPanel, BorderLayout.SOUTH);

        add(chatPanel);
    }

    private boolean verifyConnection() {
        // Realizar a verificação de conexao entre as máquinas (Pessoa 2)
        boolean connectionResult = true;
        return connectionResult;
    }

    private void showErrorMessage() {
        JOptionPane.showMessageDialog(this, "Não foi possível realizar a conexão.", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ChatDialog dialog = new ChatDialog();
                dialog.setVisible(true);

                while (!dialog.verifyConnection()) {
                    dialog.showErrorMessage();
                }
            }
        });
    }
}
