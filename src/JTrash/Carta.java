package JTrash;

import java.util.ArrayList;

public class Carta {

    protected enum Seme{QUADRI, FIORI, CUORI, PICCHE}
    protected enum Valore{ASSO, DUE, TRE, QUATTRO, CINQUE, SEI, SETTE, OTTO, NOVE, DIECI, JACK, REGINA, RE}

    private boolean coperta;
    Seme seme;
    Valore valore;


    public Carta(Valore valore, Seme seme){
        this.valore = valore;
        this.seme = seme;
        coperta = true;
    }


    private boolean isScoperta(){return !coperta;}

    public boolean isCoperta() {
        return coperta;
    }
    public void scopri(){coperta = false;}

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        if(isScoperta()){
            sb.append(valore.name()).append(" di ").append(seme.name());}
        else {sb.append("COPERTA");}
        return sb.toString();
    }
}

