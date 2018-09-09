package ru.academits.martin.minesweeper.Model;

import java.util.ArrayList;

public class Cell {
    private boolean isOpen;
    private boolean isMarked;
    private boolean isQuestioned;
    private int value;//value < 0 - mine
    private int x;
    private int y;

    private int numberOfAdjacentCells;//3 or 5 or 8
    private Cell[] adjacentCells; //= new Cell[numberOfAdjacentCells];// или можно хранить координаты соседей


    private ArrayList<Integer> adjacentCellsX = new ArrayList<>();
    private ArrayList<Integer> adjacentCellsY = new ArrayList<>();

    public Cell(int x, int y, int value, int size) {
        this.x = x;
        this.y = y;
        this.value = value;
        defineAdjacentCells(size);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getNumberOfAdjacentCells() {
        return numberOfAdjacentCells;
    }

    public boolean getIsMarked() {
        return isMarked;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public ArrayList<Integer> getAdjacentCellsX() {
        return adjacentCellsX;
    }

    public ArrayList<Integer> getAdjacentCellsY() {
        return adjacentCellsY;
    }

    private void defineAdjacentCells(int size) {
//Остальное поле. (i-1,j-1),(i-1,j),(i-1,j+1),(i,j-1),(i,j+1),(i+1,j-1),(i+1,j),(i+1,j+1)
        int[] adjacentCellsXCoordinates = {x - 1, x - 1, x - 1, x, x, x + 1, x + 1, x + 1};
        int[] adjacentCellsYCoordinates = {y - 1, y, y + 1, y - 1, y + 1, y - 1, y, y + 1};
        for (int i = 0; i < adjacentCellsXCoordinates.length; i++) {
            if (adjacentCellsXCoordinates[i] >= 0 && adjacentCellsYCoordinates[i] >= 0
                    && adjacentCellsXCoordinates[i] <= size - 1 && adjacentCellsYCoordinates[i] <= size - 1) {
                adjacentCellsX.add(adjacentCellsXCoordinates[i]);
                adjacentCellsY.add(adjacentCellsYCoordinates[i]);
                numberOfAdjacentCells += 1;
            }
        }
    }

    public void incrementValue() {
        value += 1;
    }
}
