package ru.academits.martin.matrix;

import ru.academits.martin.vector.Vector;

import static ru.academits.martin.vector.Vector.getScalarMultiplication;

public class Matrix {

    private Vector[] rows;

    public Matrix(int numberOfRows, int numberOfColumns) {
        if (numberOfRows <= 0 || numberOfColumns <= 0) {
            throw new IllegalArgumentException("Размерности должны быть не меньше 1.");
        }
        rows = new Vector[numberOfRows];
        for (int i = 0; i < numberOfRows; i++) {
            rows[i] = new Vector(numberOfColumns);
        }
    }

    public Matrix(double[][] components) {
        if (components.length <= 0 || components[0].length <= 0) {
            throw new IllegalArgumentException("Размерности должны быть не меньше 1.");
        }
        int maxRow = 0;
        for (double[] component : components) {
            if (component.length > maxRow) {
                maxRow = component.length;
            }
        }
        rows = new Vector[components.length];
        for (int i = 0; i < components.length; i++) {
            rows[i] = new Vector(maxRow, components[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length <= 0) {
            throw new IllegalArgumentException("Размерность массива векторов должна быть не меньше 1.");
        }
        int maxRow = 0;
        for (Vector vector : vectors) {
            if (vector.getSize() > maxRow) {
                maxRow = vector.getSize();
            }
        }
        rows = new Vector[vectors.length];
        for (int i = 0; i < vectors.length; i++) {
            if (vectors[i].getSize() < maxRow) {
                double[] components = new double[vectors[i].getSize()];
                for (int j = 0; j < vectors[i].getSize(); j++) {
                    components[j] = vectors[i].getComponent(j);
                }
                rows[i] = new Vector(maxRow, components);
            } else {
                rows[i] = new Vector(vectors[i]);
            }
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.rows);
    }

    public int getNumberOfRows() {
        return rows.length;
    }

    public int getNumberOfColumns() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть меньше 0.");
        } else if (index >= rows.length) {
            throw new IndexOutOfBoundsException("Индекс должен быть меньше количества строк в матрице.");
        }
        return new Vector(rows[index]);
    }


    public void setRow(int index, Vector vector) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть меньше 0.");
        } else if (index >= rows.length) {
            throw new IndexOutOfBoundsException("Индекс должен быть меньше количества строк в матрице.");
        } else if (vector.getSize() != getNumberOfColumns()) {
            System.out.println("Размерность вектора должна быть равна количеству столбцов в матрице");
        } else {
            rows[index] = new Vector(vector);
        }
    }

    public Vector getColumn(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть меньше 0.");
        } else if (index >= getNumberOfColumns()) {
            throw new IndexOutOfBoundsException("Индекс должен быть меньше количества столбцов в матрице.");
        }
        double[] column = new double[rows.length];
        for (int i = 0; i < rows.length; i++) {
            column[i] = rows[i].getComponent(index);
        }
        return new Vector(column);
    }

    public void transposeMatrix() {
        Vector[] vectors = new Vector[getNumberOfColumns()];
        for (int i = 0; i < getNumberOfColumns(); i++) {
            vectors[i] = getColumn(i);
        }
        rows = vectors;
    }

    public void multiplyMatrixByScalar(double scalar) {
        for (Vector component : rows) {
            component.multiplyVectorByScalar(scalar);
        }
    }

    public double getDeterminant() {
        if (rows.length != getNumberOfColumns()) {
            throw new IllegalArgumentException("Матрица должна быть квадратной.");
        }

        Matrix copyOfMatrix = new Matrix(this);

        for (int i = 0; i < copyOfMatrix.getNumberOfColumns(); i++) {
            boolean isNullColumn = false;
            int j = i;
            while (copyOfMatrix.rows[j].getComponent(i) == 0) {
                if (j == copyOfMatrix.rows.length - 1) {
                    isNullColumn = true;
                    break;
                }
                j++;
            }
            if (isNullColumn) {
                continue;
            }

            Vector tempVector = copyOfMatrix.rows[j];
            copyOfMatrix.rows[j] = copyOfMatrix.rows[i];
            copyOfMatrix.rows[i] = tempVector;

            for (int k = i + 1; k < copyOfMatrix.rows.length; k++) {
                double coefficient = copyOfMatrix.rows[k].getComponent(i) / copyOfMatrix.rows[i].getComponent(i);
                Vector tempRaw = new Vector(copyOfMatrix.rows[i]);
                tempRaw.multiplyVectorByScalar(coefficient);
                copyOfMatrix.rows[k].getDifferenceOfVectors(tempRaw);
            }
        }
        double determinant = 1;
        for (int i = 0; i < copyOfMatrix.rows.length; i++) {
            determinant *= copyOfMatrix.rows[i].getComponent(i);
        }
        return determinant;
    }

    public Vector multiplyMatrixByVector(Vector vector) {
        if (getNumberOfColumns() != vector.getSize()) {
            throw new IllegalArgumentException("Число столбцов в матрице должно быть равно размерности вектора.");
        }
        Vector vectorResult = new Vector(rows.length);
        for (int i = 0; i < rows.length; i++) {
            vectorResult.setComponent(i, getScalarMultiplication(rows[i], vector));
        }
        return vectorResult;

    }

    public void getSumOfMatrices(Matrix matrix) {
        if (rows.length != matrix.rows.length || getNumberOfColumns() != matrix.getNumberOfColumns()) {
            throw new IllegalArgumentException("Число столбцов и строк в матрицах должно совпадать.");
        }
        for (int i = 0; i < rows.length; i++) {
            rows[i].getSumOfVectors(matrix.rows[i]);
        }
    }

    public void getDifferenceOfMatrices(Matrix matrix) {
        if (rows.length != matrix.rows.length || getNumberOfColumns() != matrix.getNumberOfColumns()) {
            throw new IllegalArgumentException("Число столбцов и строк в матрицах должно совпадать.");
        }
        for (int i = 0; i < rows.length; i++) {
            rows[i].getDifferenceOfVectors(matrix.rows[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < rows.length; i++) {
            if (i == 0) {
                sb.append(rows[0].toString());
            } else {
                sb.append(",")
                        .append(rows[i].toString());
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public static Matrix getSumOfMatrices(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getNumberOfColumns() != matrix2.getNumberOfColumns()) {
            throw new IllegalArgumentException("Число столбцов и строк в матрицах должно совпадать.");
        }
        Matrix sumMatrices = new Matrix(matrix1);
        sumMatrices.getSumOfMatrices(matrix2);
        return sumMatrices;
    }

    public static Matrix getDifferenceOfMatrices(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getNumberOfColumns() != matrix2.getNumberOfColumns()) {
            throw new IllegalArgumentException("Число столбцов и строк в матрицах должно совпадать.");
        }
        Matrix diffMatrices = new Matrix(matrix1);
        diffMatrices.getDifferenceOfMatrices(matrix2);
        return diffMatrices;
    }

    public static Matrix multiplyMatrixByMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getNumberOfColumns() != matrix2.rows.length) {
            throw new IllegalArgumentException("Число столбцов в первой матрице должно быть равно числу строк во второй.");
        }
        double[][] components = new double[matrix1.rows.length][matrix2.getNumberOfColumns()];
        for (int i = 0; i < matrix1.rows.length; i++) {
            for (int j = 0; j < matrix2.getNumberOfColumns(); j++) {
                components[i][j] = getScalarMultiplication(matrix1.getRow(i), matrix2.getColumn(j));
            }
        }
        return new Matrix(components);
    }
}
