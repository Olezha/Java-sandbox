package edu.olezha.jsandbox.serialize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import edu.olezha.jsandbox.model.Person;

import java.math.BigDecimal;

public class JacksonWriterReader {

    public static void main(String[] args) {
        Person person = new Person("Oleh", BigDecimal.TEN);

        YAMLMapper mapper = new YAMLMapper();
        try {
            String yml = mapper.writeValueAsString(person);
            System.out.println(yml);
            System.out.println(mapper.readTree(yml));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
