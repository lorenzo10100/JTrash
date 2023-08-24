package JTrash.View;

import JTrash.Model.Carta;
import JTrash.Model.Mano;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Questa classe rappresenta il pannello che visualizza la mano di carte di un giocatore.
 * Mostra le carte della mano, insieme a pulsanti per pescare, posizionare le carte e terminare la sessione.
 */
public class HandPanel extends JPanel implements Observer {
    private JPanel info;
    private JPanel utente;
    private JPanel upperPanel, lowerPanel;
    private JLabel username;
    private JButton gioca;
    private JButton posiziona;
    private JButton termina;
    private ArrayList<CartaLabel> carte;
    private Mano mano;

    /**
     * Costruttore della classe HandPanel.
     * Crea il pannello che visualizza la mano del giocatore, insieme a pulsanti per le azioni.
     *
     * @param mano la mano di carte del giocatore
     */
    public HandPanel(Mano mano) {
        this.mano = mano;
        carte = new ArrayList<>(10);

        setLayout(new BorderLayout(0, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        utente = new JPanel(new GridBagLayout());
        add(utente, BorderLayout.CENTER);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        upperPanel = new JPanel(new FlowLayout());

        for (int i = 0; i < 5; i++) {
            CartaLabel cartaLabel = new CartaLabel(mano.getCarta(i));
            carte.add(i, cartaLabel);
            upperPanel.add(cartaLabel);
        }

        // Pannello inferiore per la seconda fila di carte
        lowerPanel = new JPanel(new FlowLayout());

        for (int i = 5; i < 10; i++) {
            CartaLabel cartaLabel = new CartaLabel(mano.getCarta(i));
            carte.add(i, cartaLabel);
            lowerPanel.add(cartaLabel);
        }

        // Pannello principale per la mano
        JPanel manoPanel = new JPanel(new BorderLayout());
        manoPanel.add(upperPanel, BorderLayout.NORTH);
        manoPanel.add(lowerPanel, BorderLayout.SOUTH);

        info = new JPanel(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 0;


        username = new JLabel("Username");
        username.setVerticalTextPosition(JLabel.TOP);
        username.setHorizontalTextPosition(JLabel.CENTER);
        info.add(username, gbc2);

        gbc2.gridy++;
        gioca = new JButton("Pesca");
        info.add(gioca, gbc2);

        gbc2.gridy++;
        posiziona = new JButton("Posiziona");
        info.add(posiziona, gbc2);

        gbc2.gridy++;
        termina = new JButton("Termina sessione");
        info.add(termina, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.gridheight = 2;
        utente.add(info, gbc2);

        gbc2.gridx = 1;
        gbc2.gridy = 1;
        gbc2.gridheight = 1;
        utente.add(manoPanel, gbc2);
    }

    /**
     * Nasconde le informazioni dell'utente per visualizzare il pannello come avversario.
     */
    public void avversario() {
        info.setVisible(false);
    }

    /**
     * Restituisce il pulsante per pescare una carta.
     *
     * @return il pulsante per pescare una carta
     */
    public JButton getGioca() {
        return gioca;
    }

    /**
     * Restituisce il pulsante per posizionare una carta.
     *
     * @return il pulsante per posizionare una carta
     */
    public JButton getPosiziona() {
        return posiziona;
    }

    /**
     * Restituisce il pulsante per terminare la sessione.
     *
     * @return il pulsante per terminare la sessione
     */
    public JButton getTermina() {
        return termina;
    }

    /**
     * Imposta il nome utente visualizzato nel pannello.
     *
     * @param username il nome utente da impostare
     */
    public void setUsername(String username) {
        this.username.setText(username);
    }

    /**
     * Imposta l'icona utente visualizzata nel pannello.
     *
     * @param icon l'icona utente da impostare
     */
    public void setIcon(Image icon) {
        this.username.setIcon(new ImageIcon(icon));
    }

    /**
     * Metodo dell'interfaccia Observer. Viene chiamato quando l'oggetto osservato (Carta) viene aggiornato.
     *
     * @param o   l'oggetto osservato
     * @param arg l'argomento dell'aggiornamento
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Carta)
            updateIcons();
    }

    /**
     * Aggiorna le icone delle carte nella mano del giocatore.
     */
    private void updateIcons() {
        //rimuovo le carte dal pannello
        upperPanel.removeAll();
        lowerPanel.removeAll();
        carte.clear();
        //e le aggiorno
        for (int i = 0; i < 5; i++) {
            CartaLabel cartaLabel = new CartaLabel(mano.getCarta(i));
            carte.add(i, cartaLabel);
            upperPanel.add(cartaLabel);
        }

        for (int i = 5; i < 10; i++) {
            CartaLabel cartaLabel = new CartaLabel(mano.getCarta(i));
            carte.add(i, cartaLabel);
            lowerPanel.add(cartaLabel);
        }
    }
}
