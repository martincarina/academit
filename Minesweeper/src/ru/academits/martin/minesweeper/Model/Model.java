package ru.academits.martin.minesweeper.Model;

import java.util.Random;

public class Model {
    private int size = 9;
    private int minesNumber = 10;
    private int[][] field = new int[size][size];

    public Model() {
        createField(size, minesNumber);
    }

    public Model(int selectedSize, int minesNumber) {
        field = new int[selectedSize][selectedSize];
        this.minesNumber = minesNumber;
        this.size = selectedSize;
        createField(selectedSize, minesNumber);
    }

    public void print() {
        for (int[] elementsArray : field) {
            for (int element : elementsArray) {
                System.out.print(element);
            }
            System.out.println();
        }
//        return field.toString();
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

    private void createField(int selectedSize, int minesNumber) {
        int[] lineField = new int[selectedSize * selectedSize];
        for (int i = 0; i < minesNumber; i++) {
            lineField[i] = 9;
        }
        shuffleArray(lineField);
        int count = 0;
        for (int i = 0; i < selectedSize; i++) {
            for (int j = 0; j < selectedSize; j++) {
                field[i][j] = lineField[count++];
            }
        }
    }

    private void fillField() {//todo сделать первоначально бомбу -9, а потом не проверять для окружающих ячеек бомба или нет, а всегда прибавлять 1.
        //TODO сделать условие boolean bomb = (x < 0)
        if (field[0][0] == 9) {
            //Левый верхний угол. Смотрим (i,j+1),(i+1,j+1),(i+1,j), если не бомбы делаем +1 в этих ячейках
        }

        if (field[size - 1][size - 1] == 9) {
            //Правый нижний угол. Смотрим (i-1,j),(i-1,j-1),(i,j-1), если не бомбы делаем +1 в этих ячейках
        }

        if (field[0][size - 1] == 9) {
            //Правый верхний угол. Смотрим (i,j-1),(i+1,j-1),(i+1,j), если не бомбы делаем +1 в этих ячейках
        }

        if (field[size - 1][0] == 9) {
            //Левый нижний угол. Смотрим (i-1,j),(i-1,j+1),(i,j+1), если не бомбы делаем +1 в этих ячейках
        }

        for (int j = 1; j < size - 1; j++) {
            if (field[0][j] == 9) {
                //Верхняя строка. Смотрим (i,j-1),(i+1,j-1),(i+1,j),(i,j+1),(i+1,j+1), если не бомбы делаем +1 в этих ячейках
            }
        }

        for (int j = 1; j < size - 1; j++) {
            if (field[size - 1][j] == 9) {
                //Нижняя строка. Смотрим (i,j-1),(i-1,j-1),(i-1,j),(i-1,j+1),(i,j+1), если не бомбы делаем +1 в этих ячейках
            }
        }

        for (int i = 1; i < size - 1; i++) {
            if (field[i][0] == 9) {
                //Левый столбец. Смотрим (i-1,j),(i-1,j+1),(i+1,j),(i,j+1),(i+1,j+1), если не бомбы делаем +1 в этих ячейках
            }
        }

        for (int i = 1; i < size - 1; i++) {
            if (field[i][size - 1] == 9) {
                //Правый столбец. Смотрим (i+1,j),(i+1,j-1),(i,j-1),(i-1,j-1),(i-1,j), если не бомбы делаем +1 в этих ячейках
            }
        }

        for (int i = 1; i < size - 1; i++) {
            for (int j = 1; j < size - 1; j++) {
                if (field[i][j] == 9) {
                    if(field[i-1][j-1]!=9){

                    }
                    //Остальное поле. Смотрим (i-1,j-1),(i-1,j),(i-1,j+1),(i,j-1),(i,j+1),(i+1,j-1),(i+1,j),(i+1,j+1), если не бомбы делаем +1 в этих ячейках
                }
            }
        }


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
        }*/
    }
}
