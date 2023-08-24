package JTrash.View;

import javax.swing.*;
import java.awt.*;

/**
 * Questa classe rappresenta il pannello del menu principale dell'applicazione.
 * Contiene pulsanti per avviare il gioco, visualizzare le regole e uscire dall'applicazione.
 */
public class MenuPanel extends JPanel {
    private JButton gioca, regole, esci;

    /**
     * Costruttore della classe MenuPanel.
     * Inizializza il pannello del menu con i pulsanti "Gioca", "Regole" ed "Esci".
     */
    public MenuPanel() {
        setLayout(new GridBagLayout());

        gioca = new JButton("Gioca");
        regole = new JButton("Regole");
        esci = new JButton("Esci");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        add(new JLabel("<html><h1><strong><i>JTrash</i></strong></h1><hr></html>"), gbc);
        add(Box.createRigidArea(new Dimension(0, 25)), gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        int width = (int) (screenSize.width * 0.09);
        int height = (int) (screenSize.height * 0.07);

        gioca.setPreferredSize(new Dimension(width, height));
        regole.setPreferredSize(new Dimension(width, height));
        esci.setPreferredSize(new Dimension(width, height));

        add(Box.createVerticalGlue());

        add(gioca, gbc);
        add(Box.createRigidArea(new Dimension(0, 25)), gbc);
        add(regole, gbc);
        add(Box.createRigidArea(new Dimension(0, 25)), gbc);
        add(esci, gbc);

        add(Box.createVerticalGlue());
    }

    /**
     * Restituisce il pulsante "Gioca".
     *
     * @return il pulsante "Gioca"
     */
    public JButton getGioca() {
        return gioca;
    }

    /**
     * Restituisce il pulsante "Regole".
     *
     * @return il pulsante "Regole"
     */
    public JButton getRegole() {
        return regole;
    }

    /**
     * Restituisce il pulsante "Esci".
     *
     * @return il pulsante "Esci"
     */
    public JButton getEsci() {
        return esci;
    }

}
