package JTrash.View;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che si occupa di creare il menu principale del gioco
 * inoltre aggiunge tutti gli altri componenti grafici,
 * come i bottoni per giocare, per vedere le regole e per uscire dal gioco.
 */
public class Menu extends JFrame {
    private JFrame frame;
    private JPanel panel, buttons;
    private JButton play, rules, exit;
    private GridBagConstraints gbc;

    /**
     * Costruttore del menu principale del gioco.
     * Al suo interno vengono creati tutti i componenti grafici
     * e vengono aggiunti al pannello principale.
     *
     * Inoltre vengono aggiunti i listener ai bottoni per gestire
     * le varie azioni.
     *
     * Il metodo pack() viene utilizzato per adattare la finestra al contenuto.
     *
     * Il metodo setSize() viene utilizzato per impostare la dimensione della finestra.
     *
     * Il metodo setLocationRelativeTo() viene utilizzato per posizionare la finestra al centro dello schermo.
     *
     * Il metodo setDefaultCloseOperation() viene utilizzato per impostare la chiusura del programma
     *
     * Il metodo setVisible() viene utilizzato per rendere visibile la finestra.
     *
     * Il metodo setPreferredSize() viene utilizzato per impostare la dimensione preferita dei bottoni.
     */
    public Menu(){

        frame = new JFrame("JTrash");

        //creo il pannello
        panel = new JPanel();

        panel.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        panel.add(new JLabel("<html><h1><strong><i>JTrash</i></strong></h1><hr></html>"), gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        buttons = new JPanel(new GridBagLayout());
        play = new JButton("Gioca"); rules = new JButton("Regole"); exit = new JButton("Esci");

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        int width = (int)(screenSize.width * 0.09);
        int height = (int)(screenSize.height * 0.07);

        play.setPreferredSize(new Dimension(width, height));
        rules.setPreferredSize(new Dimension(width, height));
        exit.setPreferredSize(new Dimension(width, height));

        buttons.add(play, gbc);
        buttons.add(Box.createRigidArea(new Dimension(0, 10)), gbc);
        buttons.add(rules, gbc);
        buttons.add(Box.createRigidArea(new Dimension(0, 10)), gbc);
        buttons.add(exit, gbc);

        exit.addActionListener(e -> System.exit(0));

        play.addActionListener(e -> {
            new PlayGUI();
            new Login();
            frame.dispose();
        });

        rules.addActionListener(e-> new RegoleGUI().setVisible(true));

        gbc.weighty = 1;
        panel.add(buttons, gbc);

        frame.add(panel);
        frame.pack();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Menu();
    }
}