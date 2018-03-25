package ru.academits.martin.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream("example.csv"))) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        }
        System.out.printf("Длина lines: %d%n", lines.size());
        System.out.printf("lines: %s%n", lines);

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 4, 2, 7, 8));
        System.out.printf("Первоначальный list = %s%n", list);
        int index = 0;
        while (index < list.size()) {
            if (list.get(index) % 2 == 0) {
                list.remove(index);
            } else {
                ++index;
            }
        }
        System.out.printf("list: %s%n", list);

        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5));
        ArrayList<Integer> list2 = new ArrayList<>();
        index = 0;
        do {
            int number = list1.get(index);
            if (!list2.contains(number)) {
                list2.add(number);
            }
            ++index;
        } while (index < list1.size());
        System.out.printf("list1: %s%n", list1);
        System.out.printf("list2: %s%n", list2);
    }
}