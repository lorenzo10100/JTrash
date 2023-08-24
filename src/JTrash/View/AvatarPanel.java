package JTrash.View;


import javax.swing.*;
import java.awt.*;

/**
 * Questa classe rappresenta un pannello per la selezione dell'avatar da parte dell'utente.
 * Fornisce elementi grafici per visualizzare il nome dell'utente, l'avatar selezionato
 * e pulsanti per confermare la scelta dell'avatar e per effettuare il logout.
 */
public class AvatarPanel extends JPanel {
    private JPanel buttons;
    private JLabel avatar, nome;
    private JButton conferma, logout, scegli;

    /**
     * Costruttore della classe AvatarPanel.
     * Inizializza gli elementi grafici del pannello, inclusi etichette e pulsanti.
     */
    public AvatarPanel() {
        avatar = new JLabel("Scegli il tuo avatar:");
        nome = new JLabel();
        nome.setFont(new Font("Arial", Font.BOLD, 20));
        conferma = new JButton("Conferma");
        logout = new JButton("Logout");
        scegli = new JButton("Scegli");
        buttons = new JPanel();

        setLayout(new GridBagLayout());
        avatar = new JLabel("Scegli un avatar!");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;


        add(nome, gbc);
        add(Box.createRigidArea(new Dimension(0, 10)), gbc);
        add(avatar, gbc);
        add(scegli, gbc);
        add(Box.createRigidArea(new Dimension(0, 10)), gbc);
        add(buttons, gbc);
        buttons.add(conferma);
        buttons.add(Box.createRigidArea(new Dimension(5, 0)));
        buttons.add(logout);
    }

    /**
     * Restituisce l'etichetta che mostra l'avatar selezionato.
     *
     * @return l'etichetta dell'avatar
     */
    public JLabel getAvatar() {
        return avatar;
    }

    /**
     * Restituisce l'etichetta che mostra il nome dell'utente.
     *
     * @return l'etichetta del nome
     */
    public JLabel getNome() {
        return nome;
    }

    /**
     * Restituisce il pulsante per confermare la scelta dell'avatar.
     *
     * @return il pulsante di conferma
     */
    public JButton getConferma() {
        return conferma;
    }

    /**
     * Restituisce il pulsante per effettuare il logout.
     *
     * @return il pulsante di logout
     */
    public JButton getLogout() {
        return logout;
    }

    /**
     * Restituisce il pulsante per scegliere l'avatar.
     *
     * @return il pulsante per la scelta dell'avatar
     */
    public JButton getScegli() {
        return scegli;
    }
}
