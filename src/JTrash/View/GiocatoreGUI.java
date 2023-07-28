package JTrash.View;

import JTrash.Model.Giocatore;
import JTrash.Model.Mano;
import JTrash.Model.Mazzo;

import javax.swing.*;
import java.awt.*;

public class GiocatoreGUI extends JPanel {
    private JLabel username;
    private ManoGUI mano;

    public GiocatoreGUI(Giocatore g){
        username = new JLabel(g.getUsername());
        mano = new ManoGUI(g.getMano());

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        mano.updateMano(g.getMano() );

        add(username);
        add(Box.createVerticalStrut(10));
        add(mano);
    }


    public static void main(String[] args) {
        Giocatore g = new Giocatore("Pippo");
        Mazzo mazzo = new Mazzo(1);
        mazzo.mischia();
        mazzo.copriMazzo();
        Mano mano = new Mano();
        mano.setCarte(mazzo.pesca(10));
        g.setMano(mano);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GiocatoreGUI(g));
        frame.pack();
        frame.setVisible(true);
    }
}
