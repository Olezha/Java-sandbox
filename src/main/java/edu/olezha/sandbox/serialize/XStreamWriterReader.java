package edu.olezha.sandbox.serialize;

import com.thoughtworks.xstream.XStream;
import edu.olezha.sandbox.model.Person;

import java.math.BigDecimal;

public class XStreamWriterReader {

    public static void main(String[] args) {
        Person person = new Person("Oleh", BigDecimal.TEN);

        XStream xstream = new XStream();

        String xml = xstream.toXML(person);
        System.out.println(xml);

        person = (Person) xstream.fromXML(xml);
        System.out.println(person);
    }
}
