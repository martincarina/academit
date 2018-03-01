package ru.academits.martin.vector;

import java.util.Arrays;

public class Vector {
    private int size;
    private double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть не меньше 1.");
        }
        size = n;
        components = new double[size];
    }

    public Vector(Vector vector) {
        this(vector.getSize());
        components = vector.getComponents();
    }

    public Vector(double[] components) {
        size = components.length;
        this.components = new double[size];
        this.components = components;
    }

    public Vector(int n, double[] components) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть не меньше 1.");
        }
        size = n;
        int length = components.length;
        this.components = new double[size];
        System.arraycopy(components, 0, this.components, 0, length);
    }

    private int getSize() {
        return size;
    }

    private double[] getComponents() {
        return components;
    }

    public double getComponent(int index) {
        if (index >= size) {
            throw new IllegalArgumentException("Индекс должен быть меньше размерности вектора.");
        }
        return components[index];
    }

    public void setComponent(int index, double value) {
        if (index >= size) {
            throw new IllegalArgumentException("Индекс должен быть меньше размерности вектора.");
        }
        this.components[index] = value;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < size; i++) {
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
        int maxSize;
        int minSize;
        Vector maxVector;
        Vector minVector;
        if (size > vector2.size) {
            maxSize = size;
            minSize = vector2.size;
            maxVector = this;
            minVector = vector2;
        } else {
            maxSize = vector2.size;
            minSize = size;
            maxVector = vector2;
            minVector = this;
        }
        double[] componentsNew = new double[maxSize];
        for (int i = 0; i < maxSize; i++) {
            if (i < minSize) {
                componentsNew[i] = maxVector.components[i] + minVector.components[i];
            } else {
                componentsNew[i] = maxVector.components[i];
            }
        }
        return new Vector(componentsNew);
    }

    public Vector differenceOfVectors(Vector vector2) {
        return sumOfVectors(vector2.inversionOfVector());
    }

    public Vector multiplicationOfVector(double scalar) {
        double[] componentsNew = new double[size];
        for (int i = 0; i < size; i++) {
            componentsNew[i] = components[i] * scalar;
        }
        return new Vector(componentsNew);
    }

    public Vector inversionOfVector() {
        return multiplicationOfVector(-1);
    }

    public double getLength() {
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += Math.pow(components[i], 2);
        }
        return Math.sqrt(sum);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = prime * hash + size;
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
        return (size == vector2.size && Arrays.equals(components, vector2.components));
    }

    public static Vector sumOfVectors(Vector vector1, Vector vector2) {
        int maxSize;
        int minSize;
        Vector maxVector;
        Vector minVector;
        if (vector1.size > vector2.size) {
            maxSize = vector1.size;
            minSize = vector2.size;
            maxVector = vector1;
            minVector = vector2;
        } else {
            maxSize = vector2.size;
            minSize = vector1.size;
            maxVector = vector2;
            minVector = vector1;
        }
        double[] componentsNew = new double[maxSize];
        for (int i = 0; i < maxSize; i++) {
            if (i < minSize) {
                componentsNew[i] = maxVector.components[i] + minVector.components[i];
            } else {
                componentsNew[i] = maxVector.components[i];
            }
        }
        return new Vector(componentsNew);
    }

    public static Vector differenceOfVectors(Vector vector1, Vector vector2) {
        return sumOfVectors(vector1, vector2.inversionOfVector());
    }

    public static double scalarMultiplication(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.size, vector2.size);
        double multiplication = 0;
        for (int i = 0; i < minSize; i++) {
            multiplication += vector1.components[i] * vector2.components[i];
        }
        return multiplication;
    }
}
