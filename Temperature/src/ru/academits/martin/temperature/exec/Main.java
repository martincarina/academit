package ru.academits.martin.temperature.exec;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("My first GUI application");
                frame.setSize(300, 200); // размер окна
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // задать, что делать при закрытии окна –
// EXIT_ON_CLOSE - завершить программу
// по умолчанию это не так – программа не завершается
                frame.setVisible(true); // показать фрейм
            }
        });
    }
}
