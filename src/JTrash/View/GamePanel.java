package JTrash.View;

import JTrash.Controller.CartaController;
import JTrash.Model.Carta;
import JTrash.Model.Giocatore;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Questa classe rappresenta il pannello principale del gioco, che contiene le mani dei giocatori e il tavolo di gioco.
 * Mostra le mani dei giocatori e il tavolo dove vengono posizionate le carte in gioco.
 */
public class GamePanel extends JPanel {
    private HandPanel user, g1, g2, g3;
    private TavoloPanel tavolo;

    /**
     * Costruttore della classe GamePanel.
     * Crea il pannello principale del gioco, inizializza le mani dei giocatori e il tavolo di gioco.
     *
     * @param players una lista di giocatori partecipanti al gioco
     */
    public GamePanel(ArrayList<Giocatore> players) {
        setLayout(new BorderLayout());
        tavolo = new TavoloPanel();
        add(tavolo, BorderLayout.CENTER);
        for (int i = 0; i < players.size(); i++) {
            switch (i) {
                case 0 -> {
                    user = new HandPanel(players.get(i).getMano());
                    for (Carta carta : players.get(i).getMano().getCarte())
                        new CartaController(carta, user);
                    add(user, BorderLayout.SOUTH);
                }
                case 1 -> {
                    g1 = new HandPanel(players.get(i).getMano());
                    g1.avversario();
                    for (Carta carta : players.get(i).getMano().getCarte())
                        new CartaController(carta, g1);
                    add(g1, BorderLayout.NORTH);
                }
                case 2 -> {
                    g2 = new HandPanel(players.get(i).getMano());
                    g2.avversario();
                    for (Carta carta : players.get(i).getMano().getCarte())
                        new CartaController(carta, g2);
                    add(g2, BorderLayout.WEST);
                }
                case 3 -> {
                    g3 = new HandPanel(players.get(i).getMano());
                    g3.avversario();
                    for (Carta carta : players.get(i).getMano().getCarte())
                        new CartaController(carta, g3);
                    add(g3, BorderLayout.EAST);
                }
            }
        }
    }

    /**
     * Imposta il pannello della mano del giocatore.
     *
     * @param user il pannello della mano del giocatore
     */
    public void setUser(HandPanel user) {
        this.user = user;
    }

    /**
     * Restituisce il pannello della mano dell'utente.
     *
     * @return la mano dell'utente
     */
    public HandPanel getUser() {
        return user;
    }

    /**
     * Restituisce la mano del giocatore g1.
     *
     * @return la mano del giocatore g1
     */
    public HandPanel getG1() {
        return g1;
    }

    /**
     * Restituisce la mano del giocatore g2.
     *
     * @return la mano del giocatore g2
     */
    public HandPanel getG2() {
        return g2;
    }

    /**
     * Restituisce la mano del giocatore g3.
     *
     * @return la mano del giocatore g3
     */
    public HandPanel getG3() {
        return g3;
    }

    /**
     * Restituisce il pannello del tavolo di gioco.
     *
     * @return il pannello del tavolo di gioco
     */
    public TavoloPanel getTavolo() {
        return tavolo;
    }

}

