package SI400.JavaChat.GUI;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class AboutDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    public AboutDialog(JFrame parent) {
        
        super(parent, "Sobre", true);

        setResizable(false); // Rezising is disabled

        createUI();
    }

    private void createUI() {

        setLayout(new FlowLayout());

        String labelText = "<html>" +
                "<head><style>" +
                "body" +
                "</style></head>" +
                "<h1>Versão 1.0 do programa</h1><br>" +
                "<h2>Criado por:</h2>" +
                "Brian Oliveira 260757" + "<br>" +
                "Caio Piteli 234451" + "<br>" +
                "Carolina Quiterio 257041" + "<br>" +
                "Gabriel Sales 248008" + "<br>" +
                "Rafaella Rodrigues 243597" +
                "</html>";


        JLabel label = new JLabel(labelText);

        add(label, BorderLayout.CENTER);

        setSize(350, 250);
        setLocationRelativeTo(null);
    }
}
