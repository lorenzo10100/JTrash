package JTrash.View;

import JTrash.Controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;

/**
 * Classe che si occupa della gestione del login.
 */
public class Login extends Observable {
    private JFrame frame;
    private JPanel panel;
    private JButton conferma;
    private JTextField  scelta;
    private JLabel nome, password;
    private JPasswordField pwd;

    private GridBagConstraints gbc;

    private LoginController loginController;


    /**
     * Costruttore della classe Login.
     * All'interno vi e' la creazione della finestra di login e la gestione dei vari componenti grafici.
     *
     * Inoltre vengono aggiunti i listener per ogni bottone.
     *
     * la variabile logged viene utilizzata per capire se l'utente ha effettuato il login.
     * Se logged e' true l'utente ha effettuato il login, viene quindi fatta una query al database per verificare
     * se l'utente e' registrato e verificare se e' loggato o meno.
     */
    public Login(){
        frame = new JFrame("JTrash");
        panel = new JPanel(new GridBagLayout());
        nome = new JLabel("Inserisci il tuo nome:");
        scelta = new JTextField();
        password = new JLabel("Inserisci la tua password:");
        pwd = new JPasswordField();
        conferma = new JButton("Conferma");
        gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        panel.add(new JLabel("<html><h1><strong><i>Login</i></strong></h1><hr></html>"), gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginController = new LoginController(this);
        pwd.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isWhitespace(c)) {
                    e.consume();
                }
            }
        });

        conferma.addActionListener(e->{
            loginController.conferma(scelta.getText(), new String(pwd.getPassword()));
        });

        panel.add(nome, gbc);
        panel.add(scelta, gbc);
        panel.add(Box.createRigidArea(new Dimension(0, 10)), gbc);
        panel.add(password, gbc);
        panel.add(pwd, gbc);
        panel.add(Box.createRigidArea(new Dimension(0, 10)), gbc);
        panel.add(conferma, gbc);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        frame.add(panel);
        frame.pack();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void dispose(){
        frame.dispose();
    }
}
