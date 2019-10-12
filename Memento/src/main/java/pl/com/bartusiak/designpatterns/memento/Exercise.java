package pl.com.bartusiak.designpatterns.memento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Token
{
    public int value = 0;

    public Token(int value)
    {
        this.value = value;
    }
}

class Memento
{
    public List<Token> tokens = new ArrayList<>();
}

class TokenMachine
{
    public List<Token> tokens = new ArrayList<>();

    public Memento addToken(int value)
    {
        Token token = new Token(value);
        tokens.add(token);
        return memento();
    }

    public Memento addToken(Token token)
    {
        tokens.add(new Token(token.value));
        return memento();
    }

    public void revert(Memento m)
    {
        tokens = m.tokens;
    }

    public Memento memento(){
        Memento memento = new Memento();
        for(Token tok: tokens){
            memento.tokens.add(new Token(tok.value));
        }
        return memento;
    }
}

public class Exercise {
}
