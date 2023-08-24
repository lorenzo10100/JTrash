package JTrash;


import JTrash.Controller.MainController;
import javax.swing.*;

public class JTrash {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainController::new);
    }
}
