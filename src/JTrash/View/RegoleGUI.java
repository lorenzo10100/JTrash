package JTrash.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


/**
 * Classe che gestisce la finestra di gioco delle regole.
 */
public class RegoleGUI extends JFrame{
    private JFrame frame;
    private JPanel panel;
    private JButton chiudi;

    private GridBagConstraints gbc;

    /**
     * Costruttore della finestra delle regole.
     * Vengono creati i componenti grafici della finestra Regole.
     * Vengono anche trascritte le regole del gioco all'interno di un JTextArea.
     */
    public RegoleGUI() {
        frame = new JFrame("Regole");
        panel = new JPanel(new GridBagLayout());
        chiudi = new JButton("Chiudi");
        gbc = new GridBagConstraints();

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        int width = (int)(screenSize.width * 0.09);
        int height = (int)(screenSize.height * 0.07);

        chiudi.setPreferredSize(new Dimension(width, height));

        JTextArea rulesText = new JTextArea();
        rulesText.setFont(new Font("Sans Serif", Font.BOLD, 20));
        rulesText.setText("Le regole sono semplici: \n\n" +
                " •Il giocatore pesca una carta dal mazzo e la scopre\n\n" +
                " •Il giocatore deve posizionare la carta pescata nella sua posizione (es: pesco un due, metto il due in seconda posizione)\n\n" +
                " •Se in quella posizione vi e' una carta diversa dalla posizione che guardo, allora metto la carta pescata in quella posizione, la carta che si trovava precedentemente in quella posizione viene scartata\n\n" +
                " •Vince il giocatore che riesce a posizionare tutte le carte in ordine crescente\n\n" +
                " •Jack e Regina sono carte non giocabili, mentre Re sono wildcard(possono essere posizionate in qualsiasi posizione)\n\n" +
                " •Se il mazzo finisce, viene mischiato il mazzo scarti e viene coperto il mazzo\n\n"
        );
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        rulesText.setEditable(false);
        rulesText.setWrapStyleWord(true);
        rulesText.setBorder(new EmptyBorder(10, 10, 10, 10));

        panel.add(rulesText, gbc);
        panel.add(Box.createRigidArea(new Dimension(0, 10)), gbc);
        panel.add(chiudi, gbc);

        chiudi.addActionListener(e->frame.dispose());

        frame.add(panel);
        frame.pack();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
