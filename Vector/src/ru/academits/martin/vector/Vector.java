package ru.academits.martin.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть не меньше 1.");
        }
        components = new double[n];
    }

    public Vector(Vector vector) {
        this(Arrays.copyOf(vector.components, vector.components.length));
    }

    public Vector(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть не меньше 1.");
        }
        this.components = new double[components.length];
        this.components = components;
    }

    public Vector(int n, double[] components) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть не меньше 1.");
        }
        this.components = Arrays.copyOf(components, n);
    }

    public int getSize() {
        return components.length;
    }

    public double getComponent(int index) {
        if (index >= components.length) {
            throw new IndexOutOfBoundsException("Индекс должен быть меньше размерности вектора.");
        }
        return components[index];
    }

    public void setComponent(int index, double value) {
        if (index >= components.length) {
            throw new IndexOutOfBoundsException("Индекс должен быть меньше размерности вектора.");
        }
        this.components[index] = value;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < components.length; i++) {
            if (i == 0) {
                sb.append(components[0]);
            } else {
                sb.append(", ")
                        .append(components[i]);
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public Vector sumOfVectors(Vector vector2) {
        if (components.length < vector2.components.length) {
            this.components = Arrays.copyOf(components, vector2.components.length);
        }
        for (int i = 0; i < vector2.components.length; i++) {
            components[i] += vector2.components[i];
        }
        return this;
    }

    public Vector differenceOfVectors(Vector vector2) {
        Vector copyOfVector2 = new Vector(vector2);
        return sumOfVectors(copyOfVector2.inversionOfVector());
    }

    public Vector multiplicationOfVector(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
        return this;
    }

    public Vector inversionOfVector() {
        return multiplicationOfVector(-1);
    }

    public double getLength() {
        double sum = 0;
        for (double i : components) {
            sum += Math.pow(i, 2);
        }
        return Math.sqrt(sum);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = prime * hash + components.length;
        hash = prime * hash + Arrays.hashCode(components);
        return hash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vector vector2 = (Vector) obj;
        return (components.length == vector2.components.length && Arrays.equals(components, vector2.components));
    }

    public static Vector sumOfVectors(Vector vector1, Vector vector2) {
        Vector sumVector = new Vector(vector1);
        return sumVector.sumOfVectors(vector2);
    }

    public static Vector differenceOfVectors(Vector vector1, Vector vector2) {
        Vector diffVector = new Vector(vector1);
        return diffVector.differenceOfVectors(vector2);
    }

    public static double scalarMultiplication(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.components.length, vector2.components.length);
        double multiplication = 0;
        for (int i = 0; i < minSize; i++) {
            multiplication += vector1.components[i] * vector2.components[i];
        }
        return multiplication;
    }
}
