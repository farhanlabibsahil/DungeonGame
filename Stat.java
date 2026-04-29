package dungeongame;

import java.io.FileWriter;
import java.io.IOException;

public class Stat {

    private String name;
    private int age;

    public Stat(String name, int age) {
        this.name = name;
        this.age = age;

        saveToFile(); //automatically save
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    //File Handle
   private void saveToFile() {
    try {
        FileWriter writer = new FileWriter("stat.txt", true);

        writer.write("Name: " + name + "\n");
        writer.write("Age: " + age + "\n");
        writer.write("-------------------\n");

        writer.close();

    } catch (IOException e) {
        System.out.println("File error: " + e.getMessage());
    }
}
}
