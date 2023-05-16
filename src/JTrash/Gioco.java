package JTrash;

import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 *  Classe che si occupa della gestione del gioco.
 *  Viene gestita la logica del gioco di carte Trash e vengono gestite le
 *  varie regole del gioco.
 */

public class Gioco {

    /**
     * giocatore corrente
     */
    private int currentPlayer;

    /**
     * numero di giocatori
     */
    private Giocatore[] players;

    /**
     * rappresenta il mazzo da gioco
     */
    private Mazzo mazzo;


    private ArrayList<ArrayList<Carta>> playerHand;

    /**
     * Array che rappresenta la pila scarti del gioco
     */
    private ArrayList<Carta> pilaScarti;


    /**
     * Costruttore della sessione di gioco, al suo interno inoltre vi è tutta la logica
     * che concerne il gioco Trash e tutte le varie casistiche e regole di gioco.
     * @param players Giocatore[] : rappresenta un array di giocatori per la sessione di gioco(massimo 4)
     *                    se players.length = 1 oppure players.length > 4 viene sollevata un'eccezione
     */
    public Gioco(Giocatore[] players) {
        this.players = players;
        if (players.length < 2 || players.length > 4) {
            throw new IllegalArgumentException("Numero di giocatori non valido, impossibile iniziare il gioco");
        }
        /**
         * rappresenta un'ArrayList, dove ogni indice rappresenta un giocatore, dove ogni giocatore
         * avrà la sua mano di gioco
         */
        playerHand = new ArrayList<>(players.length);
        pilaScarti = new ArrayList<>();
        if (players.length == 2) {
            mazzo = new Mazzo(1);
        }
        if (players.length >= 3) {
            mazzo = new Mazzo(2);
        }
        mazzo.mischia();
        currentPlayer = 0;
        for (int i = 0; i < players.length; i++) {
            ArrayList<Carta> mano = new ArrayList<>(mazzo.pesca(10));
            playerHand.add(mano);
        }
    }

    public void start(Gioco game) {
        Carta c = mazzo.pesca();
        c.scopri();
        while (true) {
            if (currentPlayer == players.length) {
                currentPlayer = 0;
            }
            
            if(gameOver()){
                System.out.println("Il giocatore " + players[currentPlayer].getNickname() + " ha vinto!");
                break;
            }

            ArrayList<Carta> hand = playerHand.get(currentPlayer);

            switch (c.valore) {//TODO fixare il caso dove uno o piu' giocatori ha la carta c in mano
                default -> {
                    Carta c2 = playerHand.get(currentPlayer).get(c.valore.ordinal());
                    c2.scopri();
                    System.out.println(c);
                    if (c.valore.equals(c2.valore)) {
                        pilaScarti.add(c);
                        currentPlayer += 1;
                        c = mazzo.pesca();
                        c.scopri();
                        continue;
                    }
                    playerHand.get(currentPlayer).set(c.valore.ordinal(), c);
                    System.out.println(playerHand.get(currentPlayer));
                    System.out.println(c2);
                    c = c2;
                }
                case JACK, REGINA -> {
                    pilaScarti.add(c);
                    currentPlayer += 1;
                    c = mazzo.pesca();
                    c.scopri();
                }
                case RE -> {
                    OptionalInt index = IntStream.range(0, hand.size()).filter(i -> hand.get(i).isCoperta()).findFirst();
                    if (index.isPresent()) {
                        int indice = index.getAsInt();
                        Carta c2 = hand.get(indice);
                        c2.scopri();
                        hand.set(indice, c);
                        c = c2;
                    } else {
                        pilaScarti.add(c);
                        currentPlayer += 1;
                        c = mazzo.pesca();
                        c.scopri();
                    }
                }
            }
        }
    }

    public boolean gameOver(){
        return IntStream.range(1, playerHand.get(currentPlayer).size()).allMatch(i-> playerHand.get(currentPlayer).get(i-1).valore.ordinal() < playerHand.get(currentPlayer).get(i).valore.ordinal());
    }


    public static void main(String[] args) {
        Giocatore[] players;
        Giocatore g1 = new Giocatore("G1");
        Giocatore g2 = new Giocatore("G2");
        players = new Giocatore[]{g1, g2};
        Gioco game = new Gioco(players);
        game.start(game);
    }
}

