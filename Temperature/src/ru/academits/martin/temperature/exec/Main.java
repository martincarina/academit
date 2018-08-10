package ru.academits.martin.temperature.exec;

import ru.academits.martin.temperature.view.MyView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MyView frame1 = new MyView();
            frame1.setTitle("Конвертер температур");
            frame1.pack();
            frame1.setLocationRelativeTo(null);
            frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame1.setVisible(true);
        });
    }
}
