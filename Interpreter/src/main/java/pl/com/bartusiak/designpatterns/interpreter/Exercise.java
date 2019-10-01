package pl.com.bartusiak.designpatterns.interpreter;

import java.util.*;

interface Element{
    int eval();
}

class Int implements Element{
    private int val;

    public Int(int val) {
        this.val = val;
    }

    @Override
    public int eval() {
        return val;
    }
}

class BinaryOperation implements Element{
    public Element left, right;
    public Type type;

    public BinaryOperation() {

    }

    @Override
    public int eval() {
        switch (type){
            case PLUS:{
                return left.eval()+right.eval();
            }
            case MINUS:{
                return left.eval()-right.eval();
            }
        }
        return 0;
    }
}

enum Type{
    INTEGER,
    PLUS,
    MINUS,
    UNKNOWN,
    VAR
}

class Token{


    private Type type;
    private String exp;

    public Token(Type type, String exp) {
        this.type = type;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "Token{exp=" + exp + '\'' +
                '}';
    }

    public Type getType() {
        return type;
    }

    public String getExp() {
        return exp;
    }
}


class Tokenizer{
    private String input;
    private List<Token> tokens;

    public Tokenizer(String input) {
        this.input = input;
        tokens = new ArrayList<>();
        tokenize();
    }

    private void tokenize(){
        for (int i=0; i<input.length(); i++){
            char c = input.charAt(i);
            if (c == '+'){
                tokens.add(new Token(Type.PLUS, "+"));
            }
            else if (c == '-'){
                tokens.add(new Token(Type.MINUS, "-"));
            }
            else if (Character.isDigit(c)){
                i+=extractDigit(input.substring(i));
            }
            else{
                i+=extractVar(input.substring(i));
            }
        }
    };

    private int extractDigit(String in){
        for (int i=0; i<in.length(); i++){
            if(!Character.isDigit(in.charAt(i))){
                tokens.add(new Token(Type.INTEGER, in.substring(0, i)));
                return i-1;
            }
        }
        tokens.add(new Token(Type.INTEGER, in));
        return in.length()-1;
    }

    private int extractVar(String in){
        for (int i=0; i<in.length(); i++){
            if(!Character.isAlphabetic(in.charAt(i))){
                tokens.add(new Token(Type.VAR, in.substring(0, i)));
                return i-1;
            }
        }
        tokens.add(new Token(Type.VAR, in));
        return in.length()-1;
    }

    public List<Token> getTokens(){
        return tokens;
    }
}

class ExpressionProcessor {
    public Map<Character, Integer> variables = new HashMap<>();

    public int calculate(String expression) {
        Tokenizer tokenizer = new Tokenizer(expression);
        List<Token> tokens = tokenizer.getTokens();

        List<Token> onp = new ArrayList<>();
        Stack<Token> stack = new Stack<>();

        for (Token t: tokens){
            switch (t.getType()){
                case INTEGER:
                case VAR: {
                    onp.add(t);
                    break;
                }
                case PLUS:
                case MINUS:{
                    if(stack.empty()){
                        stack.push(t);
                    }
                    else{
                        onp.add(stack.pop());
                        stack.push(t);
                    }
                    break;
                }
            }
        }
        if(!stack.empty()){
            onp.add(stack.pop());
        }

        onp.forEach(e->System.out.print("'"+e.getExp()+"'  "));

        Stack<Integer> result = new Stack<>();

        for(Token t: onp){
            if(t.getType().equals(Type.INTEGER)){
                result.push(Integer.parseInt(t.getExp()));
            }
            else if(t.getType().equals(Type.VAR)){
                if(t.getExp().length()==1){
                    Integer integer = variables.get(t.getExp().charAt(0));
                    result.push(integer);
                }
                else return 0;
            }
            else if(t.getType().equals(Type.PLUS)){
                int a = result.pop();
                int b = result.pop();
                result.push(a+b);
            }
            else if(t.getType().equals(Type.MINUS)){
                int a = result.pop();
                int b = result.pop();
                result.push(a-b);
            }
        }

        return result.pop();
    }
}

public class Exercise {
    public static void main(String[] args) {
        ExpressionProcessor expressionProcessor = new ExpressionProcessor();
        expressionProcessor.variables.put('a',1);
        expressionProcessor.variables.put('b',5);

        int calculate = expressionProcessor
                .calculate("1+2-3+a-b");
        System.out.println("Result is "+calculate);
    }
}
