package JTrash;

import javax.swing.*;
import java.awt.*;


public class Avatar {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel label = new JLabel();

        label.setIcon(new ImageIcon("images.jpeg"));
        JTextArea text = new JTextArea();
        text.setText("Inserire un nome per il giocatore:");
        panel.setLayout(new GridBagLayout());
        panel.add(label);
        panel.add(text);
        panel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(500,500);
        frame.setVisible(true);
    }
}