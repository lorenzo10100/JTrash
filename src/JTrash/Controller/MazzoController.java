package JTrash.Controller;

import JTrash.Model.Mazzo;

import javax.swing.*;
import java.awt.*;

public class MazzoController {
    private Mazzo mazzo;
    private JLabel mazzoGUI;
    private static final ImageIcon DORSO = new ImageIcon("src/JTrash/sprites/COPERTA.png");

    public MazzoController(Mazzo mazzo, JLabel mazzoGUI){
        this.mazzo = mazzo;
        this.mazzoGUI = mazzoGUI;
    }

    public void updateView(){
        if(!mazzo.isEmpty()){
            mazzoGUI.setIcon(DORSO);
            mazzoGUI.setText("");
        }
        else{
            mazzoGUI.setIcon(null);
            mazzoGUI.setText("Mazzo vuoto");
        }
    }

    public void updateScarto(){
        if(!mazzo.isEmpty()){
            mazzoGUI.setIcon(mazzo.getUltimaCarta().getImage());
            mazzoGUI.setText("");
        }
        else{
            mazzoGUI.setIcon(null);
        }
    }

    public void setView(){
        mazzoGUI.setPreferredSize(new Dimension(124,113));
    }


}
