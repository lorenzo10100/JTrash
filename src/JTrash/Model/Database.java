package JTrash.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Questa classe rappresenta un semplice database per la gestione degli utenti.
 * Consente di aggiungere, recuperare, salvare e caricare utenti.
 */
public class Database {
    private ArrayList<Utente> utenti;

    /**
     * Costruttore della classe Database.
     * Inizializza l'elenco degli utenti.
     */
    public Database() {
        utenti = new ArrayList<>();
    }

    /**
     * Aggiunge un utente al database.
     *
     * @param user l'utente da aggiungere
     */
    public void addUtente(Utente user) {
        utenti.add(user);
    }


    /**
     * Salva l'elenco degli utenti in un file specificato.
     *
     * @param file il file in cui salvare gli utenti
     * @throws IOException in caso di errori durante la scrittura del file
     */
    public void salva(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream os = new ObjectOutputStream(fos);

        Utente[] arrayUtenti = utenti.toArray(new Utente[utenti.size()]);
        os.writeObject(arrayUtenti);

        os.close();
        fos.close();
    }

    /**
     * Carica l'elenco degli utenti da un file specificato.
     *
     * @param file il file da cui caricare gli utenti
     * @throws IOException in caso di errori durante la lettura del file
     */
    public void carica(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream is = new ObjectInputStream(fis);

        try {
            Utente[] utentiCaricati = (Utente[]) is.readObject();

            utenti.clear();
            utenti.addAll(Arrays.asList(utentiCaricati));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        is.close();
        fis.close();
    }

    /**
     * Trova un utente nel database in base a username e password.
     *
     * @param username l'username dell'utente
     * @param password la password dell'utente
     * @return l'utente corrispondente, se trovato; altrimenti, null
     */
    public Utente findUtente(String username, String password) {
        for (Utente u : utenti) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password))
                return u;
        }
        return null;
    }

    /**
     * Aggiorna i dati di un utente nel database.
     *
     * @param user l'utente con i dati aggiornati
     */
    public void aggiornaDati(Utente user) {
        for (Utente u : utenti) {
            if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
                u.setLivello(user.getLivello());
                u.setVittorie(user.getVinte());
                u.setSconfitte(user.getPerse());
                u.setAvatar(user.getAvatar());
            }
        }
        try {
            salva(new File("database.ser"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

