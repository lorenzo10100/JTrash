package JTrash;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu extends JFrame implements ActionListener {
    public Menu(){
        //inizializzo il menu
        JFrame frame = new JFrame("JTrash");
        JPanel panel = new JPanel(new GridBagLayout());
        JButton play = new JButton("Gioca");
        JButton rules = new JButton("Regole");
        JButton exit = new JButton("Esci");
        GridBagConstraints c = new GridBagConstraints();


        //imposto il layout e aggiungo i bottoni al menu
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0,0,10,0);
        panel.add(play, c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10,0,10,0);
        panel.add(rules, c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10,0,0,0);
        panel.add(exit, c);


        //imposto il menu
        frame.add(panel);
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public static void main(String[] args){
        new Menu();
    }



}