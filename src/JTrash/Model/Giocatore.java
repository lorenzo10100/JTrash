package JTrash.Model;

import java.util.Observable;

/**
 *	classe Giocatore che modella e permette di costruire un
 *	giocatore artificiale(con solo il nome)
 *
 */
public class Giocatore extends Observable {
    private String nome;
    private Mano mano;

    /**
     * Costruttore della classe Giocatore
     * @param nome String: nome del giocatore
     */
    public Giocatore(String nome) {
        this.nome = nome;
        mano = new Mano();
    }

    public Giocatore(){}; // costruttore vuoto per la serializzazione di utente
    /**
     * Metodo che restituisce il nome del giocatore
     * @return String: nome del giocatore
     */
    public String getUsername() {
        return nome;
    }

    public Carta getCartaMano(int i){
        return mano.getCarta(i);
    }

    public Mano getMano() {
        return mano;
    }
    public void setMano(Mano mano) {
        this.mano = mano;
    }

    public void setCartaMano(int i, Carta c){
        mano.set(i, c);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}