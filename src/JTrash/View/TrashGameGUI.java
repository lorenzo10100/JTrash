package JTrash.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import JTrash.Model.Carta;
import JTrash.Model.Giocatore;
import JTrash.Model.Gioco2;

public class TrashGameGUI extends JFrame {
    private Gioco2 gioco;
    private JPanel playerHandPanel;
    private JLabel currentPlayerLabel;

    public TrashGameGUI(ArrayList<Giocatore> players) {
        gioco = new Gioco2(players);

        setTitle("Trash Card Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLayout(new BorderLayout());

        currentPlayerLabel = new JLabel("Current Player: " + gioco.getCurrentPlayer().getUsername());
        add(currentPlayerLabel, BorderLayout.NORTH);

        playerHandPanel = new JPanel();
        playerHandPanel.setLayout(new FlowLayout());
        updatePlayerHand();
        add(playerHandPanel, BorderLayout.CENTER);

        JButton drawButton = new JButton("Draw Card");
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDrawButtonClicked();
            }
        });

        JButton discardButton = new JButton("Discard Card");
        discardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDiscardButtonClicked();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(drawButton);
        buttonPanel.add(discardButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updatePlayerHand() {
        Giocatore currentPlayer = gioco.getCurrentPlayer();
        playerHandPanel.removeAll();

        for (Carta carta : currentPlayer.getMano().getCarte()) {
            JButton cardButton = new JButton(carta.toString());
            cardButton.setEnabled(carta.isCoperta());
            cardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onCardButtonClicked(carta);
                }
            });
            playerHandPanel.add(cardButton);
        }

        revalidate();
        repaint();
    }

    private void onCardButtonClicked(Carta carta) {
        gioco.gioco(carta);
        updatePlayerHand();
        currentPlayerLabel.setText("Current Player: " + gioco.getCurrentPlayer().getUsername());

        if (gioco.gameOver()) {
            // Handle game over condition
            JOptionPane.showMessageDialog(this, "Il giocatore " + gioco.getCurrentPlayer().getUsername() + " ha vinto!");
            // Add your code to handle the end of the game here (e.g., show a dialog, reset the game, etc.)
        }
    }

    private void onDrawButtonClicked() {
        Carta cartaPescata = gioco.getMazzo().pesca();
        gioco.getCurrentPlayer().getMano().addCarta(cartaPescata);
        updatePlayerHand();
    }

    private void onDiscardButtonClicked() {
        // Implement the discard logic here (if needed)
    }

    public static void main(String[] args) {
        // Create players (you can create your own players here)
        Giocatore player1 = new Giocatore("Player 1");
        Giocatore player2 = new Giocatore("Player 2");
        ArrayList<Giocatore> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TrashGameGUI gameGUI = new TrashGameGUI(players);
                gameGUI.setVisible(true);
            }
        });
    }
}