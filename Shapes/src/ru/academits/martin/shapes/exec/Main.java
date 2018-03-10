package ru.academits.martin.shapes.exec;

import ru.academits.martin.shapes.*;

import static ru.academits.martin.shapes.Shape.getShapeWithMaxArea;
import static ru.academits.martin.shapes.Shape.getShapeWithSecondPerimeter;

public class Main {
    public static void main(String[] args) {
        Shape square1 = new Square(5);

        System.out.println("Квадрат1:");
        System.out.printf("Ширина = %f%n", square1.getWidth());
        System.out.printf("Высота = %f%n", square1.getHeight());
        System.out.printf("Площадь = %f%n", square1.getArea());
        System.out.printf("Периметр = %f%n", square1.getPerimeter());

        Shape square2 = new Square(5);

        System.out.println("Квадрат2:");
   /*     System.out.printf("Ширина = %f%n", square2.getWidth());
        System.out.printf("Высота = %f%n", square2.getHeight());*/
        System.out.printf("Площадь = %f%n", square2.getArea());
        System.out.printf("Периметр = %f%n", square2.getPerimeter());

        Shape triangle1 = new Triangle(0, 0, 0, 1, 1, 1);
        System.out.println("Треугольник1:");
        System.out.printf("Ширина = %f%n", triangle1.getWidth());
        System.out.printf("Высота = %f%n", triangle1.getHeight());
        System.out.printf("Площадь = %f%n", triangle1.getArea());
        System.out.printf("Периметр = %f%n", triangle1.getPerimeter());

        Shape triangle2 = new Triangle(5, 7, 0, 1, 1, 1);
        System.out.println("Треугольник2:");
/*        System.out.printf("Ширина = %f%n", triangle2.getWidth());
        System.out.printf("Высота = %f%n", triangle2.getHeight());*/
        System.out.printf("Площадь = %f%n", triangle2.getArea());
        System.out.printf("Периметр = %f%n", triangle2.getPerimeter());

        Shape rectangle1 = new Rectangle(8, 7);
        System.out.println("Прямоугольник1:");
        System.out.printf("Ширина = %f%n", rectangle1.getWidth());
        System.out.printf("Высота = %f%n", rectangle1.getHeight());
        System.out.printf("Площадь = %f%n", rectangle1.getArea());
        System.out.printf("Периметр = %f%n", rectangle1.getPerimeter());

        Shape rectangle2 = new Rectangle(7, 8);
        System.out.println("Прямоугольник2:");
 /*       System.out.printf("Ширина = %f%n", rectangle2.getWidth());
        System.out.printf("Высота = %f%n", rectangle2.getHeight());*/
        System.out.printf("Площадь = %f%n", rectangle2.getArea());
        System.out.printf("Периметр = %f%n", rectangle2.getPerimeter());

        Shape circle1 = new Circle(2);
        System.out.println("Круг1:");
        System.out.printf("Ширина = %f%n", circle1.getWidth());
        System.out.printf("Высота = %f%n", circle1.getHeight());
        System.out.printf("Площадь = %f%n", circle1.getArea());
        System.out.printf("Периметр = %f%n", circle1.getPerimeter());

        Shape circle2 = new Circle(3);
        System.out.println("Круг2:");
        System.out.printf("Площадь = %f%n", circle2.getArea());
        System.out.printf("Периметр = %f%n", circle2.getPerimeter());

        Shape[] shapes = {square1, square2, triangle1, triangle2, rectangle1, rectangle2, circle1, circle2};

        Shape maxAreaShape = getShapeWithMaxArea(shapes);
        System.out.println("Max Area:");
        System.out.printf("Ширина = %f%n", maxAreaShape.getWidth());
        System.out.printf("Высота = %f%n", maxAreaShape.getHeight());
        System.out.printf("Площадь = %f%n", maxAreaShape.getArea());
        System.out.printf("Периметр = %f%n", maxAreaShape.getPerimeter());

        System.out.println("Second Perimeter:");
        Shape secondPerimeterShape = getShapeWithSecondPerimeter(shapes);
        System.out.printf("Ширина = %f%n", secondPerimeterShape.getWidth());
        System.out.printf("Высота = %f%n", secondPerimeterShape.getHeight());
        System.out.printf("Площадь = %f%n", secondPerimeterShape.getArea());
        System.out.printf("Периметр = %f%n", secondPerimeterShape.getPerimeter());

        System.out.println(square2.toString());
        System.out.println(triangle2.toString());
        System.out.println(rectangle2.toString());
        System.out.println(circle2.toString());

        System.out.print("Проверка на равенство фигур: ");
        if (triangle1.hashCode() != triangle2.hashCode()) {
            System.out.println("Фигуры не равны.");
        } else {
            if (triangle2.equals(triangle1)) {
                System.out.println("Фигуры равны.");
            } else {
                System.out.println("Фигуры не равны.");
            }
        }
    }
}
