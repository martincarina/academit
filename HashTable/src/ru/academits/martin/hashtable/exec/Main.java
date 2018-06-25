package ru.academits.martin.hashtable.exec;

import ru.academits.martin.hashtable.HashTable;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<String> hashTable = new HashTable<>(10);
        System.out.println(hashTable.size());
        hashTable.add("dog");
        hashTable.add("cat");
        for (String s : hashTable) {
            System.out.println(s);
        }
        System.out.println(hashTable.size());
        System.out.println(hashTable.contains("dog"));
//        hashTable.clear(); - работает нормально. Тест в конец перенести
        System.out.println(hashTable.size());
        System.out.println("Перевод коллекции в массив");
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
    }
}
