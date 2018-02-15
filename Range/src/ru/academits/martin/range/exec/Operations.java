package ru.academits.martin.range.exec;

import ru.academits.martin.range.Range;

import java.util.Scanner;

public class Operations {
    private static double printAndRead(String line) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(line);
        return scanner.nextDouble();
    }

    public static void main(String[] args) {
        double begin1 = printAndRead("Введите начало первого интервала: ");
        double end1 = printAndRead("Введите конец первого интервала: ");
        double begin2 = printAndRead("Введите начало второго интервала: ");
        double end2 = printAndRead("Введите конец второго интервала: ");

        Range interval1 = new Range(begin1, end1);
        Range interval2 = new Range(begin2, end2);

        Range intersection = interval1.getIntersection(interval2);
        if (intersection == null) {
            System.out.println("Интервалы не пересекаются.");
        } else {
            System.out.printf("Длина интервала-пересечения = %f%n", intersection.getLength());
        }

        Range[] union = interval1.getUnion(interval2);
        for (int i = 0; i < union.length; i++) {
            System.out.printf("Длина %d-го интервала-объединения = %f%n", i + 1, union[i].getLength());
        }

        Range[] difference = interval1.getDifference(interval2);
        if (difference.length == 0) {
            System.out.println("Первый интервал полностью входит во второй.");
        } else {
            for (int i = 0; i < difference.length; i++) {
                System.out.printf("Длина %d-го интервала-разности = %f%n", i + 1, difference[i].getLength());
            }
        }
    }
}