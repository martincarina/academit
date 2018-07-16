package ru.academits.martin.lambdas.exec;

import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;


public class Main2 {

    private static Function<Integer, Integer> fibonacci;

    public static void main(String[] args) {
        DoubleStream roots = DoubleStream.iterate(0, x -> x + 1).map(Math::sqrt);

        Scanner in = new Scanner(System.in);
        System.out.print("Введите количество элементов, которое нужно вычислить:");
        int number = in.nextInt();
        if (number < 0) {
            throw new IllegalArgumentException("Число должно быть неотрицательным");
        }

        DoubleStream rootsStream = roots.limit(number);
        System.out.println("Поток квадратных корней");
        rootsStream.forEach(System.out::println);

        fibonacci = x -> {
            if (x <= 1) {
                return x;
            } else {
                return fibonacci.apply(x - 2) + fibonacci.apply(x - 1);
            }
        };

        IntStream fibonacciStream = IntStream.iterate(0, x -> x + 1).map(x -> fibonacci.apply(x));

        IntStream stream1 = fibonacciStream.limit(number);
        System.out.println("Поток чисел Фибоначи");
        stream1.forEach(System.out::println);
    }
}
