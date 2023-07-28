package JTrash.Model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * classe Mazzo che permette l'inizializzazione di un mazzo di carte per il gioco e gestice le operazioni di pesca e mescolamento
 */
public class Mazzo {
    private ArrayList<Carta> mazzo;

    /**
     * Costruttore di Mazzo
     * @param nMazzi int: numero di mazzi da inizializzare. Il numero di mazzi da istanziare cambia in base al numero di giocatori
     */
    //inizializzo un mazzo di carte tramite costruttore
    public Mazzo(int nMazzi){
        if(nMazzi == 1){
            mazzo = new ArrayList<>();
            creaMazzo();
        }
        else {
            mazzo = new ArrayList<>();
            for (int i = 0; i < nMazzi; i++) {
                creaMazzo();
            }
        }
    }

    /**
     * Costruttore di Mazzo vuoto.
     * ATTENZIONE: questo costruttore viene utilizzato solo per inizializzare il mazzo di scarti. Utilizzato solo per wrappare un ArrayList di carte a Mazzo
     */
    public Mazzo(){
        mazzo = new ArrayList<>();
    }


    /**
     * Metodo privato utilizzato dal costruttore per inizializzare il mazzo di carte.
     * Inizializza un mazzo di carte con tutti i valori e semi possibili
     *
     */
    private void creaMazzo(){
        for (Valore v : Valore.values()) {
            {
                for (Seme s : Seme.values()) {
                    Carta c = new Carta(v, s);
                    mazzo.add(c);
                }
            }
        }
    }

    /**
     * Metodo toString di Mazzo
     * @return String: rappresentazione in stringa del mazzo
     */
    @Override
    public String toString(){
        return mazzo.toString();
    }


    /**
     * Metodo che mischia il mazzo di carte
     */
    public void mischia(){
        Collections.shuffle(mazzo);
    }

    /**
     * Metodo che permette di pescare una carta dal mazzo
     * @return Carta: carta pescata dal mazzo
     */
    public Carta pesca(){
        return mazzo.remove(mazzo.size()-1);
    }

    /**
     * Metodo che permette di pescare n carte dal mazzo.
     * Utilizzato solo in fase di inizializzazione delle mani dei giocatori
     * @param n int: numero di carte da pescare
     * @return ArrayList<Carta>: ArrayList di carte pescate dal mazzo
     */
    public ArrayList<Carta> pesca(int n){
        ArrayList<Carta> carte = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            carte.add(mazzo.remove(mazzo.size()-1));
        }
        return carte;
    }

    /**
     * Metodo che permette di aggiungere una carta al mazzo
     * @param c: carta da aggiungere al mazzo
     */
    public void aggiungi(Carta c){
        mazzo.add(c);
    }

    /**
     * Metodo che permette di pulire il mazzo
     */
    public void clear(){
        mazzo.clear();
    }

    /**
     * Metodo che permette di copiare un mazzo in un altro.
     * Utilizzato per copiare il mazzo di scarti nel mazzo di carte
     * @param m: mazzo da copiare
     */
    public void copia(Mazzo m){mazzo.addAll(m.mazzo);}

    /**
     * Metodo che permette di coprire tutte le carte del mazzo
     * Utilizzato per coprire le carte del mazzo di scarti una volta che viene copiato e usato come mazzo di carte giocabili
     */
    public void copriMazzo(){
        for (Carta c : mazzo) {
            c.copri();
        }
    }

    /**
     * Metodo che permette di controllare se il mazzo è vuoto
     * @return boolean: true se il mazzo è vuoto, false altrimenti
     */
    public boolean isEmpty() {
        return mazzo.size() == 0;
    }

    public Carta getUltimaCarta(){
        return mazzo.get(mazzo.size()-1);
    }

    public static void main(String[] args) {
        Mazzo m = new Mazzo(1);
        for(Carta c : m.mazzo){
            c.scopri();
            System.out.println(c);
        }

    }
}
