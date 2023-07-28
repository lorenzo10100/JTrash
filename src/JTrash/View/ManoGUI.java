package JTrash.View;

import JTrash.Controller.ManoController;
import JTrash.Model.Carta;
import JTrash.Model.Mano;
import JTrash.Model.Mazzo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ManoGUI extends JPanel {
    private JPanel carte, carte1, carte2;
    private ManoController controller;

    public ManoGUI(Mano mano){
        controller = new ManoController(mano, this);
        carte = new JPanel();
        carte1 = new JPanel();
        carte2 = new JPanel();

        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        carte.setLayout(new BoxLayout(carte, BoxLayout.Y_AXIS));
        carte1.setLayout(new BoxLayout(carte1, BoxLayout.X_AXIS));
        carte2.setLayout(new BoxLayout(carte2, BoxLayout.X_AXIS));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.CENTER;



        carte.add(carte1);
        carte.add(Box.createVerticalStrut(10));
        carte.add(carte2);
        add(carte, gbc);
    }

    public void updateMano(Mano mano){
        carte1.removeAll();
        carte2.removeAll();

        for (int i = 0; i < 5; i++) {
            JLabel carta = new JLabel();
            carta.setPreferredSize(new Dimension(113, 124));
            carta.setIcon(mano.getCarta(i).getImage());
            carte1.add(carta);
            carte1.add(Box.createHorizontalStrut(5));
        }

        for (int i = 5; i < 10; i++) {
            JLabel carta = new JLabel();
            carta.setPreferredSize(new Dimension(113, 124));
            carta.setIcon(mano.getCarta(i).getImage());
            carte2.add(carta);
            carte2.add(Box.createHorizontalStrut(5));
        }

        // Aggiorna la GUI
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        Mano m = new Mano();
        ManoGUI mano = new ManoGUI(m);
    }
}
