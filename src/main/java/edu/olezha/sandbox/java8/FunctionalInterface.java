package edu.olezha.sandbox.java8;

import edu.olezha.sandbox.model.Person;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BiFunction;

/**
 * Created by oleh on 24.12.17.
 */
@Slf4j
public class FunctionalInterface {

    private static final BigDecimal overtimeRate = new BigDecimal(2);
    private static final BigDecimal hospitalRate = new BigDecimal(.7);

    public static void main(String[] args) {
        FunctionalInterface aPayroll = new FunctionalInterface();

        Person person = new Person();
        person.setName("Oleh");
        person.setRate(new BigDecimal(15));
        log.info("{}", person);

        aPayroll.processOvertime(person, new BigDecimal(4),
                (rate, hours) -> overtimeRate.multiply(rate.multiply(hours)));

        aPayroll.processHospital(person, 2,
                (rate, days) -> hospitalRate
                        .multiply(rate.multiply(new BigDecimal(days * 8)))
                        .setScale(2, RoundingMode.HALF_UP));

        aPayroll.processHospital(person, 3,
                (rate, days) -> hospitalRate
                        .multiply(rate.multiply(new BigDecimal(days * 8)))
                        .setScale(2, RoundingMode.HALF_UP));
    }

    private void processOvertime(Person person, BigDecimal hours,
                                 BiFunction<BigDecimal, BigDecimal, BigDecimal> function) {
        BigDecimal sum = function.apply(person.getRate(), hours);
        // Save to Repo
        // Send email ..
        log.info("Overtime sum: {}", sum);
    }

    private void processHospital(Person person, Integer days,
                                 BiFunction<BigDecimal, Integer, BigDecimal> function) {
        BigDecimal sum = function.apply(person.getRate(), days);
        // ...
        log.info("Hospital sum: {}", sum);
    }
}
