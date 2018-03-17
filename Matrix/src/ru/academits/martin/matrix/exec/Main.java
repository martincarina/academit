package ru.academits.martin.matrix.exec;

import ru.academits.martin.matrix.Matrix;
import ru.academits.martin.matrix.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix0 = new Matrix(3, 2);
        System.out.printf("matrix0 = %s%n", matrix0.toString());

        double[][] array1 = {{1, 1, 1}, {2, 2, 2}};
        Matrix matrix1 = new Matrix(array1);
        System.out.printf("matrix1 = %s%n", matrix1.toString());

        Vector vector1 = new Vector(2);
        double[] array2 = {2, 3};
        Vector vector2 = new Vector(array2);
        Vector[] vectors = {vector1, vector2};
        Matrix matrix2 = new Matrix(vectors);
        System.out.printf("matrix2 = %s%n", matrix2.toString());

        Matrix matrix3 = new Matrix(matrix2);
        System.out.printf("Копия matrix2: matrix3 = %s%n", matrix3.toString());

        int[] matrixSize = matrix1.getSize();
        System.out.printf("Размерность матрицы: n = %d, m = %d%n", matrixSize[0], matrixSize[1]);

        Vector vector3 = matrix1.getRow(1);
        System.out.printf("Вектор-строка с индексом 1 matrix1: %s%n", vector3.toString());

        matrix0.setRow(0, vector2);
        System.out.printf("matrix0 = %s%n", matrix0.toString());

        Vector vector4 = matrix0.getColumn(0);
        System.out.printf("Вектор-столбец с индексом 1 matrix0: %s%n", vector4.toString());

        Matrix matrix4 = matrix1.transposeMatrix();
        System.out.printf("matrix4 = %s%n", matrix4.toString());

        matrix2.multiplyMatrixByScalar(2);
        System.out.printf("Умножение на скаляр matrix2 : новая matrix2 = %s%n", matrix2.toString());

        Vector vector5 = matrix4.multiplyMatrixByVector(vector2);
        System.out.printf("Результат умножения матрицы на вектор: %s%n", vector5.toString());

        matrix2.getSumOfMatrices(matrix3);
        System.out.printf("Сумма матриц matrix2 и matrix3: новая matrix2 = %s%n", matrix2.toString());

        matrix2.getDifferenceOfMatrices(matrix3);
        System.out.printf("Разность матриц matrix2 и matrix3: новая matrix2 = %s%n", matrix2.toString());

        Matrix matricesSum = Matrix.getSumOfMatrices(matrix2, matrix3);
        System.out.printf("Сумма matrix2 и matrix3 (static): matricesSum = %s%n", matricesSum.toString());

        Matrix matricesDiff = Matrix.getDifferenceOfMatrices(matrix2, matrix3);
        System.out.printf("Разность matrix2 и matrix3 (static): matricesDiff = %s%n", matricesDiff.toString());

        Matrix matricesMultiplication = Matrix.multiplyMatrixByMatrix(matrix1,matrix4);
        System.out.printf("Произведение матриц matrix1 и matrix4 (static): matricesMultiplication = %s%n", matricesMultiplication.toString());

        double[][] array3 = {{5,7,1},{-4,1,0},{2,0,3}};
        Matrix matrix5 = new Matrix(array3);
        System.out.printf("Определитель матрицы matrix5 = %f%n", matrix5.getDeterminant());

       double[][] array4 = {{2,4,1,1},{0,2,1,0},{2,1,1,3},{4,0,2,3}};
        Matrix matrix6 = new Matrix(array4);
        System.out.printf("Определитель матрицы matrix6 = %f%n", matrix6.getDeterminant());
    }
}
