package JTrash.View;

import javax.swing.*;
import java.awt.*;

/**
 * Questa classe rappresenta il frame principale dell'applicazione.
 * Contiene un layout a schede per passare tra i diversi pannelli dell'applicazione, come il menu, il login
 * la selezione dell'avatar, la configurazione e il gioco.
 */
public class MainFrame extends JFrame {
    private JLabel background;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private MenuPanel menu;
    private SetupPanel setup;

    /**
     * Costruttore della classe MainFrame.
     * Inizializza il frame principale con il titolo "JTrash", il layout a schede, il pannello principale e i pannelli del menu e della configurazione.
     * Imposta le dimensioni del frame in base alle dimensioni dello schermo e altre impostazioni.
     */
    public MainFrame() {
        super("JTrash");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        menu = new MenuPanel();
        setup = new SetupPanel();



        setSize(1200, 700);

        setMenuPanel(menu);
        setSetupPanel(setup);
        add(mainPanel);
        cardLayout.show(mainPanel, "menu");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Passa al pannello di login.
     */
    public void switchToLogin() {
        cardLayout.show(mainPanel, "login");
    }

    /**
     * Passa al pannello di selezione dell'avatar.
     */
    public void switchToAvatar() {
        cardLayout.show(mainPanel, "avatar");
    }

    /**
     * Imposta il pannello di login all'interno del pannello principale.
     *
     * @param loginPanel il pannello di login da impostare
     */
    public void setLoginPanel(LoginPanel loginPanel) {
        mainPanel.add(loginPanel, "login");
    }

    /**
     * Imposta il pannello del menu all'interno del pannello principale.
     *
     * @param menuPanel il pannello del menu da impostare
     */
    public void setMenuPanel(MenuPanel menuPanel) {
        mainPanel.add(menuPanel, "menu");
    }

    /**
     * Imposta il pannello dell'avatar all'interno del pannello principale.
     *
     * @param avatarPanel il pannello dell'avatar da impostare
     */
    public void setAvatarPanel(AvatarPanel avatarPanel) {
        mainPanel.add(avatarPanel, "avatar");
    }

    /**
     * Passa al pannello di gioco.
     */
    public void switchToGame() {
        cardLayout.show(mainPanel, "game");
    }

    /**
     * Imposta il pannello di configurazione all'interno del pannello principale.
     *
     * @param setupPanel il pannello di configurazione da impostare
     */
    public void setSetupPanel(SetupPanel setupPanel) {
        mainPanel.add(setupPanel, "setup");
    }

    /**
     * Imposta il pannello di gioco all'interno del pannello principale.
     *
     * @param gamePanel il pannello di gioco da impostare
     */
    public void setGamePanel(GamePanel gamePanel) {
        mainPanel.add(gamePanel, "game");
    }

    /**
     * Restituisce il pannello del menu.
     *
     * @return il pannello del menu
     */
    public MenuPanel getMenu() {
        return menu;
    }

    /**
     * Restituisce il pannello di configurazione.
     *
     * @return il pannello di configurazione
     */
    public SetupPanel getSetup() {
        return setup;
    }

    public void switchToSetup() {
        cardLayout.show(mainPanel, "setup");
    }
}
