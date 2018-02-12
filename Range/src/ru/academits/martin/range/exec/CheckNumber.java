package ru.academits.martin.range.exec;

import ru.academits.martin.range.Range;

import java.util.Scanner;

public class CheckNumber {
    private static double printAndRead(String line) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(line);
        return scanner.nextDouble();
    }

    public static void main(String[] args) {
        double begin = 0.0;
        double end = 20.0;

        Range interval = new Range(begin, end);
        System.out.printf("Первоначальные границы интервала: %f - %f.%n", interval.from(), interval.to());

        interval.setFrom(printAndRead("Введите новую нижнюю границу интервала: "));
        interval.setTo(printAndRead("Введите новую верхнюю границу интервала: "));
        System.out.printf("Новые границы интервала: %f - %f.%n", interval.from(), interval.to());
        System.out.printf("Длина интервала = %f%n", interval.getLength());

        double number = printAndRead("Введите число: ");

        if (interval.isInside(number)) {
            System.out.printf("Число %f попадает в заданный интервал.", number);
        } else {
            System.out.printf("Число %f не попадает в заданный интервал.", number);
        }
    }
}
