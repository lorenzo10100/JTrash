package JTrash.Controller;

import JTrash.Model.*;
import JTrash.View.GamePanel;
import JTrash.View.MainFrame;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Questa classe rappresenta il controller per la gestione del gioco.
 * Si occupa di interagire con il pannello del gioco, il frame principale e la logica del gioco.
 */
public class GameController {
    private Gioco gioco;
    private GamePanel gamePanel;
    private Carta cartaAttuale;
    private MainFrame frame;
    private Utente user;
    private int giocatori;
    private Controller controller;

    /**
     * Costruttore della classe GameController.
     *
     * @param controller database dell'applicazione
     */
    public GameController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Imposta l'utente attuale del gioco.
     *
     * @param user l'utente attuale
     */
    public void setUser(Utente user) {
        this.user = user;
    }

    /**
     * Imposta il numero di giocatori per il gioco.
     *
     * @param giocatori il numero di giocatori
     */
    public void setGiocatori(int giocatori) {
        this.giocatori = giocatori;
    }

    /**
     * Avvia il gioco.
     */
    public void play() {
        visualizza();
        posizionaCarta();
    }

    /**
     * Avvia la musica del gioco, imposta il pannello nel frame principale e passa alla vista del gioco.
     */

    public void visualizza() {
        playMusic();
        frame.setGamePanel(gamePanel);
        frame.switchToGame();
    }

    /**
     * Aggiorna le viste delle carte dei giocatori.
     * Utilizza il thread degli eventi Swing per aggiornare le viste dei giocatori, del tavolo e della carta attuale.
     */
    private void updateViews() {
        ArrayList<Giocatore> players = gioco.getPlayers();
        SwingUtilities.invokeLater(() -> {
            for (int i = 0; i < players.size(); i++) {
                switch (i) {
                    case 0 -> {
                        for (Carta carta : players.get(i).getMano().getCarte()) {
                            gamePanel.getUser().update(carta, null);
                        }
                    }
                    case 1 -> {
                        for (Carta carta : players.get(i).getMano().getCarte()) {
                            gamePanel.getG1().update(carta, null);
                        }
                    }
                    case 2 -> {
                        for (Carta carta : players.get(i).getMano().getCarte()) {
                            gamePanel.getG2().update(carta, null);
                            ;
                        }
                    }
                    case 3 -> {
                        for (Carta carta : players.get(i).getMano().getCarte()) {
                            gamePanel.getG3().update(carta, null);
                        }
                    }
                }
            }
            if (!gioco.getScarti().isEmpty())
                gamePanel.getTavolo().setScarti(gioco.getScarti().getUltimaCarta().getImage());
            if (gioco.getMazzo().isEmpty())
                gamePanel.getTavolo().setMazzo(null);
            gamePanel.getTavolo().update(cartaAttuale, null);
            gamePanel.getTavolo().setGiocatoreAttuale(gioco.getGiocatore().getUsername());
        });
    }

    /**
     * Posiziona una carta durante il gioco.
     * Gestisce l'azione del pulsante di posizionamento della carta, scambia la carta attuale e aggiorna le viste.
     */
    private void posizionaCarta() {
        gamePanel.getUser().getPosiziona().addActionListener(e -> {
            AudioManager.getInstance().playCardEffect("carta");
            if (cartaAttuale == null) {
                JOptionPane.showMessageDialog(null, "Nessuna carta disponibile, pesca una carta!");
            } else {
                Carta temp = gioco.swap(cartaAttuale);
                if (temp != null) {
                    cartaAttuale = temp;
                    cartaAttuale.scopri();
                } else {
                    cartaAttuale = null;
                }
            }
            updateViews();
            checkWinner();
        });
    }

    /**
     * Verifica se c'è un vincitore del gioco.
     * Mostra messaggi e aggiorna le viste in caso di vincita o sconfitta dei giocatori.
     */
    private void checkWinner() {
        if (gioco.gameOver()) {
            Giocatore vincitore = gioco.getGiocatore();
            if (vincitore instanceof Utente) {
                AudioManager.getInstance().playCardEffect("vittoria");
                JOptionPane.showMessageDialog(null, "Hai vinto!");
                updateViews();
                ((Utente) vincitore).vittoria();
                ((Utente) vincitore).addExp();
                controller.aggiornaDati((Utente) vincitore);
            } else {
                AudioManager.getInstance().playCardEffect("sconfitta");
                JOptionPane.showMessageDialog(null, "Il giocatore " + vincitore.getUsername() + " ha vinto!");
                updateViews();
                Utente utente = (Utente) gioco.getPlayers().get(0);
                utente.sconfitta();
                utente.addExp();
                controller.aggiornaDati(utente);
            }
        }
    }

    /**
     * Inizializza l'azione di pesca di una carta.
     * Gestisce l'azione del pulsante di pesca, esegue l'effetto sonoro e aggiorna le viste.
     */
    private void initPesca() {
        gamePanel.getUser().getGioca().addActionListener(e -> {
            drawCard();
            AudioManager.getInstance().playCardEffect("carta");
        });
    }

    /**
     * Pesca una carta dal mazzo durante il gioco.
     * Gestisce l'azione di pesca, rivela la carta pescata, aggiorna le viste e gestisce il rimescolamento del mazzo.
     */
    private void drawCard() {
        if (!gioco.getMazzo().isEmpty()) {
            if (cartaAttuale == null) {
                cartaAttuale = gioco.getMazzo().pesca();
                cartaAttuale.scopri();
                updateViews();
            } else
                JOptionPane.showMessageDialog(null, "E' gia stata pescata una carta!");
        } else
            gioco.swapDeck();
    }

    /**
     * Avvia la musica del gioco.
     */
    private void playMusic() {
        AudioManager.getInstance();
    }


    /**
     * Inizializza il gioco, i giocatori e il pannello del gioco.
     * Inizializza la logica del gioco, crea i giocatori e il pannello, imposta la vista e avvia il gioco.
     */
    public void initGame() {
        ArrayList<Giocatore> players = new ArrayList<>();
        players.add(user);
        for (int i = 0; i < giocatori; i++) {
            players.add(new Giocatore("CPU - " + i));
        }
        gioco = new Gioco(players);
        gamePanel = new GamePanel(players);
        initView();
        initPesca();
        play();
    }

    /**
     * Inizializza la vista del pannello del gioco.
     * Carica l'immagine dell'avatar dell'utente e imposta il nome utente e l'azione del pulsante "Termina".
     */
    private void initView() {
        String path = System.getProperty("user.dir")
                + System.getProperty("file.separator") +
                "avatars" + System.getProperty("file.separator") + user.getAvatar();
        ImageIcon tmp = new ImageIcon(path);
        Image img = tmp.getImage();
        Image newimg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        gamePanel.getUser().setUsername(gioco.getPlayers().get(0).getUsername());
        gamePanel.getUser().setIcon(newimg);
        gamePanel.getUser().getTermina().addActionListener(e -> {
            JOptionPane.showConfirmDialog(null, "Sei sicuro di voler terminare il gioco?", "Termina gioco", JOptionPane.YES_NO_OPTION);
            System.exit(0);
        });
    }

    /**
     * Imposta il frame principale.
     *
     * @param frame il frame principale
     */
    public void setFrame(MainFrame frame) {
        this.frame = frame;
    }
}