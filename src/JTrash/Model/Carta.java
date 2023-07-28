package JTrash.Model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 * classe che rappresenta una carta da gioco
 */
public class Carta extends Observable {
    /**
     * enum che rappresenta i semi delle carte
     */
    private Seme seme;

    /**
     * enum che rappresenta i valori delle carte
     */
    private Valore valore;

    private boolean coperta;
    private ImageIcon image;
    private ArrayList<Observer> observers;


    /**
     * Costruttore della classe Carta.
     * Permette di creare un oggetto di tipo Carta, passando come parametri il valore e il seme della carta.
     * @param valore : valore della carta
     * @param seme : seme della carta
     */
    public Carta(Valore valore, Seme seme){
        this.valore = valore;
        this.seme = seme;
        coperta = true;
        image = getImage();
        setImage(image);
    }

    /**
     * Metodo che permette di verificare se la carta è scoperta o meno.
     * @return true se la carta è scoperta, false altrimenti
     */
    private boolean isScoperta(){return !coperta;}

    /**
     * Metodo che permette di verificare se la carta è coperta o meno.
     * @return true se la carta è coperta, false altrimenti
     */
    public boolean isCoperta() {
        return coperta;
    }

    /**
     * Metodo che permette di scoprire una carta.
     */
    public void scopri(){
        coperta = false;
        setChanged();
        notifyObservers();
    }

    /**
     * Metodo che permette di coprire una carta.
     */
    public void copri(){coperta = true;}

    /**
     * Metodo che rappresenta in stringa una carta.
     * @return stringa che rappresenta la carta
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        if(isScoperta()){
            sb.append(valore.name()).append(" di ").append(seme.name());}
        else {sb.append("COPERTA");}
        return sb.toString();
    }

    /**
     * Metodo che permette di ottenere l'immagine di una carta.
     * @return immagine della carta
     */
    public ImageIcon getImage(){
        String path = "src/sprites";
        String fileName = "";
        if(isScoperta()){
            fileName = valore.name() + "-" + seme.name() + ".png";
        }
        else {
            fileName = "COPERTA.png";
        }
        return new ImageIcon(path + "/" + fileName);
    }

    /**
     * Metodo che permette di settare l'immagine di una carta.
     * @param image : immagine della carta
     */
    public void setImage(ImageIcon image){
        this.image = image;
    }

    public Valore getValore() {
        return valore;
    }

    public void addObserver(Observer o){
        observers.add(o);
    }
}

