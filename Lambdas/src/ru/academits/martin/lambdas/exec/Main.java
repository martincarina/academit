package ru.academits.martin.lambdas.exec;

import ru.academits.martin.lambdas.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("Олег", 30);
        Person person2 = new Person("Игорь", 45);
        Person person3 = new Person("Анна", 35);
        Person person4 = new Person("Анна", 25);
        Person person5 = new Person("Владимир", 15);
        Person person6 = new Person("Елена", 16);
        Person person7 = new Person("Андрей", 19);
        Person person8 = new Person("Алина", 55);
        Person person9 = new Person("Марина", 28);
        Person person10 = new Person("Александр", 18);
        Person person11 = new Person("Светлана", 60);
        List<Person> list = Arrays.asList(person1, person2, person3, person4, person5, person6, person7, person8, person9, person10, person11);

        List<String> listNames = list.stream().map(Person::getName).distinct().collect(Collectors.toList());
        String names = listNames.stream().collect(Collectors.joining(", ", "Имена: ", "."));
        System.out.println(names);

        List<Person> listYoung = list.stream().filter(p -> p.getAge() < 18).collect(Collectors.toList());
        double averageAgeYoung = listYoung.stream().mapToInt(Person::getAge).average().orElse(Double.NaN);
        System.out.printf("Средний возраст людей моложе 18: %.1f лет.", averageAgeYoung);
        System.out.println();

        Map<String, Double> personsByName = list.stream().collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        System.out.println("Группировка по имени.");
        personsByName.forEach((name, averageAge) -> System.out.printf("%s: %s\n", name, averageAge));

        System.out.println("Вывод имен по убыванию возраста:");
        list.stream().filter(p -> p.getAge() >= 20 && p.getAge() <= 45).sorted(((p1, p2) -> (p2.getAge() - p1.getAge())))
                .forEach(p -> System.out.println(p.getName()));
    }
}