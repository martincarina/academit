package ru.academits.martin.shapes;

import java.util.Arrays;
import java.util.Comparator;

public class Shape {//TODO прочитать лекцию 4 и переделать прогу
    public double getWidth() {
        return 0;
    }

    public double getHeight() {
        return 0;
    }

    public double getArea() {
        return 0;
    }

    public double getPerimeter() {
        return 0;
    }

    public static Shape getShapeWithMaxArea(Shape[] shapes) {
        Arrays.sort(shapes, new AreaComparator());
        return shapes[0];
    }

    public static Shape getShapeWithSecondPerimeter(Shape[] shapes) {
        Arrays.sort(shapes, new PerimeterComparator());
        return shapes[1];
    }
}

class AreaComparator implements Comparator<Object> {
    @Override
    public int compare(Object object1, Object object2) {
        Shape shape1 = (Shape) object1;
        Shape shape2 = (Shape) object2;
        return Double.compare(shape2.getArea(), shape1.getArea());
    }
}

class PerimeterComparator implements Comparator<Object> {
    @Override
    public int compare(Object object1, Object object2) {
        Shape shape1 = (Shape) object1;
        Shape shape2 = (Shape) object2;
        return Double.compare(shape2.getPerimeter(), shape1.getPerimeter());
    }
}
