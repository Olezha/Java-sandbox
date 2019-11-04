package edu.olezha.sandbox.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by oleh on 24.12.17.
 */
@Data
public class Person implements Serializable {

    private Long id;
    private String name;
    private BigDecimal rate;

    public Person() {}

    public Person(String name, BigDecimal rate) {
        this.name = name;
        this.rate = rate;
    }
}
