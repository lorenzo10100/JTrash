package JTrash;

import java.util.ArrayList;
import java.util.Collections;

public class Mazzo {
    private ArrayList<Carta> mazzo;
    private int nMazzi=1;

    //inizializzo un mazzo di carte tramite costruttore
    public Mazzo(int nMazzi){
        this.nMazzi = nMazzi;
        if(nMazzi == 1){
            mazzo = new ArrayList<>();
            creaMazzo();
        }
        else {
            mazzo = new ArrayList<>();
            for (int i = 0; i < nMazzi; i++) {
                creaMazzo();
            }
        }
    }



    private void creaMazzo(){
        for (Carta.Valore v : Carta.Valore.values()) {
            {
                for (Carta.Seme s : Carta.Seme.values()) {
                    Carta c = new Carta(v, s);
                    mazzo.add(c);
                }
            }
        }
    }


    @Override
    public String toString(){
        return mazzo.toString();
    }


    public void mischia(){
        Collections.shuffle(mazzo);
    }


    public Carta pesca(){
        return mazzo.remove(mazzo.size()-1);
    }

    public ArrayList<Carta> pesca(int n){
        ArrayList<Carta> carte = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            carte.add(mazzo.remove(mazzo.size()-1));
        }
        return carte;
    }

    public int size(){
        return mazzo.size();
    }
}
