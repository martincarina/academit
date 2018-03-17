package ru.academits.martin.matrix;

import static ru.academits.martin.matrix.Vector.getScalarMultiplication;

public class Matrix {

    private Vector[] components;

    public Matrix(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Размерности должны быть не меньше 1.");
        }

        components = new Vector[n];
        for (int i = 0; i < n; i++) {
            components[i] = new Vector(m);
        }
    }

    public Matrix(double[][] components) {
        if (components.length <= 0 || components[0].length <= 0) {
            throw new IllegalArgumentException("Размерности должны быть не меньше 1.");
        }
        this.components = new Vector[components.length];
        for (int i = 0; i < components.length; i++) {
            this.components[i] = new Vector(components[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length <= 0) {
            throw new IllegalArgumentException("Размерность массива векторов должна быть не меньше 1.");
        }
        components = new Vector[vectors.length];
        for (int i = 0; i < vectors.length; i++) {
            components[i] = new Vector(vectors[i]);
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.components);
    }

    public int[] getSize() {
        return new int[]{components.length, components[0].getSize()};
    }

    public Vector getRow(int index) {
        if (index >= components.length) {
            throw new IndexOutOfBoundsException("Индекс должен быть меньше количества строк в матрице.");
        }
        return components[index];
    }


    public void setRow(int index, Vector vector) {
        if (index >= components.length) {
            throw new IndexOutOfBoundsException("Индекс должен быть меньше количества строк в матрице.");
        } else if (vector.getSize() != components[index].getSize()) {
            System.out.println("Размерность вектора должно быть равно количеству столбцов в матрице");
        } else {
            components[index] = new Vector(vector);
        }
    }

    public Vector getColumn(int index) {
        if (index >= components[0].getSize()) {
            throw new IndexOutOfBoundsException("Индекс должен быть меньше количества столбцов в матрице.");
        }
        double column[] = new double[components.length];
        for (int i = 0; i < components.length; i++) {
            column[i] = components[i].getComponent(index);
        }
        return new Vector(column);
    }

    public Matrix transposeMatrix() {
        Vector[] vectors = new Vector[components[0].getSize()];
        for (int i = 0; i < components[0].getSize(); i++) {
            vectors[i] = getColumn(i);
        }
        return new Matrix(vectors);
    }

    public void multiplyMatrixByScalar(double scalar) {
        for (Vector component : components) {
            component.multiplyVectorByScalar(scalar);
        }
    }

    public Vector multiplyMatrixByVector(Vector vector) {
        if (components[0].getSize() != vector.getSize()) {
            throw new IllegalArgumentException("Число столбцов в матрице должно быть равно размерности вектора.");//TODO может, другое исключение надо
        } else {
            Vector vectorResult = new Vector(components.length);
            for (int i = 0; i < components.length; i++) {
                vectorResult.setComponent(i, getScalarMultiplication(components[i], vector));
            }
            return vectorResult;
        }
    }

    public void getSumOfMatrices(Matrix matrix) {
        if (components.length != matrix.components.length || components[0].getSize() != matrix.components[0].getSize()) {
            throw new IllegalArgumentException("Число столбцов и строк в матрицах должно совпадать.");
        }
        for (int i = 0; i < components.length; i++) {
            components[i].getSumOfVectors(matrix.components[i]);
        }
    }

    public void getDifferenceOfMatrices(Matrix matrix) {
        if (components.length != matrix.components.length || components[0].getSize() != matrix.components[0].getSize()) {
            throw new IllegalArgumentException("Число столбцов и строк в матрицах должно совпадать.");
        }
        for (int i = 0; i < components.length; i++) {
            components[i].getDifferenceOfVectors(matrix.components[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < components.length; i++) {
            if (i == 0) {
                sb.append(components[0].toString());
            } else {
                sb.append(",")
                        .append(components[i].toString());
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public static Matrix getSumOfMatrices(Matrix matrix1, Matrix matrix2) {
        if (matrix1.components.length != matrix2.components.length || matrix1.components[0].getSize() != matrix2.components[0].getSize()) {
            throw new IllegalArgumentException("Число столбцов и строк в матрицах должно совпадать.");
        }
        Matrix sumMatrices = new Matrix(matrix1);
        sumMatrices.getSumOfMatrices(matrix2);
        return sumMatrices;
    }
    public static Matrix getDifferenceOfMatrices(Matrix matrix1, Matrix matrix2) {
        if (matrix1.components.length != matrix2.components.length || matrix1.components[0].getSize() != matrix2.components[0].getSize()) {
            throw new IllegalArgumentException("Число столбцов и строк в матрицах должно совпадать.");
        }
        Matrix diffMatrices = new Matrix(matrix1);
        diffMatrices.getDifferenceOfMatrices(matrix2);
        return diffMatrices;
    }
    public static Matrix multiplyMatrixByMatrix(Matrix matrix1, Matrix matrix2){
        if (matrix1.components[0].getSize() != matrix2.components.length) {
            throw new IllegalArgumentException("Число столбцов в первой матрице должно быть равно числу строк во второй.");
        }
        return matrix1;//TODO использовать скалярное произведение из класса Vector
    }
}
