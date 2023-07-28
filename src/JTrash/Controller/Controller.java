package JTrash.Controller;

import JTrash.Model.Database;
import JTrash.Model.Utente;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Controller {

    Database database = new Database();

    public void addUtente(String username, String password, String avatar){
        Utente user = new Utente(username, password);
        user.setAvatar(avatar);

        database.addUtente(user);
    }

    public List<Utente> getUtenti(){return database.getUtenti();}

    public void salva(File file) throws IOException {
        database.salva(file);
    }

    public void carica(File file) throws IOException{
        database.carica(file);
    }
}
