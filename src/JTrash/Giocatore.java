package JTrash;

import JTrash.Avatar;

public class Giocatore {
    /**
     * @author Lorenzo Scalera
     *
     *	classe Utente che modella e permette di costruire un
     *	utente umano e un giocatore artificiale(con solo il nome)
     *
     */
    private String nickname;
    private Avatar avatar;
    private int giocate, vinte, perse;
    private String livello;

    // costruttore per giocatori artificiali
    public Giocatore(String nickname) {
        this.nickname = nickname;
    }

    // costruttore per giocatori umani
    public Giocatore(String nickname, Avatar avatar, int giocate, int vinte, int perse, String livello) {
        this.nickname = nickname;
        this.avatar = avatar;
        this.giocate = giocate;
        this.vinte = vinte;
        this.perse = perse;
        this.livello = livello;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public int getGiocate() {
        return giocate;
    }

    public void setGiocate(int giocate) {
        this.giocate = giocate;
    }

    public int getVinte() {
        return vinte;
    }

    public void setVinte(int vinte) {
        this.vinte = vinte;
    }

    public int getPerse() {
        return perse;
    }

    public void setPerse(int perse) {
        this.perse = perse;
    }

    public String getLivello() {
        return livello;
    }
}