package JTrash.Controller;

import JTrash.Model.Carta;
import JTrash.View.HandPanel;

/**
 * Questa classe rappresenta il controller per la classe Carta.
 */
public class CartaController {
    /**
     * Costruttore della classe CartaController.
     * Aggiunge un observer alla carta.
     *
     * @param carta     la carta da osservare
     * @param handPanel il pannello della mano
     */
    public CartaController(Carta carta, HandPanel handPanel) {
        carta.addObserver(handPanel);
    }
}
