package pl.com.bartusiak.designpatterns.chain;

import java.util.ArrayList;
import java.util.List;

abstract class Creature
{
    protected int baseAttack, baseDefense;
    protected Game game;

    public abstract int getAttack();
    public abstract int getDefense();
}

class Goblin extends Creature
{
    public Goblin(Game game)
    {
        this.game=game;
        baseAttack=1;
        baseDefense=1;
    }

    @Override
    public int getAttack()
    {
        return baseAttack+ countGoblinKings();
    }

    @Override
    public int getDefense()
    {
        return baseDefense+countGoblins()-1;
    }

    public int countGoblins(){
        return game.creatures.size();
    }

    public int countGoblinKings(){
        return (int) game.creatures.stream().filter(e->e.getClass()==GoblinKing.class).count();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"{" +
                "attack=" + getAttack() +
                ", defense=" + getDefense() +
                '}';
    }
}

class GoblinKing extends Goblin
{
    public GoblinKing(Game game)
    {
        super(game);
        baseAttack=2;
        baseDefense=2;
    }
}

enum Statistic
{
    ATTACK, DEFENSE
}

class Game
{
    public List<Creature> creatures = new ArrayList<>();
}

public class Exercise {
    public static void main(String[] args) {
        Game game = new Game();
        Goblin goblin = new Goblin(game);
        System.out.println(goblin);
    }
}
