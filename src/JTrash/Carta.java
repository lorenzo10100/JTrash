package JTrash;

public class Carta {

    protected enum Seme{QUADRI, FIORI, CUORI, PICCHE}
    protected enum Valore{ASSO, DUE, TRE, QUATTRO, CINQUE, SEI, SETTE, OTTO, NOVE, DIECI, JACK, REGINA, RE, JOKER1, JOKER2}

    private boolean coperta;
    Seme seme;
    Valore valore;


    //costruttore per carte normali
    public Carta(Valore valore, Seme seme){
        this.valore = valore;
        this.seme = seme;
        this.coperta = true;
    }

    //costruttore per jolly
    public Carta(Valore valore){
        this.valore = valore;
    }

    public void scopri(){coperta = !coperta;}

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        if(!coperta){
            if(valore == Valore.JOKER1 || valore == Valore.JOKER2){sb.append(valore.name());}
            else{
                sb.append(valore.name()).append(" di ").append(seme.name());}
        }
        else {sb.append("Carta coperta");}
        return sb.toString();
    }
}

