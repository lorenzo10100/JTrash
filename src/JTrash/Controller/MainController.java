package JTrash.Controller;

import JTrash.View.LoginPanel;
import JTrash.View.MainFrame;

import javax.swing.*;

/**
 * Questa classe rappresenta il controller principale dell'applicazione.
 * Si occupa di inizializzare le viste, i controller e di gestire gli eventi dell'interfaccia grafica.
 */
public class MainController {
    private MainFrame frame;
    private GameController gameController;
    private LoginController loginController;
    private LoginPanel login;

    /**
     * Costruttore della classe MainController.
     * Inizializza le viste, i controller e imposta i listener per gli eventi dell'interfaccia grafica.
     */
    public MainController() {
        initViews();
        initControllers();
        setupListeners();
    }

    /**
     * Inizializza le viste dell'applicazione.
     * Crea il frame principale e il pannello di login.
     */
    private void initViews() {
        frame = new MainFrame();
        login = new LoginPanel();
    }

    /**
     * Inizializza i controller dell'applicazione.
     * Crea il database e il controller del gioco, impostando le relazioni tra di essi e il frame.
     */
    private void initControllers() {
        Controller controller = new Controller();
        gameController = new GameController(controller);
        gameController.setFrame(frame);
        loginController = new LoginController(gameController, controller, login, frame);
    }

    /**
     * Imposta i listener per gli eventi dell'interfaccia grafica.
     * Gestisce gli eventi dei pulsanti nel menu e nel pannello di setup del gioco.
     */
    private void setupListeners() {
        String regole = "Le regole sono semplici: \n\n" +
                " •Il giocatore pesca una carta dal mazzo e la scopre\n\n" +
                " •Il giocatore deve posizionare la carta pescata nella sua posizione (es: pesco un due, metto il due in seconda posizione)\n\n" +
                " •Se in quella posizione vi e' una carta diversa dalla posizione che guardo, allora metto la carta pescata in quella posizione, la carta che si trovava precedentemente in quella posizione viene scartata\n\n" +
                " •Vince il giocatore che riesce a posizionare tutte le carte in ordine crescente\n\n" +
                " •Jack e Regina sono carte non giocabili, mentre Re sono wildcard(possono essere posizionate in qualsiasi posizione)\n\n" +
                " •Se il mazzo finisce, viene mischiato il mazzo scarti e viene coperto il mazzo\n\n";

        frame.getMenu().getGioca().addActionListener(e -> {
            loginController.init();
        });
        frame.getMenu().getRegole().addActionListener(e -> {
            JOptionPane.showMessageDialog(null, regole);
        });
        frame.getMenu().getEsci().addActionListener(e -> System.exit(0));

        frame.getSetup().getConferma().addActionListener(e -> {
            int players = frame.getSetup().getPlayers();
            gameController.setGiocatori(players);
            gameController.initGame();
        });
    }
}
