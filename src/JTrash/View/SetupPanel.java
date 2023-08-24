package JTrash.View;

import javax.swing.*;
import java.awt.*;

/**
 * Questa classe rappresenta il pannello di configurazione del gioco.
 * Consente all'utente di selezionare il numero di giocatori per la partita.
 */
public class SetupPanel extends JPanel {
    private JLabel giocatori;
    private JButton conferma;
    private JComboBox<Integer> numeroGiocatori;

    /**
     * Costruttore della classe SetupPanel.
     * Inizializza il pannello di configurazione con il selettore del numero di giocatori e il pulsante di conferma.
     */
    public SetupPanel() {

        setLayout(new GridBagLayout());
        giocatori = new JLabel("Scegli il numero di giocatori :");
        Integer[] ngiocatori = {1, 2, 3};
        numeroGiocatori = new JComboBox<>(ngiocatori);
        conferma = new JButton("Conferma");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;


        add(giocatori, gbc);
        add(numeroGiocatori, gbc);
        add(Box.createRigidArea(new Dimension(0, 10)), gbc);
        add(conferma, gbc);
    }

    /**
     * Restituisce il pulsante di conferma.
     *
     * @return il pulsante di conferma
     */
    public JButton getConferma() {
        return conferma;
    }

    /**
     * Ottiene il numero di giocatori selezionato dall'utente.
     *
     * @return il numero di giocatori selezionato
     */
    public int getPlayers() {
        return (int) numeroGiocatori.getSelectedItem();
    }
}
