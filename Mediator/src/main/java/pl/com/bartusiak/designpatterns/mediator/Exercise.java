package pl.com.bartusiak.designpatterns.mediator;

import java.util.ArrayList;
import java.util.List;

class Participant
{
    public int value = 0;
    private Mediator mediator;

    public Participant(Mediator mediator)
    {
        this.mediator=mediator;
        mediator.users.add(this);
    }

    public void say(int n)
    {
        mediator.broadcast(this, n);
    }
}

class Mediator
{
    public List<Participant> users = new ArrayList<>();

    public void broadcast(Participant sayer, int n){
        for(Participant p: users){
            if(!p.equals(sayer)){
                p.value+=n;
            }
        }
    }
}

public class Exercise {
}
