package edu.olezha.sandbox.serialize;

import edu.olezha.sandbox.model.Person;

import java.io.*;
import java.math.BigDecimal;

public class WriterReader {

    public static void main(String[] args) {
        final String FILE = "target/myObjects.txt";
        Person person = new Person("Oleh", BigDecimal.TEN);

        try (FileOutputStream f = new FileOutputStream(new File(FILE));
             ObjectOutputStream o = new ObjectOutputStream(f)) {
            o.writeObject(person);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream f = new FileInputStream(new File(FILE));
             ObjectInputStream o = new ObjectInputStream(f)) {
            while (f.available() > 0)
                System.out.println(o.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
