package JTrash.Controller;

import JTrash.Model.Carta;
import JTrash.Model.Mazzo;
import JTrash.Model.Utente;
import JTrash.View.UtenteGUI;

public class UtenteController {
    private Carta c;
    private Mazzo mazzo;
    private Utente utente;
    private UtenteGUI utenteGUI;

    public UtenteController(Utente utente, UtenteGUI utenteGUI){
        this.utente = utente;
        this.utenteGUI = utenteGUI;
    }


}
