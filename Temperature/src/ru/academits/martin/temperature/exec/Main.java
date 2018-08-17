package ru.academits.martin.temperature.exec;

import ru.academits.martin.temperature.view.MyView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MyView frame1 = new MyView();
            frame1.start();
        });
    }
}
