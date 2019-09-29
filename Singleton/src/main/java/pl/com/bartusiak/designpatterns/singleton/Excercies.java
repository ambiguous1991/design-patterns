package pl.com.bartusiak.designpatterns.singleton;

import java.util.function.Supplier;

class SingletonTester
{
    public static boolean isSingleton(Supplier<Object> func)
    {
        return func.get().equals(func.get());
    }
}

public class Excercies {
}
