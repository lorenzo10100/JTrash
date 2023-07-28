package JTrash.View;

import JTrash.Controller.AvatarController;
import JTrash.Model.Utente;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;


/**
 * Classe che si occupa della gestione e della creazione di un avatar per un utente.
 */
public class AvatarChoiceGUI extends JFrame{
    private JFrame frame;
    private JPanel panel, buttons;
    private JButton conferma, scegli, logout;
    private JLabel immagine, nickname;
    private ImageIcon image;
    private AvatarController controller;
    /**
     * Costruttore della classe Avatar.
     * All'interno vi e' la creazione della finestra di scelta dell'avatar e la gestione dei vari componenti grafici.
     */
    public AvatarChoiceGUI(Utente user){
        controller = new AvatarController(user, this);
        frame = new JFrame("Scelta Avatar");
        panel = new JPanel(new GridBagLayout());
        buttons = new JPanel();

        nickname = new JLabel("Benvenuto, " + controller.getNickname() + "!");
        nickname.setFont(new Font("Arial", Font.BOLD, 20));
        if(user.getAvatar() == null)
            immagine = new JLabel("Scegli un avatar!");
        else
            immagine = new JLabel(image);
        conferma = new JButton("Conferma");
        scegli = new JButton("Scegli");
        logout = new JButton("Logout");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        conferma.addActionListener(e->{
            controller.conferma();
        });

        scegli.addActionListener(e->{
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File(System.getProperty("user.dir") + System.getProperty("file.separator")+ "src" + System.getProperty("file.separator") + "avatars"));
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
            image = new ImageIcon(fc.getSelectedFile().getAbsolutePath());
            user.setAvatar(fc.getSelectedFile().getAbsolutePath());
            Image img = image.getImage();
            Image newimg = img.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
            image = new ImageIcon(newimg);
            immagine.setIcon(image);
            immagine.setText("");
        });

        logout.addActionListener(e->{
            frame.dispose();
            new Login();
        });

        panel.add(nickname, gbc);
        panel.add(Box.createRigidArea(new Dimension(0, 10)), gbc);
        panel.add(immagine, gbc);
        panel.add(scegli, gbc);
        panel.add(Box.createRigidArea(new Dimension(0, 10)), gbc);
        panel.add(buttons, gbc);
        buttons.add(conferma);
        buttons.add(Box.createRigidArea(new Dimension(10, 0)));
        buttons.add(logout);

        frame.add(panel);
        frame.pack();
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public ImageIcon getImage(){
        return image;
    }

    public JFrame getFrame(){
        return frame;
    }
}