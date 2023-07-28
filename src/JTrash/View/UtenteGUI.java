package JTrash.View;
import JTrash.Model.Utente;

import javax.swing.*;
import java.awt.*;

public class UtenteGUI extends JPanel {
    private JLabel username;
    private JLabel avatar;
    private JButton play, draw;

    public UtenteGUI(Utente user){
        username = new JLabel("Username: " + user.getUsername());
        avatar = new JLabel(user.getAvatar());
        play = new JButton("Play");
        draw = new JButton("Draw");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));


        add(username);
        add(avatar);
    }


    public JButton draw() {
        return draw;
    }

    public JButton play() {
        return play;
    }
}
