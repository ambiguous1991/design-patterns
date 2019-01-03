package com.jba;

class Person
{
    public int id;
    public String name;

    public Person(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
}

class PersonFactory
{
    static int itemsCreated =0;
    public Person createPerson(String name)
    {
        return new Person(itemsCreated++, name);
    }
}

public class Exercise {
}
