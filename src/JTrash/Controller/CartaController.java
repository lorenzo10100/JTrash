package JTrash.Controller;

import JTrash.Model.Carta;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class CartaController implements Observer {
    private Carta c;
    private JLabel cartaGUI;

    public CartaController(Carta c, JLabel cartaGUI){
        this.c = c;
        this.cartaGUI = cartaGUI;
        c.addObserver(this);
    }

    public void updateView(){
        if(c!=null){
        cartaGUI.setIcon(c.getImage());
        cartaGUI.setText("");
        }
        else{
            cartaGUI.setIcon(null);
            cartaGUI.setText("Nessuna carta");
        }
    }


    public void setView(){
        cartaGUI.setPreferredSize(new Dimension(124,113));
    }
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Carta){
            updateView();
        }
    }
}
