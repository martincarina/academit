package ru.academits.martin.temperature.exec;

import ru.academits.martin.temperature.view.MyWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MyWindow().start());
    }
}
