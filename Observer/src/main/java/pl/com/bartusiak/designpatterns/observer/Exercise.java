package pl.com.bartusiak.designpatterns.observer;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

interface Observable{
    void notifyObservers();
}

interface Observer<T>{
    void valueChanged(T value);
}

class Game implements Observable
{
    public List<Rat> rats = new ArrayList<>();

    public void addRat(Rat rat){
        this.rats.add(rat);
        notifyObservers();
    }
    public void removeRat(Rat rat){
        rats.remove(rat);
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        rats.forEach(rat->rat.valueChanged(rats.size()));
    }
}

class Rat implements Closeable, Observer<Integer>
{
    private Game game;
    public int attack;

    public Rat(Game game)
    {
        this.game=game;
        this.game.addRat(this);
    }

    @Override
    public void close() throws IOException
    {
        game.removeRat(this);
    }

    @Override
    public void valueChanged(Integer value) {
        this.attack=value;
    }
}

public class Exercise {
}
