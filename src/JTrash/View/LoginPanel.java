package JTrash.View;

import javax.swing.*;
import java.awt.*;

/**
 * Questa classe rappresenta il pannello di login dell'applicazione.
 * Consente all'utente di inserire il nome utente e la password per effettuare il login o registrarsi.
 */
public class LoginPanel extends JPanel {
    private JTextField username;
    private JPasswordField password;
    private JButton login, register;
    private JLabel nome, pass;

    /**
     * Costruttore della classe LoginPanel.
     * Crea il pannello di login con campi di inserimento per nome utente e password, insieme a pulsanti per il login e la registrazione.
     */
    public LoginPanel() {
        nome = new JLabel("Inserisci il tuo nome:");
        pass = new JLabel("Inserisci la tua password:");
        username = new JTextField();
        password = new JPasswordField();
        login = new JButton("Login");
        register = new JButton("Registrati");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        add(new JLabel("<html><h1><strong><i>Login</i></strong></h1><hr></html>"), gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.insets = new Insets(5, 5, 5, 5);

        add(nome, gbc);
        add(username, gbc);
        add(pass, gbc);
        add(password, gbc);
        add(login);
        add(register);
    }

    /**
     * Restituisce il campo di inserimento del nome utente.
     *
     * @return il campo di inserimento del nome utente
     */
    public JTextField getUsername() {
        return username;
    }

    /**
     * Imposta il campo di inserimento del nome utente.
     *
     * @param username il campo di inserimento del nome utente da impostare
     */
    public void setUsername(JTextField username) {
        this.username = username;
    }

    /**
     * Restituisce il campo di inserimento della password.
     *
     * @return il campo di inserimento della password
     */
    public JPasswordField getPassword() {
        return password;
    }

    /**
     * Imposta il campo di inserimento della password.
     *
     * @param password il campo di inserimento della password da impostare
     */
    public void setPassword(JPasswordField password) {
        this.password = password;
    }

    /**
     * Restituisce il pulsante per effettuare il login.
     *
     * @return il pulsante per effettuare il login
     */
    public JButton getLogin() {
        return login;
    }

    /**
     * Restituisce il pulsante per effettuare la registrazione.
     *
     * @return il pulsante per effettuare la registrazione
     */
    public JButton getRegister() {
        return register;
    }
}
