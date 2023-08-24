package JTrash.Controller;

import JTrash.Model.Mano;
import JTrash.Model.Utente;
import JTrash.View.AvatarPanel;
import JTrash.View.LoginPanel;
import JTrash.View.MainFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.io.File;
import java.io.IOException;


/**
 * Questa classe rappresenta il controller per la gestione del login e della registrazione.
 * Si occupa di interagire con il pannello di login, il frame principale, il controller principale
 * e il controller del gioco per effettuare l'accesso o la registrazione degli utenti.
 */
public class LoginController {

    private GameController gameController;
    private Controller controller;
    private LoginPanel loginPanel;
    private MainFrame mainFrame;


    /**
     * Costruttore della classe LoginController.
     *
     * @param gameController il controller del gioco
     * @param controller     database dell'applicazione
     * @param loginPanel     il pannello di login
     * @param mainFrame      il frame principale
     */
    public LoginController(GameController gameController, Controller controller, LoginPanel loginPanel, MainFrame mainFrame) {
        this.gameController = gameController;
        this.controller = controller;
        this.loginPanel = loginPanel;
        this.mainFrame = mainFrame;
    }

    /**
     * Inizializza il controller del login.
     * Imposta il pannello di login nel frame principale e gestisce le azioni dei pulsanti.
     */
    private void initController() {
        mainFrame.setLoginPanel(loginPanel);
        mainFrame.switchToLogin();
        loginPanel.getPassword().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isWhitespace(c))
                    e.consume();
            }
        });
        loginPanel.getLogin().addActionListener(e -> login());
        loginPanel.getRegister().addActionListener(e -> register());
    }

    /**
     * Effettua l'accesso dell'utente.
     * Verifica le credenziali dell'utente, carica il database, esegue il login se le credenziali sono valide
     * e passa alla schermata di selezione dell'avatar.
     */
    private void login() {
        String username = getInputUsername();
        String password = getInputPassword();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Inserisci username e password!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File databaseFile = new File("database.ser");

        if (!databaseFile.exists()) {
            // Il file non esiste, potresti gestire questa situazione se necessario
            // Ad esempio, mostrare un messaggio per informare l'utente di registrarsi prima di effettuare il login
            JOptionPane.showMessageDialog(null, "Database non trovato, devi prima registrarti!", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            controller.carica(databaseFile);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Errore nel caricamento del file, devi prima registrarti!", "Errore", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return;
        }

        Utente user = controller.findUtente(username, password);

        if (user != null) {
            JOptionPane.showMessageDialog(null, "Login effettuato!", "Info", JOptionPane.INFORMATION_MESSAGE);
            user.setMano(new Mano());
            gameController.setUser(user);
            new AvatarController(user, new AvatarPanel(), mainFrame).init();
            mainFrame.switchToAvatar();
        } else
            JOptionPane.showMessageDialog(null, "Devi prima registrarti!", "Errore", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Registra un nuovo utente.
     * Registra un nuovo utente nel database, salva il database su file e passa alla schermata di selezione dell'avatar.
     */
    private void register() {
        String username = getInputUsername();
        String password = getInputPassword();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Inserisci username e password!", "Errore", JOptionPane.ERROR_MESSAGE);
        } else {
            File databaseFile = new File("database.ser");
            controller.addUtente(username, password);
            try {
                controller.salva(databaseFile);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Errore nel salvataggio del file!", "Errore", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
                return;
            }
            JOptionPane.showMessageDialog(null, "Registrazione effettuata!", "Info", JOptionPane.INFORMATION_MESSAGE);

            mainFrame.switchToAvatar();
        }
    }

    /**
     * Ottiene l'input dell'username senza spazi.
     *
     * @return l'username inserito senza spazi
     */
    private String getInputUsername() {
        String tmp = loginPanel.getUsername().getText();
        return tmp.replaceAll(("\\s+"), "");
    }

    /**
     * Ottiene l'input della password senza spazi.
     *
     * @return la password inserita senza spazi
     */
    private String getInputPassword() {
        String pwd = new String(loginPanel.getPassword().getPassword());
        return pwd.replaceAll(("\\s+"), "");
    }

    /**
     * Inizializza il controller del login.
     */
    public void init() {
        initController();
    }
}
