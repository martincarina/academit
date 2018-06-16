package ru.academits.martin.hashtable.exec;

import ru.academits.martin.hashtable.HashTable;

public class Main {
    public static void main(String[] args) {
        HashTable<String> hashTable = new HashTable<>(31);
        System.out.println(hashTable.size());
        hashTable.add("dog");
        hashTable.add("cat");
        hashTable.print();
        System.out.println(hashTable.size());
    }
}
