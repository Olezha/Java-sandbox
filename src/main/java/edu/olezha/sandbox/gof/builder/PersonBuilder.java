package edu.olezha.sandbox.gof.builder;

import edu.olezha.sandbox.model.Person;

import java.math.BigDecimal;

public class PersonBuilder {

    private Person person = new Person();

    public PersonBuilder setName(String name) {
        person.setName(name);
        return this;
    }

    public PersonBuilder setRate(BigDecimal rate) {
        person.setRate(rate);
        return this;
    }

    public Person build() {
        return person;
    }
}
