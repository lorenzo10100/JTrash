package JTrash.Controller;

import JTrash.Model.Database1;
import JTrash.Model.Utente;
import JTrash.View.AvatarChoiceGUI;
import JTrash.View.Login;

import javax.swing.*;

public class LoginController {
    private Database1 db;
    private Login login;
    public LoginController(Login login) {
        this.login = login;
        db = new Database1();
    }
    public void addUtente(String nickname, String pass) {
        Utente u = new Utente(nickname, pass);
        db.addData(u);
    }

    public Utente retrieveUtente(String nickname, String pass) {
        String username = db.retrieveUsername(nickname);
        String password = db.retrievePassword(pass);
        if(username != null && password != null){
            return new Utente(username, password);
        }
        return null;
    }

    public void conferma(String nickname, String pass){
        String name = nickname.replaceAll("\\s+","");
        String password = pass.replaceAll("\\s+","");
        if(name.equals("") || password.equals(""))
            JOptionPane.showMessageDialog(null, "Inserisci nome e password", "Errore", JOptionPane.ERROR_MESSAGE);
        if(retrieveUtente(name, password) == null){
            addUtente(name, password);
            JOptionPane.showMessageDialog(null, "Registrazione effettuata con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null, "Login effettuato con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
            Utente u = retrieveUtente(name, password);
            login.dispose();
            new AvatarChoiceGUI(u);
        }
    }
}
