package SI400.JavaChat.GUI;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;

public class HelpDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    public HelpDialog(JFrame parent) {

        super(parent, "Ajuda", true);

        setResizable(false); // Rezising is disabled

        createUI();
    }

    private void createUI() {

        setLayout(new FlowLayout());
        
        String labelText = "<html>" +
                "<h1>Bem vindo!</h1>" +
                "O programa conecta e permite a comunicação entre dois computadores<br><br>" +
                "Para fazer a conexão com o outro computador vá para:<br>" +
                "Arquivo -> Conectar -> Insira o IP e a PORTA para conectar" +
                "</html>";


        JLabel label = new JLabel(labelText);
        add(label);

        setSize(600, 200);
        setLocationRelativeTo(null);
    }
}

