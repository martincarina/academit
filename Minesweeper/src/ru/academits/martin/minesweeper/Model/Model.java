package ru.academits.martin.minesweeper.Model;

import java.util.ArrayList;
import java.util.Random;

public class Model {
    private int size = 9;
    private int minesNumber = 10;
//    private int[][] field = new int[size][size];
    private Cell[][] minefield = new Cell[size][size];

    private ArrayList<Integer> mineXCoordinate = new ArrayList<>();
    private ArrayList<Integer> mineYCoordinate = new ArrayList<>();

    public Model() {
//        createField(size, minesNumber);
        createMinefield();
        fillMinefield();
//        fillField();
    }

    public Model(int selectedSize, int minesNumber) {
//        field = new int[selectedSize][selectedSize];
        this.minesNumber = minesNumber;
        this.size = selectedSize;
        createMinefield();
        fillMinefield();
  //      createField(selectedSize, minesNumber);
  //      fillField();
    }

/*    public void print() {
        for (int[] elementsArray : field) {
            for (int element : elementsArray) {
                System.out.printf("%3d", element);
            }
            System.out.println();
        }
    }*/

    public void printMinefield() {
        for (Cell[] elementsArray : minefield) {
            for (Cell element : elementsArray) {
                if (element != null) {
                    System.out.printf("%3d", element.getValue());
                } else {
                    System.out.printf("%3d", 0);
                }

            }
            System.out.println();
        }
    }


    // Implementing Fisher–Yates shuffle
    private static void shuffleArray(int[] array) {
        Random rnd = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }

 /*   private void createField(int selectedSize, int minesNumber) {
        int[] lineField = new int[selectedSize * selectedSize];
        for (int i = 0; i < minesNumber; i++) {
            lineField[i] = -9;
        }
        shuffleArray(lineField);
        int count = 0;
        for (int i = 0; i < selectedSize; i++) {
            for (int j = 0; j < selectedSize; j++) {
                field[i][j] = lineField[count++];
            }
        }
    }*/

    private void createMinefield() {
        int[] lineField = new int[size * size];
        for (int i = 0; i < minesNumber; i++) {
            lineField[i] = -9;
        }
        shuffleArray(lineField);
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (lineField[count] < 0) {
                    minefield[i][j] = new Cell(i, j, lineField[count], size);
                    //можно координаты бомб сохранять и потом только их соседей смотреть
                    mineXCoordinate.add(i);
                    mineYCoordinate.add(j);
                }
                count++;
            }
        }
    }

    private void fillMinefield() {
        for (int i = 0; i < minesNumber; i++) {
            Cell currentCell = minefield[mineXCoordinate.get(i)][mineYCoordinate.get(i)];
//            System.out.printf("%d: x=%d y=%d%n", currentCell.getValue(), mineXCoordinate.get(i), mineYCoordinate.get(i));
            ArrayList<Integer> cellX = currentCell.getAdjacentCellsX();
            ArrayList<Integer> cellY = currentCell.getAdjacentCellsY();
            for (int j = 0; j < currentCell.getNumberOfAdjacentCells(); j++) {
                if (minefield[cellX.get(j)][cellY.get(j)] != null) {
                    minefield[cellX.get(j)][cellY.get(j)].incrementValue();
                } else {
                    minefield[cellX.get(j)][cellY.get(j)] = new Cell(i, j, 1, size);
                }
            }
        }
    }

