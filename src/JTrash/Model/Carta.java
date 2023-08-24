package JTrash.Model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 * classe che rappresenta una carta da gioco
 */

/**
 * Questa classe rappresenta una carta da gioco.
 * Ogni carta ha un seme e un valore, può essere scoperta o coperta,
 * e può avere un'immagine associata.
 */
public class Carta extends Observable {

    /**
     * Enumerazione che rappresenta i semi delle carte.
     */
    private Seme seme;

    /**
     * Enumerazione che rappresenta i valori delle carte.
     */
    private Valore valore;

    private boolean coperta;
    private String image;
    private ArrayList<Observer> observers;

    /**
     * Costruttore della classe Carta.
     * Permette di creare un oggetto di tipo Carta, passando come parametri il valore e il seme della carta.
     *
     * @param valore il valore della carta
     * @param seme   il seme della carta
     */
    public Carta(Valore valore, Seme seme) {
        this.valore = valore;
        this.seme = seme;
        coperta = true;
        image = getImage();
        setImage(image);

        observers = new ArrayList<>();
    }

    /**
     * Verifica se la carta è scoperta.
     *
     * @return true se la carta è scoperta, false altrimenti
     */
    public boolean isScoperta() {
        return !coperta;
    }

    /**
     * Verifica se la carta è coperta.
     *
     * @return true se la carta è coperta, false altrimenti
     */
    public boolean isCoperta() {
        return coperta;
    }

    /**
     * Scopre la carta.
     */
    public void scopri() {
        coperta = false;
        setChanged();
        notifyObservers();
    }

    /**
     * Copre la carta.
     */
    public void copri() {
        coperta = true;
    }

    /**
     * Restituisce una rappresentazione testuale della carta.
     *
     * @return una stringa che rappresenta la carta
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isScoperta()) {
            sb.append(valore.name()).append(" di ").append(seme.name());
        } else {
            sb.append("COPERTA");
        }
        return sb.toString();
    }

    /**
     * Restituisce il percorso dell'immagine associata alla carta.
     *
     * @return il percorso dell'immagine della carta
     */
    public String getImage() {
        String path = "/sprites";
        String fileName = "";
        if (isScoperta()) {
            fileName = valore.name() + "-" + seme.name() + ".png";
        } else {
            fileName = "COPERTA.png";
        }
        return path + "/" + fileName;
    }

    /**
     * Imposta l'immagine associata alla carta.
     *
     * @param image l'immagine della carta
     */
    public void setImage(String image) {
        this.image = image;
        setChanged();
        notifyObservers();
    }

    /**
     * Restituisce il valore della carta.
     *
     * @return il valore della carta
     */
    public Valore getValore() {
        return valore;
    }

    /**
     * Aggiunge un osservatore alla lista degli osservatori della carta.
     *
     * @param o l'osservatore da aggiungere
     */
    public void addObserver(Observer o) {
        observers.add(o);
    }
}

