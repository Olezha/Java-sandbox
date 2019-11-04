package edu.olezha.sandbox.core;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class EqualsAndHashcode {

    public static void main(String[] args) {
        /*
         * Note: great care must be exercised if mutable objects are used as map keys.
         * The behavior of a map is not specified if the value of an object is changed
         * _in a manner that affects_ equals comparisons while the object is a key in the map.
         *
         * A special case of this prohibition is that it is not permissible for a map to contain itself as a key.
         * While it is permissible for a map to contain itself as a value,
         * extreme caution is advised: the equals and hashCode methods are no longer well defined on such a map.
         */
        Map<Person, String> names = new HashMap<>();

        Person oleh = new Person(1L, "Oleh");
        names.put(oleh, oleh.getName());
        Person vladimir = new Person(2L, "Vladimir");
        names.put(vladimir, vladimir.getName());
        oleh.setName("Olezha");
        log.info("{}, {}", names.get(oleh), names.get(vladimir));
        log.info("{}", names);
    }
}

@Data
class Person {

    private Long id;
    private String name;
    private BigDecimal sum;

    Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Person a = (Person) object;

        return getId().equals(a.getId()) && getName().equals(a.getName());
    }

    /*
     * Whenever it is invoked on the same object more than once during
     * an execution of a Java application, the {@code hashCode} method
     * must consistently return the same integer, _provided_ no information
     * used in {@code equals} comparisons on the object is modified.
     * This integer need not remain consistent from one execution of an
     * application to another execution of the same application.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getId().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }
}