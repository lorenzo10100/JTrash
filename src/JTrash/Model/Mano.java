package JTrash.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Mano extends Observable {
    private ArrayList<Carta> carte;
    private List<Observer> observers;

    public Mano(){
        carte = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void addCarta(Carta c){
        carte.add(c);
    }

    public Carta getCarta(int i){
        return carte.get(i);
    }

    public ArrayList<Carta> getCarte(){
        return carte;
    }

    public int size(){
        return carte.size();
    }

    public void set(int i, Carta c){
        carte.set(i, c);
        setChanged();
        notifyObservers(carte);
    }

    public void setCarte(ArrayList<Carta> carte){
        this.carte = carte;
    }

    @Override
    public String toString(){
        return carte.toString();
    }

    @Override
    public void addObserver(Observer o){
        observers.add(o);
    }

    @Override
    public void notifyObservers(Object arg){
        for(Observer o: observers){
            o.update(this, arg);
        }
    }

}
