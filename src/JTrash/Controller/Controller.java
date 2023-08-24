package JTrash.Controller;

import JTrash.Model.Database;
import JTrash.Model.Utente;

import java.io.File;
import java.io.IOException;

/**
 * Questa classe rappresenta il controller principale per la gestione degli utenti e del database.
 * Si occupa di interagire con il database e fornire metodi per aggiungere, salvare, caricare, cercare e aggiornare utenti.
 */
public class Controller {

    private Database database = new Database();

    /**
     * Aggiunge un nuovo utente al database.
     *
     * @param username l'username dell'utente da aggiungere
     * @param password la password dell'utente da aggiungere
     */
    public void addUtente(String username, String password) {
        Utente user = new Utente(username, password);
        database.addUtente(user);
    }

    /**
     * Salva il database in un file specificato.
     *
     * @param file il file in cui salvare il database
     * @throws IOException in caso di errori durante la scrittura del file
     */
    public void salva(File file) throws IOException {
        database.salva(file);
    }

    /**
     * Carica il database da un file specificato.
     *
     * @param file il file da cui caricare il database
     * @throws IOException in caso di errori durante la lettura del file
     */
    public void carica(File file) throws IOException {
        database.carica(file);
    }

    /**
     * Trova un utente nel database in base a username e password.
     *
     * @param username l'username dell'utente
     * @param password la password dell'utente
     * @return l'utente corrispondente, se trovato; altrimenti, null
     */
    public Utente findUtente(String username, String password) {
        return database.findUtente(username, password);
    }

    /**
     * Aggiorna i dati di un utente nel database.
     *
     * @param user l'utente con i dati aggiornati
     */
    public void aggiornaDati(Utente user) {
        database.aggiornaDati(user);
    }
}

