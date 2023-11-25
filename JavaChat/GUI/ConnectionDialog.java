package SI400.JavaChat.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectionDialog extends JDialog {

    private static final long serialVersionUID = 942124112302349846L;                                    

    private JTextField fieldIP;
    private JTextField fieldPort;

    public ConnectionDialog(Frame parent) {
        super(parent, "Conexão", true);
        setResizable(false);

        createUI();
    }

    private void createUI() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel ipLabel = new JLabel("Endereço IP:");
        fieldIP = new JTextField(15); //Setting the initial size of the field (just as a tip to how many characters should be there)

        JLabel portLabel = new JLabel("Porta:");
        fieldPort = new JTextField(8); //Setting the initial size of the field (just as a tip to how many characters should be there)

        JButton connectButton = new JButton("Conectar");
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
        try {
            String ipAddress = fieldIP.getText();
            int port = Integer.parseInt(fieldPort.getText());
            
            // Lógica para lidar com a conexão aqui
            
            // Precisa dar uma confirmação caso funcionou a conexão
            JOptionPane.showMessageDialog(this, "Conexão estabelecida com sucesso!", "Conexão", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número válido para a porta.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}



