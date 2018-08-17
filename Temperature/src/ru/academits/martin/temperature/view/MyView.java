package ru.academits.martin.temperature.view;

import ru.academits.martin.temperature.controller.*;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MyView extends JFrame implements IView {
    private JTextField textFieldInput = new JTextField(20);
    private JTextField textFieldOutput = new JTextField(20);

    private IScale[] scales = {new FahrenheitScale(), new CelsiusScale(), new KelvinScale()};//TODO потом на ArrayList поменять
    //    private ArrayList<IScale> scales = new ArrayList<>();// = new ArrayList<>(Arrays.asList(new FahrenheitScale(),new CelsiusScale(), new KelvinScale()));
    private String[] elements = new String[scales.length];

    private String[] getScales(IScale[] scales) {
        for (int i = 0; i < scales.length; i++) {
            elements[i] = scales[i].getNAME();
        }
        return elements;
    }

    /*   private String[] elements = new String[]{"Фаренгейт", "Цельсий", "Кельвин"};
    private JComboBox<String> initCombo = new JComboBox<>(elements);
    private JComboBox<String> finalCombo = new JComboBox<>(elements);*/

    private JComboBox<String> initCombo = new JComboBox<>(getScales(scales));
    private JComboBox<String> finalCombo = new JComboBox<>(getScales(scales));

    // private JComboBox<IScale> initCombo = new JComboBox<IScale>();

    public MyView() {
        setLayout(new FlowLayout());

        JLabel scaleInput = new JLabel("Шкала ввода:");
        JLabel scaleOutput = new JLabel("Шкала результата:");
        JLabel labelInput = new JLabel("Поле ввода:");
        JLabel labelOutput = new JLabel("Результат:");

        Box initTemperatureBox = Box.createVerticalBox();
        initTemperatureBox.add(scaleInput);
        initTemperatureBox.add(initCombo);
        initTemperatureBox.add(labelInput);
        initTemperatureBox.add(textFieldInput);
        add(initTemperatureBox);

        Box finalTemperatureBox = Box.createVerticalBox();
        finalTemperatureBox.add(scaleOutput);
        finalTemperatureBox.add(finalCombo);
        finalTemperatureBox.add(labelOutput);
        finalTemperatureBox.add(textFieldOutput);
        textFieldOutput.setEditable(false);
        add(finalTemperatureBox);

        Controller controller = new Controller(this);
        JButton button = new JButton("Конвертировать");
        button.addActionListener(controller);
        add(button);
    }

    public void start() {
        setTitle("Конвертер температур");
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void setOutputDegree(double value) {
        Locale.setDefault(new Locale("en", "US"));
        String formattedDouble = new DecimalFormat("#0.##").format(value);
        textFieldOutput.setText(formattedDouble);
    }

    @Override
    public String getInputText() {
        return textFieldInput.getText();
    }

 /*   @Override
    //выдает номер шкалы ввода в списке
    public int getInputChoice() {
        return initCombo.getSelectedIndex();
    }

    @Override
    //выдает номер шкалы результата в списке
    public int getOutputChoice() {
        return finalCombo.getSelectedIndex();
    }

    @Override
    public IScale getInputScale(int number) {
        return scales[number];
    }

    @Override
    public IScale getOutScale(int number) {
        return scales[number];
    }*/

    @Override
    public IScale getInputScale() {
        return scales[initCombo.getSelectedIndex()];
    }

    @Override
    public IScale getOutputScale() {
        return scales[finalCombo.getSelectedIndex()];
    }

    @Override
    public void showTemperatureErrorMessage() {//TODO сейчас не работает
        JOptionPane.showMessageDialog(this, "Не существует температуры ниже 0 K. Введите другое значение.", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
        textFieldInput.setText(null);
        textFieldOutput.setText(null);
    }

    @Override
    public void showInputErrorMessage() {
        JOptionPane.showMessageDialog(this, "Неверный формат данных. Введите число.", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
        textFieldInput.setText(null);
        textFieldOutput.setText(null);
    }
}