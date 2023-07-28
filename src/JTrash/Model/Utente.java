package JTrash.Model;

import javax.swing.*;
import java.io.Serializable;


/**
 * Classe che modella e permette di costruire un
 * giocatore umano con nome, avatar e statistiche
 *
 */
public class Utente extends Giocatore implements Serializable {
    private static int id;
    private String password;
    private String avatar;
    private int vinte;
    private int perse;
    private String livello;

    /**
     * Costruttore della classe Utente
     *
     * @param username String: nome del giocatore
     */
    public Utente(String username, String password) {
        super(username);
        this.password = password;
        vinte = 0;
        perse = 0;
        livello = "Livello: 0";
        id++;
    }
    public Utente(){}; // costruttore vuoto per la serializzazione
    /**
     * Metodo che restituisce il numero di partite giocate
     *
     * @return int: numero di partite giocate
     */
    public int getGiocate() {
        return vinte + perse;
    }

    /**
     * Metodo che incrementa il numero di vittorie dell'utente
     */
    public void vittoria() {
        vinte++;
    }

    /**
     * Metodo che incrementa il numero di sconfitte dell'utente
     */
    public void sconfitta() {
        perse++;
    }

    public int getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Metodo che restituisce la password dell'utente
     *
     * @return String: password dell'utente
     */
    public String getPassword() {
        return password;
    }

    /**
     * Metodo che restituisce l'avatar dell'utente
     *
     * @return Avatar: avatar dell'utente
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Metodo che setta l'avatar dell'utente
     *
     * @param avatar Avatar: avatar dell'utente
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * Metodo che restituisce il numero di vittorie
     *
     * @return int: numero di vittorie
     */
    public int getVinte() {
        return vinte;
    }


    /**
     * Metodo che restituisce il numero di sconfitte
     *
     * @return int: numero di sconfitte
     */
    public int getPerse() {
        return perse;
    }

    /**
     * Metodo che restituisce il livello dell'utente
     *
     * @return String: livello dell'utente
     */
    public String getLivello() {
        return livello;
    }

    /**
     * Metodo che incrementa il livello dell'utente
     */
    public void addExp() {
        livello = "Livello: ";
        int xp_vittoria = getVinte() * 50;
        int xp_sconfitta = getPerse() * 10;
        int totale_xp = xp_vittoria + xp_sconfitta;
        livello += (int) (totale_xp / 100);
    }
}