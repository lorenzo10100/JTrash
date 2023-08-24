package JTrash.View;

import JTrash.Model.Carta;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Questa classe rappresenta il pannello del tavolo di gioco, che mostra le informazioni relative alla partita in corso.
 * Include le immagini delle carte in gioco, lo stato del mazzo e altre informazioni utili.
 */
public class TavoloPanel extends JPanel implements Observer {
    private JLabel scarti, mazzo, cartaAttuale, giocatoreAttuale;

    /**
     * Costruttore della classe TavoloPanel.
     * Inizializza le etichette per mostrare lo stato delle carte in gioco, del mazzo e del giocatore attuale.
     */
    public TavoloPanel() {
        mazzo = new JLabel("Mazzo");
        mazzo.setVerticalTextPosition(JLabel.TOP);
        mazzo.setHorizontalTextPosition(JLabel.CENTER);
        mazzo.setIcon(new ImageIcon(getClass().getResource("/sprites/COPERTA.png")));

        scarti = new JLabel("Scarti");
        scarti.setVerticalTextPosition(JLabel.TOP);
        scarti.setHorizontalTextPosition(JLabel.CENTER);

        cartaAttuale = new JLabel("Carta Attuale");
        cartaAttuale.setVerticalTextPosition(JLabel.TOP);
        cartaAttuale.setHorizontalTextPosition(JLabel.CENTER);

        giocatoreAttuale = new JLabel("Giocatore Attuale");
        giocatoreAttuale.setVerticalTextPosition(JLabel.TOP);
        giocatoreAttuale.setHorizontalTextPosition(JLabel.CENTER);

        add(scarti);
        add(mazzo);
        add(cartaAttuale);
        add(Box.createHorizontalStrut(20));
        add(giocatoreAttuale);
    }

    /**
     * Imposta l'immagine della carta degli scarti.
     *
     * @param carta l'immagine della carta degli scarti
     */
    public void setScarti(String carta) {
        scarti.setIcon(new ImageIcon(getClass().getResource(carta)));
    }

    /**
     * Imposta l'immagine della carta attualmente in gioco.
     *
     * @param carta l'immagine della carta attuale
     */
    private void setCartaAttuale(String carta) {
        if (carta != null)
            cartaAttuale.setIcon(new ImageIcon(getClass().getResource(carta)));
        else
            cartaAttuale.setIcon(null);
    }

    /**
     * Imposta l'immagine della carta del mazzo.
     *
     * @param carta l'immagine della carta del mazzo
     */
    public void setMazzo(String carta) {
        mazzo.setIcon(new ImageIcon(getClass().getResource(carta)));
    }

    /**
     * Imposta il giocatore attuale.
     *
     * @param giocatore il nome del giocatore attuale
     */
    public void setGiocatoreAttuale(String giocatore) {
        giocatoreAttuale.setText("Giocatore attuale: " + giocatore);
    }

    /**
     * Metodo di aggiornamento chiamato quando cambia lo stato degli oggetti osservati.
     *
     * @param o   l'oggetto osservato
     * @param arg l'argomento passato dall'oggetto osservato
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Carta c)
            setCartaAttuale(c.getImage());
        if (o == null)
            setCartaAttuale(null);
    }
}
