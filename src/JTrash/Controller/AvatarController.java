package JTrash.Controller;

import JTrash.Model.Utente;
import JTrash.View.AvatarPanel;
import JTrash.View.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

/**
 * Questa classe rappresenta il controller per la gestione degli avatar dell'utente.
 * Si occupa di interagire con il pannello dell'avatar e il frame principale.
 */
public class AvatarController {
    private Utente user;
    private AvatarPanel avatarPanel;
    private MainFrame mainFrame;

    /**
     * Costruttore della classe AvatarController.
     *
     * @param user        l'utente associato al controller
     * @param avatarPanel il pannello degli avatar
     * @param mainFrame   il frame principale dell'applicazione
     */
    public AvatarController(Utente user, AvatarPanel avatarPanel, MainFrame mainFrame) {
        this.user = user;
        this.avatarPanel = avatarPanel;
        this.mainFrame = mainFrame;
    }

    /**
     * Inizializza la vista del pannello dell'avatar.
     * Imposta il testo di benvenuto con il nome dell'utente.
     */
    private void initView() {
        avatarPanel.getNome().setText("Benvenuto " + user.getUsername() + "!");
        avatarPanel.getConferma().addActionListener(e -> saveAvatar());
        avatarPanel.getScegli().addActionListener(e -> chooseAvatar());
        avatarPanel.getLogout().addActionListener(e -> logout());
    }

    /**
     * Inizializza il controller per la gestione dell'avatar.
     * Collega il pannello dell'avatar al frame principale
     */
    private void initController() {
        mainFrame.setAvatarPanel(avatarPanel);
        mainFrame.switchToAvatar();

    }

    /**
     * Salva l'avatar dell'utente.
     * Converte il percorso dell'avatar in un'icona e la ridimensiona prima di impostarla nel pannello.
     * Mostra messaggi di avviso in base all'esito del salvataggio.
     */
    private void saveAvatar() {
        if(user.getAvatar()!=null){
            String path = System.getProperty("user.dir")
                    + System.getProperty("file.separator") + "avatars"
                    + System.getProperty("file.separator") + user.getAvatar();
            ImageIcon tmp = new ImageIcon(path);
            Image img = tmp.getImage();
            Image newimg = img.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
            avatarPanel.getAvatar().setIcon(new ImageIcon(newimg));
            avatarPanel.getAvatar().setText("");
        }
        if (avatarPanel.getAvatar().getIcon() != null){
            JOptionPane.showMessageDialog(null, "Avatar salvato!", "Info", JOptionPane.INFORMATION_MESSAGE);
            mainFrame.switchToSetup();
        }
        else
            JOptionPane.showMessageDialog(null, "Scegli un avatar!", "Errore", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Permette all'utente di scegliere un nuovo avatar.
     * Apre un selettore di file, filtra i file PNG e imposta l'avatar selezionato nell'utente.
     * Mostra l'avatar scelto nel pannello dopo averlo ridimensionato.
     */
    private void chooseAvatar() {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "avatars"));
        fc.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".png");
            }

            @Override
            public String getDescription() {
                return "PNG Images";
            }
        });
        fc.showOpenDialog(null);
        String path = fc.getSelectedFile().getAbsolutePath();
        ImageIcon image = new ImageIcon(path);
        user.setAvatar(path.substring(path.lastIndexOf(System.getProperty("file.separator")) + 1));
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(newimg);
        avatarPanel.getAvatar().setIcon(image);
        avatarPanel.getAvatar().setText("");
    }

    /**
     * Esegue il logout dell'utente.
     * Cambia la vista nel frame principale passando alla schermata di login.
     */
    private void logout() {
        mainFrame.switchToLogin();
    }

    /**
     * Inizializza la vista e il controller dell'avatar.
     */
    public void init() {
        initView();
        initController();
    }

    /**
     * Restituisce l'utente associato al controller.
     *
     * @return l'utente associato al controller
     */
    public Utente getUser() {
        return user;
    }
}
