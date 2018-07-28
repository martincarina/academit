package ru.academits.martin.temperature.view;

import javax.swing.*;
import java.awt.*;


public class MyWindow {
    public void start() {
        JFrame frame = new JFrame("Конвертер температур");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JButton convertButton = new JButton("Конвертировать");
        convertButton.addActionListener(e -> {
            if (convertButton.getText().equals("Конвертировать")) {
                convertButton.setText("Ok");
            } else {
                convertButton.setText("Конвертировать");
            }
        });
//        convertButton.setPreferredSize(new Dimension(120,30));
        panel.add(convertButton);
        frame.add(panel, BorderLayout.PAGE_END);

        Box initTemperatureBox = Box.createVerticalBox();
        initTemperatureBox.setPreferredSize(new Dimension(frame.getWidth() / 2, frame.getHeight()));

//        JPanel initTemperaturePanel = new JPanel(new BorderLayout());

        JLabel temperatureScaleLabel = new JLabel("Выберете шкалу:", SwingConstants.RIGHT);//не работает SwingConstants.RIGHT
        JLabel initTemperatureLabel = new JLabel("Введите температуру:", SwingConstants.LEFT);
        JTextField initTemperatureField = new JTextField(20);
        //  initTemperatureField.setPreferredSize(new Dimension(0, 0));
        //
        String[] elements = new String[]{"Кельвин", "Цельсий", "Фаренгейт"};
        JComboBox initCombo = new JComboBox(elements);
        initCombo.setSelectedIndex(1);

        //        initTemperaturePanel.add(initTemperatureLabel, BorderLayout.PAGE_START);
//        initTemperaturePanel.add(initTemperatureField, BorderLayout.PAGE_END);

        //String text = textField.getText();
        //int number = Integer.parse(text);

        initTemperatureBox.add(temperatureScaleLabel);
        initTemperatureBox.add(Box.createVerticalStrut(10));

        initTemperatureBox.add(initCombo);

        //       initTemperatureBox.add(scalePanel);
        initTemperatureBox.add(Box.createVerticalStrut(10));
        initTemperatureBox.add(initTemperatureLabel);
        initTemperatureBox.add(Box.createVerticalGlue());
        initTemperatureBox.add(initTemperatureField);
        initTemperatureBox.add(Box.createVerticalStrut(100));

//        frame.add(initTemperaturePanel, BorderLayout.LINE_START);
        frame.add(initTemperatureBox, BorderLayout.LINE_START);

        Box finalTemperatureBox = Box.createVerticalBox();
        finalTemperatureBox.setPreferredSize(new Dimension(frame.getWidth() / 3, frame.getHeight()));

        JLabel temperatureScaleLabel1 = new JLabel("Выберете шкалу:");
        JLabel finalTemperatureLabel = new JLabel("Результат:");

        JComboBox finalCombo = new JComboBox(elements);
        finalCombo.setSelectedIndex(1);

        JTextField finalTemperatureField = new JTextField(20);

        finalTemperatureBox.add(temperatureScaleLabel1);
        finalTemperatureBox.add(Box.createVerticalStrut(10));
        finalTemperatureBox.add(finalCombo);
        finalTemperatureBox.add(Box.createVerticalStrut(10));
        finalTemperatureBox.add(finalTemperatureLabel);
        finalTemperatureBox.add(Box.createVerticalGlue());
        finalTemperatureBox.add(finalTemperatureField);
        finalTemperatureBox.add(Box.createVerticalStrut(100));

        frame.add(finalTemperatureBox, BorderLayout.LINE_END);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setResizable(false);
        frame.setVisible(true);
    }
}
