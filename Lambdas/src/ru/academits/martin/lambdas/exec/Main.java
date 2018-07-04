package ru.academits.martin.lambdas.exec;

import ru.academits.martin.lambdas.Person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("Олег", 30);
        Person person2 = new Person("Игорь", 45);
        Person person3 = new Person("Анна", 35);
        Person person4 = new Person("Анна", 25);
        List<Person> list = Arrays.asList(person1, person2, person3, person4);

    //    List<Person> sorted = list.stream().sorted(((p1, p2) -> (p1.getAge() - p2.getAge()))).collect(Collectors.toList());
    //    sorted.forEach(System.out::println);

        String names = list.stream().map(p -> p.getName()).distinct().collect(Collectors.joining(", "));
        System.out.println(names);
    }
}
