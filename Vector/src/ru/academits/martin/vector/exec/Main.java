package ru.academits.martin.vector.exec;

import ru.academits.martin.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(2);
        System.out.printf("vector1 = %s%n", vector1.toString());

        Vector vector2 = new Vector(vector1);
        System.out.printf("Копия vector1 : vector2 = %s%n", vector2.toString());

        double[] array = {1, 2, 3};
        System.out.println(Arrays.toString(array));
        Vector vector3 = new Vector(array);
        System.out.printf("vector3 = %s%n", vector3.toString());

        double[] array1 = {1, 2, 3, 7, 8};
        Vector vector4 = new Vector(10, array1);
        System.out.printf("vector4 = %s%n", vector4.toString());

        Vector vector5 = vector3.sumOfVectors(vector4);
        System.out.printf("Сумма vector3 и vector4 : vector5 = %s%n", vector5.toString());

        Vector vector6 = vector5.differenceOfVectors(vector3);
        System.out.printf("Разность vector5 и vector3 : vector6 = %s%n", vector6.toString());

        Vector vector7 = vector4.multiplicationOfVector(2);
        System.out.printf("Умножение на скаляр vector4 : vector7 = %s%n", vector7.toString());

        Vector vector8 = vector4.inversionOfVector();
        System.out.printf("Разворот vector4 : vector8 = %s%n", vector8.toString());

        System.out.printf("Длина вектора vector3 = %f%n", vector3.getLength());

        int index = 2;
        System.out.printf("Компонента vector3 с индексом %d равна %f%n", index, vector3.getComponent(index));

        vector3.setComponent(index, 9);
        System.out.printf("Компонента vector3 с индексом %d равна %f%n", index, vector3.getComponent(index));

        System.out.println("Проверка на равенство vector4 и vector6");
        if (vector4.hashCode() != vector6.hashCode()) {
            System.out.println("Векторы не равны.");
        } else {
            if (vector4.equals(vector6)) {
                System.out.println("Векторы равны.");
            } else {
                System.out.println("Векторы не равны.");
            }
        }

        Vector vector9 = Vector.sumOfVectors(vector1, vector3);
        System.out.printf("Сумма векторов vector1 и vector3 (static): vector9 = %s%n", vector9.toString());

        Vector vector10 = Vector.differenceOfVectors(vector1, vector4);
        System.out.printf("Разность векторов vector1 и vector3 (static): vector10 = %s%n", vector10.toString());

        System.out.printf("Скалярное произведение векторов vector4 и vector5 = %f%n", Vector.scalarMultiplication(vector4, vector5));
    }
}
