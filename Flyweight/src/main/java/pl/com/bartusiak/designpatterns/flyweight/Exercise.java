package pl.com.bartusiak.designpatterns.flyweight;

import java.util.*;

class Sentence
{
    private String plainText;
    private List<WordToken> tokens = new ArrayList<>();

    public Sentence(String plainText)
    {
        this.plainText = plainText;
        Arrays.stream(plainText.split(" ")).forEach(element-> tokens.add(new WordToken(element)));
    }

    public WordToken getWord(int index)
    {
        return tokens.get(index);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        tokens.forEach(el-> {
                    if (el.capitalize){
                        sb.append(el.word.toUpperCase()).append(" ");
                    }
                    else sb.append(el.word).append(" ");
                });
        return sb.substring(0, sb.length()-1);
    }

    class WordToken
    {
        private String word;

        public WordToken(String word) {
            this.word = word;
        }

        public String getWord() {
            return word;
        }

        public boolean capitalize;
    }
}

public class Exercise {
}
