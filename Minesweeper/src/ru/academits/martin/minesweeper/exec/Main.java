package ru.academits.martin.minesweeper.exec;

import ru.academits.martin.minesweeper.Model.Cell;
import ru.academits.martin.minesweeper.Model.Model;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        model.printMinefield();
 //       model.print();

        //     Model model1 = new Model(5,4);
        //      model1.print();

 /*       Cell cell0 = new Cell(0, 0, 0, 9);
        ArrayList<Integer> cell0X = cell0.getAdjacentCellsX();
        ArrayList<Integer> cell0Y = cell0.getAdjacentCellsY();
        System.out.println("X");
        for (int e : cell0X) {
            System.out.println(e);
        }
        System.out.println("Y");
        for (int e : cell0Y) {
            System.out.println(e);
        }
        System.out.println("Number of adjacent cells");
        System.out.println(cell0.getNumberOfAdjacentCells());*/
    }
}
