package ru.academits.martin.hashtable.exec;

import ru.academits.martin.hashtable.HashTable;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<String> hashTable = new HashTable<>(1);
        System.out.println("Размер таблицы");
        System.out.println(hashTable.size());
        System.out.println("Добавление элементов");
        hashTable.add("dog");
        hashTable.add("cat");
        hashTable.add(null);
        System.out.println("Элементы таблицы");
        for (String s : hashTable) {
            System.out.println(s);
        }
        System.out.println("Размер таблицы");
        System.out.println(hashTable.size());
        System.out.println("Поиск элемента в таблице");
        System.out.println(hashTable.contains(null));
        System.out.println("Перевод таблицы в массив");
        Object[] array = hashTable.toArray();
        for (Object s : array) {
            System.out.println(s);
        }
        System.out.println("Удаление элемента из таблицы");
        System.out.println(hashTable.remove("dog"));
        System.out.println("Таблица после удаления элемента");
        for (String s : hashTable) {
            System.out.println(s);
        }
        ArrayList<String> lines = new ArrayList<>(Arrays.asList("one", "two", "three", "four"));
        System.out.println("Добавление коллекции");
        hashTable.addAll(lines);
        System.out.println("Таблица после добавления коллекции");
        for (String s : hashTable) {
            System.out.println(s);
        }
        System.out.println("Поиск коллекции в таблице");
        ArrayList<String> lines0 = new ArrayList<>(Arrays.asList("cat", "dog", null));
        System.out.println(hashTable.containsAll(lines0));

        System.out.println("Перевод таблицы в существующий массив");
        String[] array1 = hashTable.toArray(new String[0]);
        for (Object s : array1) {
            System.out.println(s);
        }

        ArrayList<String> lines1 = new ArrayList<>(Arrays.asList("a", "b"));
        System.out.println("Удаление коллекции");
        System.out.println(hashTable.removeAll(lines0));
        System.out.println("После удаления:");
        for (String s : hashTable) {
            System.out.println(s);
        }
        System.out.println("Оставить элементы коллекции");
        hashTable.retainAll(lines1);
        System.out.println("После применения метода retainAll:");
        for (String s : hashTable) {
            System.out.println(s);
        }
        System.out.println("Очистка таблицы");
        hashTable.clear();
        System.out.println("Размер таблицы");
        System.out.println(hashTable.size());
    }
}
