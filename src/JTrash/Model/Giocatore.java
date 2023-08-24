package JTrash.Model;

import java.io.Serializable;

/**
 * classe Giocatore che modella e permette di costruire un
 * giocatore artificiale(con solo il nome)
 */
public class Giocatore implements Serializable {
    private static final long serialVersionUID = 1L;
	private String nome;
    private transient Mano mano;

    /**
     * Costruttore della classe Giocatore.
     *
     * @param nome il nome del giocatore
     */
    public Giocatore(String nome) {
        this.nome = nome;
        mano = new Mano();
    }

    /**
     * Costruttore vuoto per la serializzazione di utente.
     * Questo costruttore è utilizzato per la serializzazione e non dovrebbe essere utilizzato direttamente.
     */
    public Giocatore() {
    }

    /**
     * Restituisce il nome del giocatore.
     *
     * @return il nome del giocatore
     */
    public String getUsername() {
        return nome;
    }

    /**
     * Restituisce una carta dalla mano del giocatore.
     *
     * @param i l'indice della carta nella mano
     * @return la carta nella posizione specificata
     */
    public Carta getCartaMano(int i) {
        return mano.getCarta(i);
    }

    /**
     * Restituisce la mano del giocatore.
     *
     * @return la mano del giocatore
     */
    public Mano getMano() {
        return mano;
    }

    /**
     * Imposta la mano del giocatore.
     *
     * @param mano la mano da impostare
     */
    public void setMano(Mano mano) {
        this.mano = mano;
    }

    /**
     * Imposta il nome del giocatore.
     *
     * @param nome il nome da impostare
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}