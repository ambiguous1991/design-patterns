package pl.com.bartusiak.designpatterns.bridge;

interface Renderer{
    public String whatToRenderAs();
}

class VectorRenderer implements Renderer{
    @Override
    public String whatToRenderAs() {
        return "lines";
    }

    @Override
    public String toString() {
        return "Drawing as "+whatToRenderAs();
    }
}

class RasterRenderer implements Renderer{
    @Override
    public String whatToRenderAs() {
        return "pixels";
    }
    @Override
    public String toString() {
        return "Drawing as "+whatToRenderAs();
    }
}

abstract class Shape
{
    protected Renderer renderer;

    public Shape(Renderer renderer) {
        this.renderer = renderer;
    }

    public abstract String getName();

    @Override
    public String toString() {
        return String.format("Drawing %s as %s", getName(), renderer.whatToRenderAs());
    }
}

class Triangle extends Shape
{
    public Triangle(Renderer renderer) {
        super(renderer);
    }

    @Override
    public String getName()
    {
        return "Triangle";
    }
}

class Square extends Shape
{
    public Square(Renderer renderer) {
        super(renderer);
    }

    @Override
    public String getName()
    {
        return "Square";
    }
}

class VectorSquare extends Square
{
    public VectorSquare(Renderer renderer) {
        super(renderer);
    }

    @Override
    public String toString()
    {
        return String.format("Drawing %s as lines", getName());
    }
}

class RasterSquare extends Square
{
    public RasterSquare(Renderer renderer) {
        super(renderer);
    }

    @Override
    public String toString()
    {
        return String.format("Drawing %s as pixels", getName());
    }
}

// imagine VectorTriangle and RasterTriangle are here too

public class Excercise {
}
