package JTrash.Model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Classe che si occupa della gestione del gioco.
 * Viene gestita la logica del gioco di carte Trash e vengono gestite le
 * varie regole del gioco.
 */

public class Gioco {

    /**
     * giocatore corrente
     */
    private int currentPlayer;

    /**
     * numero di giocatori
     */
    private ArrayList<Giocatore> players;

    /**
     * rappresenta il mazzo da gioco
     */
    private Mazzo mazzo;

    /**
     * ArrayList di ArrayList di carte, dove ogni indice rappresenta un giocatore e ogni giocatore
     * avrà la sua mano di gioco
     */

    /**
     * Array che rappresenta la pila scarti del gioco
     */
    private Mazzo pilaScarti;


    /**
     * Costruttore della sessione di gioco, al suo interno inoltre vi è tutta la logica
     * che concerne il gioco Trash e tutte le varie casistiche e regole di gioco.
     *
     * @param players Giocatore[] : rappresenta un array di giocatori per la sessione di gioco(massimo 4)
     */
    public Gioco(ArrayList<Giocatore> players) {
        this.players = players;
        pilaScarti = new Mazzo();
        if (players.size() == 2) {
            mazzo = new Mazzo(1);
        }
        if (players.size() >= 3) {
            mazzo = new Mazzo(2);
        }
        mazzo.mischia();
        mazzo.copriMazzo();
        currentPlayer = new Random().nextInt(players.size());
        for (Giocatore g : players) {
            g.getMano().setCarte(mazzo.pesca(10));
        }
    }

    public ArrayList<Giocatore> getPlayers() {
        return players;
    }

    /**
     * Metodo che si occupa di iniziare la sessione di gioco
     */
    public Carta start() {
        Carta c = mazzo.pesca();
        c.scopri();
        return c;
    }

    /**
     * Metodo che si occupa di gestire la fine del gioco.
     * Controlla se almeno in una mano, le carte sono ordinate in maniera crescente
     *
     * @return boolean : true se il gioco è finito, false altrimenti
     */

    public boolean gameOver() {
        return IntStream.range(1, getMano().size()).allMatch(i -> getGiocatore().getCartaMano(i - 1).isScoperta() && getGiocatore().getCartaMano(i).isScoperta());
    }

    /**
     * Metodo che si occupa di gestire il cambio del mazzo.
     * Se il mazzo è vuoto, viene copiata la pila degli scarti nel mazzo, viene coperto il mazzo
     * e viene mischiato.
     */
    public void swapDeck() {
        if (mazzo.isEmpty()) {
            mazzo.copia(pilaScarti);
            mazzo.copriMazzo();
            mazzo.mischia();
            pilaScarti.clear();
        }
    }

    /**
     * Metodo che si occupa di gestire il cambio del giocatore.
     * Viene incrementato il giocatore corrente, se il giocatore corrente è l'ultimo
     * viene settato a 0.
     */
    public void nextPlayer() {
        currentPlayer = (currentPlayer + 1) % players.size();
    }

    /**
     * Metodo che si occupa di gestire il giocatore corrente.
     *
     * @return Giocatore : rappresenta il giocatore corrente
     */
    public Giocatore getGiocatore() {
        return players.get(currentPlayer);
    }


    /**
     * Metodo che fornisce la mano del giocatore corrente
     *
     * @return ArrayList<Carta> : rappresenta la mano del giocatore corrente
     */
    public Mano getMano() {
        return players.get(currentPlayer).getMano();
    }


    /**
     * Metodo che si occupa dello scambio di una carta all'interno della mano del giocatore.
     * Esso controlla il valore della carta passata in input con uno switch e la mette al posto corretto, scartando in caso la carta al posto i
     *
     * @param c: rappresenta la carta che viene pescata dal mazzo
     */
    public void gioco(Carta c) {
        while (!gameOver()) {
            Giocatore giocatoreCorrente = getGiocatore();
            // Esegui il turno del giocatore corrente usando il metodo swap
            Carta cartaScartata = swap(c);
            // Aggiorna la carta attuale con la carta scartata
            c = cartaScartata != null ? cartaScartata : getMazzo().pesca();
            if (c.isCoperta())
                c.scopri();
        }
        System.out.println("Il giocatore " + getGiocatore().getUsername() + " ha vinto!");
    }

    /**
     * Metodo che si occupa dello scambio di una carta all'interno della mano del giocatore.
     *
     * @param c : rappresenta la carta da scambiare
     * @return Carta : rappresenta la carta scambiata
     */
    public Carta swap(Carta c) {
        switch (c.getValore()) {
            default -> {
                Carta temp = getGiocatore().getCartaMano(c.getValore().ordinal());
                temp.scopri();
                if (c.getValore().equals(temp.getValore())) {
                    pilaScarti.aggiungi(c);
                    nextPlayer();
                } else {
                    getMano().set(c.getValore().ordinal(), c);
                    return temp;
                }
            }
            case JACK, REGINA -> {
                pilaScarti.aggiungi(c);
                nextPlayer();
                return null;
            }
            case RE -> {
                int index = IntStream.range(0, getMano().size()).filter(i -> getGiocatore().getCartaMano(i).isCoperta()).findFirst().orElse(-1);
                if (index != -1) {
                    Carta temp = getGiocatore().getCartaMano(index);
                    temp.scopri();
                    getMano().set(index, c);
                    return temp;
                } else {
                    pilaScarti.aggiungi(c);
                    nextPlayer();
                }
            }
        }
        return null;
    }

    /**
     * Metodo che si occupa di prendere il mazzo della sessione di gioco corrente.
     *
     * @return Mazzo : rappresenta il mazzo
     */
    public Mazzo getMazzo() {
        return mazzo;
    }

    /**
     * Metodo che si occupa di prendere la pila degli scarti della sessione di gioco corrente.
     *
     * @return Mazzo : rappresenta la pila degli scarti
     */
    public Mazzo getScarti() {
        return pilaScarti;
    }
}