/*    private void fillField() {
        //TODO сделать условие boolean bomb = (x < 0)

        if (field[0][0] < 0) {
            //Левый верхний угол. (i,j+1),(i+1,j+1),(i+1,j) делаем +1 в этих ячейках
            field[0][1] += 1;
            field[1][1] += 1;
            field[1][0] += 1;
        }

        if (field[size - 1][size - 1] < 0) {
            //Правый нижний угол. (i-1,j),(i-1,j-1),(i,j-1) делаем +1 в этих ячейках
            field[size - 2][size - 1] += 1;
            field[size - 2][size - 2] += 1;
            field[size - 1][size - 2] += 1;
        }

        if (field[0][size - 1] < 0) {
            //Правый верхний угол. (i,j-1),(i+1,j-1),(i+1,j) делаем +1 в этих ячейках
            field[0][size - 2] += 1;
            field[1][size - 2] += 1;
            field[1][size - 1] += 1;
        }

        if (field[size - 1][0] < 0) {
            //Левый нижний угол. i-1,j),(i-1,j+1),(i,j+1) делаем +1 в этих ячейках
            field[size - 2][0] += 1;
            field[size - 2][1] += 1;
            field[size - 1][1] += 1;
        }

        for (int j = 1; j < size - 1; j++) {
            if (field[0][j] < 0) {
                //Верхняя строка. (i,j-1),(i+1,j-1),(i+1,j),(i,j+1),(i+1,j+1), делаем +1 в этих ячейках
                field[0][j - 1] += 1;
                field[1][j - 1] += 1;
                field[1][j] += 1;
                field[0][j + 1] += 1;
                field[1][j + 1] += 1;
            }
        }

        for (int j = 1; j < size - 1; j++) {
            if (field[size - 1][j] < 0) {
                //Нижняя строка. (i,j-1),(i-1,j-1),(i-1,j),(i-1,j+1),(i,j+1) делаем +1 в этих ячейках
                field[size - 2][j - 1] += 1;
                field[size - 2][j] += 1;
                field[size - 2][j + 1] += 1;
                field[size - 1][j - 1] += 1;
                field[size - 1][j + 1] += 1;
            }
        }

        for (int i = 1; i < size - 1; i++) {
            if (field[i][0] < 0) {
                //Левый столбец. (i-1,j),(i-1,j+1),(i+1,j),(i,j+1),(i+1,j+1) делаем +1 в этих ячейках
                field[i - 1][0] += 1;
                field[i - 1][1] += 1;
                field[i][1] += 1;
                field[i + 1][0] += 1;
                field[i + 1][1] += 1;
            }
        }

        for (int i = 1; i < size - 1; i++) {
            if (field[i][size - 1] < 0) {
                //Правый столбец. (i+1,j),(i+1,j-1),(i,j-1),(i-1,j-1),(i-1,j) делаем +1 в этих ячейках
                field[i - 1][size - 2] += 1;
                field[i - 1][size - 1] += 1;
                field[i][size - 2] += 1;
                field[i + 1][size - 2] += 1;
                field[i + 1][size - 1] += 1;
            }
        }

        for (int i = 1; i < size - 1; i++) {
            for (int j = 1; j < size - 1; j++) {
                if (field[i][j] < 0) {
                    field[i - 1][j - 1] += 1;
                    field[i - 1][j] += 1;
                    field[i - 1][j + 1] += 1;
                    field[i][j - 1] += 1;
                    field[i][j + 1] += 1;
                    field[i + 1][j - 1] += 1;
                    field[i + 1][j] += 1;
                    field[i + 1][j + 1] += 1;
                    //Остальное поле. (i-1,j-1),(i-1,j),(i-1,j+1),(i,j-1),(i,j+1),(i+1,j-1),(i+1,j),(i+1,j+1) делаем +1 в этих ячейках
                }
            }
        }

*/
/*        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] == 9) {
                    if (i == 0 && j == 0) {
                        //Левый верхний угол. Смотрим (i,j+1),(i+1,j+1),(i+1,j), если не бомбы делаем +1 в этих ячейках
                    } else if (i == 0 && j == size - 1) {
                        //Правый верхний угол. Смотрим (i,j-1),(i+1,j-1),(i+1,j), если не бомбы делаем +1 в этих ячейках
                    } else if (i == 0) {
                        //Верхняя строка. Смотрим (i,j-1),(i+1,j-1),(i+1,j),(i,j+1),(i+1,j+1), если не бомбы делаем +1 в этих ячейках
                    } else if (j == 0 && i == size - 1) {
                        //Левый нижний угол. Смотрим (i-1,j),(i-1,j+1),(i,j+1), если не бомбы делаем +1 в этих ячейках
                    } else if (j == 0) {
                        //Левый столбец. Смотрим (i-1,j),(i-1,j+1),(i+1,j),(i,j+1),(i+1,j+1), если не бомбы делаем +1 в этих ячейках
                    } else if (j == size - 1 && i == size - 1) {
                        //Правый нижний угол. Смотрим (i-1,j),(i-1,j-1),(i,j-1), если не бомбы делаем +1 в этих ячейках
                    } else if (i == size - 1) {
                        //Нижняя строка. Смотрим (i,j-1),(i-1,j-1),(i-1,j),(i-1,j+1),(i,j+1), если не бомбы делаем +1 в этих ячейках
                    } else if (j == size - 1) {
                        //Правый столбец. Смотрим (i+1,j),(i+1,j-1),(i,j-1),(i-1,j-1),(i-1,j), если не бомбы делаем +1 в этих ячейках
                    } else {
                        //Остальное поле. Смотрим (i-1,j-1),(i-1,j),(i-1,j+1),(i,j-1),(i,j+1),(i+1,j-1),(i+1,j),(i+1,j+1), если не бомбы делаем +1 в этих ячейках
                    }
                }
            }
        }
    }*/
}
