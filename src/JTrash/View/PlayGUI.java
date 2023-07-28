package JTrash.View;

import JTrash.Model.Utente;

import javax.swing.*;
import java.awt.*;
;

/**
 * Classe che si occupa della finestra dopo aver premuto il tasto "Play" nel menu principale.
 */
public class PlayGUI{

    private JFrame frame;
    private JPanel panel;
    private JButton conferma;
    private JTextField  scelta;
    private JLabel testo;

    private GridBagConstraints gbc;

    /**
     * Costruttore della classe Play.
     * All'interno vi e' la scelta del numero di giocatori artificiali (1-3).
     * Il giocatore umano e' sempre presente.
     */
    public PlayGUI(){
        frame = new JFrame("JTrash");
        panel = new JPanel(new GridBagLayout());
        conferma = new JButton("Conferma");
        testo = new JLabel("Inserisci il numero di giocatori artificiali(1-3):");
        scelta = new JTextField();

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        panel.add(new JLabel("<html><h1><strong><i>JTrash</i></strong></h1><hr></html>"), gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panel.add(testo, gbc);
        panel.add(scelta, gbc);
        panel.add(Box.createRigidArea(new Dimension(0, 10)), gbc);
        panel.add(conferma, gbc);


        frame.add(panel);
        frame.pack();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        conferma.addActionListener(e->{
            String text = scelta.getText();
            if (text.isEmpty() || !text.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Inserisci un numero di giocatori", "Errore", JOptionPane.ERROR_MESSAGE);
            }
            if (text.matches("\\d+")) {
                int players = Integer.parseInt(text);
                if (players < 1 || players > 3)
                    JOptionPane.showMessageDialog(null, "Inserisci un numero di giocatori compreso tra 1 e 3", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
