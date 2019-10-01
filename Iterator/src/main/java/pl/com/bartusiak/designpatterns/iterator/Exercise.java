package pl.com.bartusiak.designpatterns.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Node<T>
{
    public T value;
    public Node<T> left, right, parent;

    public Node(T value)
    {
        this.value = value;
    }

    public Node(T value, Node<T> left, Node<T> right)
    {
        this.value = value;
        this.left = left;
        this.right = right;

        left.parent = right.parent = this;
    }

    private List<Node<T>> treeToList(){
        List<Node<T>> result = new ArrayList<>();
        result.add(this);
        if(left!=null){
            result.addAll(left.treeToList());
        }
        if (right!=null){
            result.addAll(right.treeToList());
        }
        return result;
    }

    public Iterator<Node<T>> preOrder()
    {
        return treeToList().iterator();
    }
}

public class Exercise {
    static Node<String> node = new Node<>(
            "hello",
            new Node<>("hello_left"),
            new Node<>("hello_right")
    );

    public static void main(String[] args) {
        node.preOrder().forEachRemaining(e-> System.out.println(e.value));
    }
}

