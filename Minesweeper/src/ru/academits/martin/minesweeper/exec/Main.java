package ru.academits.martin.minesweeper.exec;

import ru.academits.martin.minesweeper.Model.Model;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        model.print();

        Model model1 = new Model(5,4);
        model1.print();
    }
}
