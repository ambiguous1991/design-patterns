package pl.com.bartusiak.designpatterns.builder;

import java.util.HashMap;
import java.util.Map;

public class CodeBuilder
{
    private String className;
    private Map<String, String> fields = new HashMap<>();

    public CodeBuilder(String className)
    {
        this.className = className;
    }

    public CodeBuilder addField(String name, String type)
    {
        fields.put(name, type);
        return this;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("public class "+className);
        builder.append(System.lineSeparator());
        builder.append("{\n");

        fields.forEach((value, type) -> {
            builder.append(String.format("  public %s %s;\n", type, value));
        });
        builder.append("}");
        return builder.toString();
    }

    public static void main(String[] args) {
        CodeBuilder cb = new CodeBuilder("Person")
                .addField("name", "String")
                .addField("age", "int");

        System.out.println(cb);
    }
}


