package JTrash.Model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Gioco2 {
    private int currentPlayer;
    private ArrayList<Giocatore> players;
    private Mazzo mazzo;
    private Mazzo pilaScarti;

    public Gioco2(ArrayList<Giocatore> players) {
        if (players.size() < 2 || players.size() > 4) {
            throw new IllegalArgumentException("Il numero di giocatori deve essere compreso tra 2 e 4.");
        }

        this.players = players;
        pilaScarti = new Mazzo();

        if (players.size() == 2) {
            mazzo = new Mazzo(1);
        } else {
            mazzo = new Mazzo(2);
        }

        mazzo.mischia();
        mazzo.copriMazzo();
        currentPlayer = new Random().nextInt(players.size());

        for (Giocatore g : players) {
            g.getMano().setCarte(mazzo.pesca(10));
        }
    }

    public List<Giocatore> getPlayers() {
        return players;
    }

    public Mazzo getMazzo() {
        return mazzo;
    }

    public Giocatore getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public void nextPlayer() {
        currentPlayer = (currentPlayer + 1) % players.size();
    }

    public void gioco(Carta c) {
        if (mazzo.isEmpty()) {
            swapDeck();
        }
        swap(c);
    }

    public void swapDeck() {
        mazzo.copia(pilaScarti);
        mazzo.copriMazzo();
        mazzo.mischia();
        pilaScarti.clear();
    }


    public Mano getMano(){
        return players.get(currentPlayer).getMano();
    }

    public Giocatore getGiocatore(){
        return players.get(currentPlayer);
    }
    public void swap(Carta c) {
        Giocatore currentPlayer = getCurrentPlayer();
        int index = currentPlayer.getMano().getCarte().indexOf(c);

        if (index == -1) {
            throw new IllegalArgumentException("La carta non è presente nella mano del giocatore.");
        }

        Carta temp = currentPlayer.getMano().getCarte().get(index);

        switch (temp.getValore()) {
            case JACK, REGINA -> {
                pilaScarti.aggiungi(temp);
                currentPlayer.getMano().getCarte().remove(index);
                nextPlayer();
            }
            case RE -> {
                int reIndex = IntStream.range(0, getMano().size()).filter(i -> getGiocatore().getCartaMano(i).isCoperta()).findFirst().orElse(-1);
                if (reIndex != -1) {
                    Carta reCard = currentPlayer.getMano().getCarte().get(reIndex);
                    reCard.scopri();
                    currentPlayer.getMano().getCarte().set(reIndex, temp);
                    currentPlayer.getMano().getCarte().remove(index);
                } else {
                    pilaScarti.aggiungi(temp);
                    currentPlayer.getMano().getCarte().remove(index);
                    nextPlayer();
                }
            }
            default -> {
                int cardIndex = temp.getValore().ordinal() + 1;
                if (cardIndex < currentPlayer.getMano().getCarte().size()) {
                    Carta nextCard = currentPlayer.getMano().getCarte().get(cardIndex);
                    nextCard.scopri();
                    currentPlayer.getMano().getCarte().set(cardIndex, temp);
                    currentPlayer.getMano().getCarte().remove(index);
                } else {
                    pilaScarti.aggiungi(temp);
                    currentPlayer.getMano().getCarte().remove(index);
                    nextPlayer();
                }
            }
        }
    }

    public boolean gameOver(){
        return IntStream.range(1, getMano().size()).allMatch(i -> getGiocatore().getCartaMano(i-1).getValore().ordinal() < getGiocatore().getCartaMano(i).getValore().ordinal());
    }
}

