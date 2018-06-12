package ru.academits.martin.arraylist.exec;

import ru.academits.martin.arraylist.ArrayList;

import java.util.Arrays;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> lines0
                = new ArrayList<>();

        ArrayList<String> lines
                = new ArrayList<>(Arrays.asList("line 1", "line 2"));

        System.out.println("Список, заполненный из коллекции");
        System.out.println("Длина списка");
        System.out.println(lines.size());
        System.out.println("Добавление элемента в конец");
        System.out.println(lines.add("b"));
        System.out.println("Добавление элемента в середину");
        System.out.println("Старое значение");
        System.out.println(lines.get(0));
        lines.add(0, "b");
        System.out.println("Новое значение");
        System.out.println(lines.get(0));

        ArrayList<String> lines2
                = new ArrayList<>(15);

        System.out.println("Список с заданной вместимостью");
        System.out.println("Длина списка");
        System.out.println(lines2.size());

        System.out.println("Список по умолчанию");
        System.out.println("Длина списка");
        System.out.println(lines0.size());
        System.out.println("Добавление элемента в конец");
        System.out.println(lines0.add("b"));
        System.out.println("Установка нового значения по индексу");
        System.out.println(lines0.set(0, "a"));
        System.out.println("Новое значение");
        System.out.println(lines0.get(0));
        System.out.println("Длина списка");
        System.out.println(lines0.size());
        System.out.println("Добавление элемента в середину");
        System.out.println("Старое значение");
        System.out.println(lines0.get(0));
        lines0.add(0, "b");
        System.out.println("Новое значение");
        System.out.println(lines0.get(0));
        System.out.println("Добавление коллекции в конец. ");
        System.out.println("До: ");
        for (String s : lines) {
            System.out.println(s);
        }
        System.out.println(lines.addAll(lines2));
        System.out.println("После: ");
        for (String s : lines) {
            System.out.println(s);
        }
        System.out.println("Добавление коллекции по индексу");
        System.out.println(lines.addAll(2, lines0));
        System.out.println("После: ");
        for (String s : lines) {
            System.out.println(s);
        }
        System.out.println("Длина списка");
        System.out.println(lines.size());
        System.out.println(lines.add(null));
        System.out.println("Элементы списка lines");
        for (String s : lines) {
            System.out.println(s);
        }
        System.out.println("Проверка наличия в списке элемента");
        System.out.println(lines0.contains("a"));
        System.out.println("Удаление элемента по индексу");
        System.out.println(lines0.remove("a"));
        System.out.println("Поиск индекса первого вхождения элемента");
        System.out.println(lines.indexOf("b"));
        System.out.println("Поиск индекса последнего вхождения элемента");
        System.out.println(lines.lastIndexOf("b"));
        System.out.println(lines.lastIndexOf(null));
        System.out.println(lines.remove(null));

        System.out.println("Перевод коллекции в массив");
        String[] strings = lines.toArray(new String[10]);
        for (String s : strings) {
            System.out.println(s);
        }

        System.out.println("Обрезание массива до длины списка");
        lines2.trimToSize();
        System.out.println("Добавление элемента в конец");
        lines2.add("t");
        System.out.println("Элементы списка lines2");
        for (String s : lines2) {
            System.out.println(s);
        }

        System.out.println("Clear");
        System.out.println(lines2.size());
        lines2.clear();
        System.out.println(lines2.size());
        lines2.add("t");
        lines2.add("p");

        System.out.println("ListIterator");
        ListIterator<String> listIterator = lines.listIterator();
        System.out.println("Печать списка");
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        System.out.println("Set+Печать списка в обратном порядке");
        listIterator.set("u");
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }
        System.out.println("Remove");
        if (listIterator.hasNext()) {
            listIterator.next();
        }
        listIterator.remove();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        System.out.println("Add+Печать списка в обратном порядке");
        listIterator.add("b");
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }

        lines0.add("a");
        System.out.println("Элементы списка lines0:");
        for (String s : lines0) {
            System.out.println(s);
        }

        System.out.println("Проверка на наличие коллекции (без удаления)");
        System.out.println(lines.containsAll(lines0));

        System.out.println("Удаление коллекции");
        System.out.println("До удаления:");
        for (String s : lines) {
            System.out.println(s);
        }

        lines2.add(null);
        System.out.println(lines.removeAll(lines2));
        System.out.println("После удаления:");
        for (String s : lines) {
            System.out.println(s);
        }
        System.out.println("Оставить элементы коллекции");
        lines.retainAll(lines0);
        System.out.println("После применения метода retainAll:");
        for (String s : lines) {
            System.out.println(s);
        }
    }
}
