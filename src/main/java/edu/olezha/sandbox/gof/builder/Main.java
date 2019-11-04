package edu.olezha.sandbox.gof.builder;

import edu.olezha.sandbox.model.Person;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class Main {

    public static void main(String[] args) {
        Person oleh = new PersonBuilder()
                .setName("Oleh")
                .setRate(BigDecimal.valueOf(2000))
                .build();
        log.info("{}", oleh);
    }
}
