package JTrash.Model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 * Questa classe rappresenta una mano di carte.
 */
public class Mano {

    private ArrayList<Carta> carte;

    /**
     * Costruttore della classe Mano.
     * Inizializza una mano vuota di carte.
     */
    public Mano() {
        carte = new ArrayList<>();
    }

    /**
     * Restituisce una carta dalla mano in base all'indice specificato.
     *
     * @param i l'indice della carta nella mano
     * @return la carta nella posizione specificata
     */
    public Carta getCarta(int i) {
        return carte.get(i);
    }

    /**
     * Restituisce l'elenco delle carte presenti nella mano.
     *
     * @return l'elenco delle carte nella mano
     */
    public ArrayList<Carta> getCarte() {
        return carte;
    }

    /**
     * Restituisce il numero di carte nella mano.
     *
     * @return il numero di carte nella mano
     */
    public int size() {
        return carte.size();
    }

    /**
     * Imposta una carta nella mano in base all'indice specificato.
     *
     * @param i l'indice della carta nella mano
     * @param c la carta da impostare
     */
    public void set(int i, Carta c) {
        carte.set(i, c);
    }

    /**
     * Imposta l'elenco delle carte nella mano.
     *
     * @param carte l'elenco delle carte da impostare
     */
    public void setCarte(ArrayList<Carta> carte) {
        this.carte = carte;
    }

    /**
     * Restituisce una rappresentazione testuale della mano.
     *
     * @return una stringa che rappresenta la mano
     */
    @Override
    public String toString() {
        return carte.toString();
    }
}

