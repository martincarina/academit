package ru.academits.martin.hashtable.exec;

import ru.academits.martin.hashtable.HashTable;

public class Main {
    public static void main(String[] args) {
        HashTable<String> hashTable = new HashTable<>(11);
        System.out.println(hashTable.size());
        hashTable.add("dog");
        hashTable.add("cat");
        hashTable.print();
        System.out.println(hashTable.size());
        System.out.println(hashTable.contains("dog"));
        hashTable.clear();
        System.out.println(hashTable.size());
        System.out.println("Перевод коллекции в массив");
        //      String[] strings = (String[]) hashTable.toArray();
        Object[] array = hashTable.toArray();
        for (Object s : array) {
            System.out.println(s);
        }
    }
}
