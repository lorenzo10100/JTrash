package JTrash;

import java.util.ArrayList;

/**
 *  Classe che si occupa della gestione del gioco.
 *  Viene gestita la logica del gioco di carte Trash e vengono gestite le
 *  varie regole del gioco.
 */

public class Gioco {

    /**
     * numero di giocatori che il costruttore userà per costruire la sessione di gioco
     */
    private int nGiocatori;

    /**
     * rappresenta un'ArrayList, dove ogni indice rappresenta un giocatore, dove ogni giocatore
     * avrà la sua mano di gioco
     */
    private ArrayList<ArrayList<Carta>> giocatori;


    /**
     * rappresenta il mazzo da gioco
     */
    private Mazzo mazzo;

    /**
     * variabile che serve per inizializzare la mano di ogni giocatore
     */
    private ArrayList<Carta> mano;

    /**
     * Array che rappresenta la pila scarti del gioco
     */
    private ArrayList<Carta> pilaScarti;


    /**
     * Costruttore della sessione di gioco, al suo interno inoltre vi è tutta la logica
     * che concerne il gioco Trash e tutte le varie casistiche e regole di gioco.
     * @param nGiocatori int : numero di giocatori all'interno della sessione (massimo 4)
     *                    se nGiocatori = 1 oppure nGiocatori > 4 viene sollevata un'eccezione
     */
    public Gioco(int nGiocatori) {
        this.nGiocatori = nGiocatori;
        if (nGiocatori < 2 || nGiocatori > 4) {
            throw new IllegalArgumentException("Numero di giocatori non valido, impossibile iniziare il gioco");
        }
        giocatori = new ArrayList<>(nGiocatori);
        pilaScarti = new ArrayList<>();
        if (nGiocatori == 2) {
            mazzo = new Mazzo(1);
        }
        if (nGiocatori >= 3) {
            mazzo = new Mazzo(2);
        }
        mazzo.mischia();
        for (int i = 0; i < nGiocatori; i++) {
            mano = new ArrayList<>();
            giocatori.add(mano);
            for (int j = 0; j < 10; j++) {
                giocatori.get(i).add(mazzo.pesca());
            }
        }

        int turno = 0;



        while(true) {
            Carta c = mazzo.pesca();
            c.scopri();

            System.out.println(c);

            switch (c.valore) {
                //TODO 1: Fixare parte in cui il giocatore pesca un jack o una regina, quindi vi è uno scarto e si passa al prossimo giocatore
                //TODO 2: aggiornare la variabile carta pescata, come fixare?
                //TODO 3: inoltre implementare la parte in cui il giocatore pesca un re o un joker
                case ASSO -> {
                    Carta c2 = giocatori.get(turno).get(0);
                    c2.scopri();
                    if(c.valore.equals(c2.valore)){
                        pilaScarti.add(c);
                        turno+=1;
                        continue;
                    }
                    giocatori.get(turno).set(0, c);
                    System.out.println(giocatori.get(turno));
                    System.out.println(c2);
                    c=c2;
                }
                case DUE -> {
                    Carta c2 = giocatori.get(turno).get(1);
                    c2.scopri();
                    if(c.valore.equals(c2.valore)){
                        pilaScarti.add(c);
                        turno+=1;
                        continue;
                    }
                    giocatori.get(turno).set(1, c);
                    System.out.println(giocatori.get(turno));
                    System.out.println(c2);
                    c=c2;
                }
                case TRE -> {
                    Carta c2 = giocatori.get(turno).get(2);
                    c2.scopri();
                    if(c.valore.equals(c2.valore)){
                        pilaScarti.add(c);
                        turno+=1;
                        continue;
                    }
                    giocatori.get(turno).set(2, c);
                    System.out.println(giocatori.get(turno));
                    System.out.println(c2);
                    c=c2;
                }
                case QUATTRO -> {
                    Carta c2 = giocatori.get(turno).get(3);
                    c2.scopri();
                    if(c.valore.equals(c2.valore)){
                        pilaScarti.add(c);
                        turno+=1;
                        continue;
                    }
                    giocatori.get(turno).set(3, c);
                    System.out.println(giocatori.get(turno));
                    System.out.println(c2);
                    c=c2;
                }
                case CINQUE -> {
                    Carta c2 = giocatori.get(turno).get(4);
                    c2.scopri();
                    if(c.valore.equals(c2.valore)){
                        pilaScarti.add(c);
                        turno+=1;
                        continue;
                    }
                    giocatori.get(turno).set(4, c);
                    System.out.println(giocatori.get(turno));
                    System.out.println(c2);
                    c=c2;
                }
                case SEI -> {
                    Carta c2 = giocatori.get(turno).get(5);
                    c2.scopri();
                    if(c.valore.equals(c2.valore)){
                        pilaScarti.add(c);
                        turno+=1;
                        continue;
                    }
                    giocatori.get(turno).set(5, c);
                    System.out.println(giocatori.get(turno));
                    System.out.println(c2);
                    c=c2;
                }
                case SETTE -> {
                    Carta c2 = giocatori.get(turno).get(6);
                    c2.scopri();
                    if(c.valore.equals(c2.valore)){
                        pilaScarti.add(c);
                        turno+=1;
                        continue;
                    }
                    giocatori.get(turno).set(6, c);
                    System.out.println(giocatori.get(turno));
                    System.out.println(c2);
                    c=c2;
                }
                case OTTO -> {
                    Carta c2 = giocatori.get(turno).get(7);
                    c2.scopri();
                    if(c.valore.equals(c2.valore)){
                        pilaScarti.add(c);
                        turno+=1;
                        continue;
                    }
                    giocatori.get(turno).set(7, c);
                    System.out.println(giocatori.get(turno));
                    System.out.println(c2);
                    c=c2;
                }
                case NOVE -> {
                    Carta c2 = giocatori.get(turno).get(8);
                    c2.scopri();
                    if(c.valore.equals(c2.valore)){
                        pilaScarti.add(c);
                        turno+=1;
                        continue;
                    }
                    giocatori.get(turno).set(8, c);
                    System.out.println(giocatori.get(turno));
                    System.out.println(c2);
                    c=c2;
                }
                case DIECI -> {
                    Carta c2 = giocatori.get(turno).get(9);
                    c2.scopri();
                    if(c.valore.equals(c2.valore)){
                        pilaScarti.add(c);
                        turno+=1;
                        continue;
                    }
                    giocatori.get(turno).set(9, c);
                    System.out.println(giocatori.get(turno));
                    System.out.println(c2);
                    c=c2;
                }
                case JACK, REGINA -> {
                    pilaScarti.add(c);
                    turno+=1;

                }
                //case RE -> giocatori.get(turno).add(1,c);

                //case JOKER1 -> giocatori.get(turno).add(1,c);
                //case JOKER2 -> giocatori.get(turno).add(1,c);

            }
        }
    }


    public static void main(String[] args) {
        new Gioco(2);
    }
}

