package JTrash.Controller;

import JTrash.Model.Utente;
import JTrash.View.AvatarChoiceGUI;

import javax.swing.*;

public class AvatarController {
    private Utente utente;
    private AvatarChoiceGUI avatarGUI;

    public AvatarController(Utente utente, AvatarChoiceGUI avatarGUI){
        this.utente = utente;
        this.avatarGUI = avatarGUI;
    }

    public void setAvatar(){
        utente.setAvatar(avatarGUI.getImage().toString());
    }

    public void conferma(){
        if(avatarGUI.getImage() == null)
            JOptionPane.showMessageDialog(null,"Seleziona un avatar!", "Errore", JOptionPane.ERROR_MESSAGE);
        else {
            setAvatar();
            JOptionPane.showMessageDialog(null, "Avatar impostato con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
            avatarGUI.getFrame().dispose();
        }

    }

    public String getNickname(){
        return utente.getUsername();
    }

}
