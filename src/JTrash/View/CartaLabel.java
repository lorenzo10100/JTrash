package JTrash.View;

import JTrash.Model.Carta;

import javax.swing.*;

/**
 * Questa classe rappresenta una JLabel personalizzata per visualizzare una carta da gioco.
 * Ogni oggetto di questa classe contiene un'istanza di Carta e mostra l'immagine associata a quella carta.
 */
public class CartaLabel extends JLabel {
    private Carta carta;

    /**
     * Costruttore della classe CartaLabel.
     * Crea un'istanza di CartaLabel associata alla carta specificata e aggiorna l'immagine dell'oggetto.
     *
     * @param carta la carta associata a questa CartaLabel
     */
    public CartaLabel(Carta carta) {
        this.carta = carta;
        updateIcon();
    }

    /**
     * Aggiorna l'immagine della CartaLabel con l'immagine associata alla carta.
     */
    public void updateIcon() {
        setIcon(new ImageIcon(getClass().getResource(carta.getImage())));
    }
}

