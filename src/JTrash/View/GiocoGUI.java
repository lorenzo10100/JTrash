package JTrash.View;

import JTrash.Controller.ManoController;
import JTrash.Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GiocoGUI extends JFrame {
    private Gioco gioco;
    private JPanel userPanel, principale, mazzi;
    private ManoGUI mano;
    private GiocatoreGUI giocatore;
    private JLabel cartaAttuale, deck, scarto, turno;
    private JButton pesca, posiziona;
    private Mazzo mazzoModel, scartoModel;
    private Carta cartaAttualeModel;
    private ManoController controller;
    private static final ImageIcon DORSO = new ImageIcon("src/sprites/COPERTA.png");

    public GiocoGUI(Utente user, int giocatori){
        controller = new ManoController(user.getMano(), new ManoGUI(user.getMano()));
        setTitle("JTrash");
        userPanel = new JPanel();
        principale = new JPanel(new BorderLayout());
        cartaAttuale = new JLabel();
        deck = new JLabel();
        mano = new ManoGUI(user.getMano());
        scarto = new JLabel();
        mazzi = new JPanel();
        mazzi.setLayout(new BoxLayout(mazzi, BoxLayout.X_AXIS));
        turno = new JLabel();
        pesca = new JButton("Pesca");
        posiziona = new JButton("Posiziona Carta");
        setLabels();


        ArrayList<Giocatore> players = new ArrayList<>();

        for(int i=0; i<giocatori; i++){
            Giocatore g = new Giocatore("CPU "+i);
            players.add(g);
        }
        players.add(user);

        setMani(players);
        setViews(players);
        updateLabels();


        add(principale);
        add(turno, BorderLayout.EAST);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        setPreferredSize(new Dimension(screenSize.width,screenSize.height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }

    private void initializeDecks(){
        mazzoModel = gioco.getMazzo();
        scartoModel = gioco.getScarti();
    }

    private void setMani(ArrayList<Giocatore> players){
        gioco = new Gioco(players);
        initializeDecks();
    }

    private void setViews(ArrayList<Giocatore> players){
        for(Giocatore g: players){
            if(g instanceof Utente){
                UtenteGUI ug = new UtenteGUI((Utente) g);
                setPlay(ug, (Utente) g, players);
                setDraw(ug);
                userPanel.add(ug);
                userPanel.add(mano);
                principale.add(userPanel, BorderLayout.SOUTH);
            }else{
                JPanel cpuPanel = new JPanel();
                giocatore = new GiocatoreGUI(g);
                cpuPanel.add(giocatore);
                principale.add(cpuPanel, BorderLayout.NORTH);
            }
        }
    }

    private void setLabels(){
        cartaAttuale.setPreferredSize(new Dimension(113,124));
        deck.setPreferredSize(new Dimension(124,113));
        scarto.setPreferredSize(new Dimension(124,113));
        mazzi.add(deck);
        mazzi.add(scarto);
        mazzi.add(Box.createHorizontalStrut(25));
        mazzi.add(cartaAttuale);
        principale.add(mazzi, BorderLayout.CENTER);
    }

    private void updateLabels(){
        if(!mazzoModel.isEmpty()) {
            deck.setIcon(DORSO);
            deck.setText("");
        }
        else{
            deck.setIcon(null);
            deck.setText("Mazzo vuoto");
        }
        if(!scartoModel.isEmpty()) {
            scarto.setIcon(scartoModel.getUltimaCarta().getImage());
        }
        else{
            scarto.setIcon(null);
        }
        if(cartaAttualeModel != null) {
            cartaAttuale.setIcon(cartaAttualeModel.getImage());
            cartaAttuale.setText("");
        }
        else{
            cartaAttuale.setIcon(null);
            cartaAttuale.setText("Nessuna carta");
        }
        turno.setText(gioco.getGiocatore().getUsername());
    }
    private void updateLabels(ArrayList<Giocatore> players) {
        for(Giocatore g:players)
            for(Carta c:g.getMano().getCarte()){
                if(!c.isCoperta() && g instanceof Utente)
                    controller.update(g.getMano(), g.getMano().getCarte());
                else if(!c.isCoperta())
                    controller.update(g.getMano(), g.getMano().getCarte());
            }
    }

    private void setPlay(UtenteGUI ug, Utente user, ArrayList<Giocatore> players){
            ug.play().addActionListener(e-> {
                if(cartaAttualeModel==null)
                    JOptionPane.showMessageDialog(null, "Nessuna carta disponibile, pesca!");
                else {
                    Carta temp = gioco.swap(cartaAttualeModel);
                    if (temp != null) {
                        cartaAttualeModel = temp;
                        cartaAttualeModel.scopri();
                    }
                    else
                        cartaAttualeModel = null;
                }
                updateLabels();
                updateLabels(players);
                if(gioco.gameOver()){
                    Giocatore vincitore = gioco.getGiocatore();
                    if(vincitore instanceof Utente){
                        JOptionPane.showMessageDialog(null, "Hai vinto!");
                        user.vittoria();
                        user.addExp();
                    }
                    else {
                        user.sconfitta();
                        user.addExp();
                        JOptionPane.showMessageDialog(null, "Il giocatore " + vincitore.getUsername() + " ha vinto!");
                        System.exit(0);
                    }
                }
            });
    }

    private void setDraw(UtenteGUI ug){
        ug.draw().addActionListener(e->{
            drawCard();
            updateLabels();
        });
    }

    private void drawCard(){
        if(!mazzoModel.isEmpty()) {
            if(cartaAttualeModel == null) {
                cartaAttualeModel = mazzoModel.pesca();
                cartaAttualeModel.scopri();
            }
            else
                JOptionPane.showMessageDialog(null, "E' gia' stata pescata una carta!");
        }
        gioco.swapDeck();
        initializeDecks();
    }

    public static void main(String[] args) {
        Utente user = new Utente("Pippo", "1234");
        GiocoGUI giocoGUI = new GiocoGUI(user, 1);
    }
}


