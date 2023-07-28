package JTrash.Controller;

import JTrash.Model.Mano;
import JTrash.View.ManoGUI;

import java.util.Observable;
import java.util.Observer;

public class ManoController implements Observer {
    private Mano mano;
    private ManoGUI manoGUI;

    public ManoController(Mano mano, ManoGUI manoGUI){
        this.mano = mano;
        this.manoGUI = manoGUI;
        mano.addObserver(this);
    }

    public void updateView(){
        manoGUI.updateMano(mano);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Mano){
            updateView();
        }
    }
}
