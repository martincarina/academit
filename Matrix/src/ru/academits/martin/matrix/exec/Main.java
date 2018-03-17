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
        System.out.printf("Вектор-строка с индексом 1 matrix1: %s%n",vector3.toString());

        matrix0.setRow(0, vector2);
        System.out.printf("matrix0 = %s%n", matrix0.toString());

        Vector vector4 = matrix0.getColumn(0);
        System.out.printf("Вектор-столбец с индексом 1 matrix0: %s%n",vector4.toString());

        Matrix matrix4 = matrix1.transposeMatrix();
        System.out.printf("matrix4 = %s%n", matrix4.toString());

        matrix1.multiplyMatrixByScalar(2);
        System.out.printf("Умножение на скаляр matrix1 : новая matrix1 = %s%n", matrix1.toString());

        Vector vector5 = matrix4.multiplyMatrixByVector(vector2);
        System.out.printf("Результат умножения матрицы на вектор: %s%n",vector5.toString());
    }
}
