package JTrash.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {
    private ArrayList<Utente> utenti;

    public Database(){
        utenti = new ArrayList<Utente>();
    }

    public void addUtente(Utente user){
        utenti.add(user);
    }

    public List<Utente> getUtenti(){
        return utenti;
    }

    public void salva(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream os = new ObjectOutputStream(fos);

        Utente[] arrayUtenti = utenti.toArray(new Utente[utenti.size()]);
        os.writeObject(arrayUtenti);

        os.close();
        fos.close();
    }

    public void carica(File file) throws IOException{
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
}
