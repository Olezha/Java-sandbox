package edu.olezha.sandbox.java8;

import edu.olezha.sandbox.model.Bar;
import edu.olezha.sandbox.model.Foo;
import edu.olezha.sandbox.model.Person;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by oleh on 09.01.18.
 */
@Slf4j
public class Stream {

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        java.util.stream.Stream
                .iterate(0, i -> i + 1)
                .limit(10)
                .forEach(i ->
                        persons.add(new Person("Name" + i, new BigDecimal(15 + (i % 4 == 0 ? i + 1 : i)))));
        log.info("{}", persons);

        List<BigDecimal> interestedRates = persons.parallelStream()
                .map(p -> {
                    log.info("{}", p);
                    return p.getRate();
                })
                .filter(r -> r.compareTo(new BigDecimal(18)) > 0)
                .collect(Collectors.toList());

        log.info("interestedRates: {}", interestedRates);

        interestedRates.parallelStream()
                .forEach(new MyPrint());

        System.out.println();

        persons.parallelStream()
                .map(Person::getRate)
                .filter(r -> r.compareTo(new BigDecimal(18)) > 0)
                .forEach(r -> System.out.print(r + " "));

        System.out.println(System.lineSeparator());

        Map<BigDecimal, List<Person>> groupsByRate = persons.stream()
                .collect(Collectors.groupingBy(Person::getRate));

        groupsByRate.forEach((rate, person) -> log.info("rate {}: {}", rate, person));

        log.info("avg rate: {}",
                persons.stream().collect(Collectors.averagingInt(p -> p.getRate().intValue())).toString());
        log.info("{}", persons.stream().collect(Collectors.summarizingInt(p -> p.getRate().intValue())).toString());

        log.info("{}",
                persons.stream()
                        .filter(person -> person.getRate().intValue() > 20)
                        .map(Person::getName)
                        .collect(Collectors.joining(", ", "", " have rate more then twenty")));

        log.info("{}", persons.stream()
                .collect(Collector.of(
                        () -> new StringJoiner(" "),
                        (j, p) -> j.add(p.getName()),
                        StringJoiner::merge)));

        List<Foo> fooList = new ArrayList<>();
        IntStream.range(1, 4).forEach(i -> fooList.add(new Foo(String.valueOf(i), new ArrayList<>())));
        fooList.forEach(f -> IntStream.range(1, 3).forEach(i -> f.getBarList().add(new Bar(f.getName() + i))));
        log.info("{}", fooList);
        log.info("{}", fooList.stream().flatMap(f -> f.getBarList().stream()).collect(Collectors.toList()));

        Optional.of(
                new Foo(null, new ArrayList<Bar>() {{
                    add(new Bar(null));
                }}))
                .flatMap(f -> {
                    log.info("get list");
                    return Optional.ofNullable(f.getBarList());
                })
                .flatMap(l -> {
                    log.info("get Bar");
                    return Optional.ofNullable(l.get(0));
                })
                .flatMap(b -> {
                    log.info("get Bar name");
                    return Optional.ofNullable(b.getName());
                })
                .ifPresent(n -> log.info("Bar name: {}", n));

        persons.stream()
                .reduce((p1, p2) -> p1.getRate().compareTo(p2.getRate()) > 0 ? p1 : p2)
                .ifPresent(p -> log.info("{}", p));

        log.info("{}",
                persons.stream().reduce(new Person("", BigDecimal.ZERO), (p1, p2) -> {
                    p1.setName(p1.getName() + p2.getName());
                    p1.setRate(p1.getRate().add(p2.getRate()));
                    return p1;
                }));

        log.info("{}",
                persons.parallelStream()
                        .reduce(
                                BigDecimal.ZERO,
                                (sum, p) -> {
                                    log.info("sum.add(p.getRate()): {} + {}", sum, p.getRate());
                                    return sum.add(p.getRate());
                                },
                                (sum1, sum2) -> {
                                    log.info("sum1.add(sum2): {} {}", sum1, sum2);
                                    return sum1.add(sum2);
                                }));
    }
}

class MyPrint implements Consumer<BigDecimal> {

    @Override
    public void accept(BigDecimal bigDecimal) {
        System.out.print(bigDecimal + " ");
    }
}
