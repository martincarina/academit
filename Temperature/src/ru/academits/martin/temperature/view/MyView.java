package ru.academits.martin.temperature.view;

import ru.academits.martin.temperature.controller.Controller;
import ru.academits.martin.temperature.controller.IView;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Locale;

public class MyView extends JFrame implements IView {
    private JTextField textFieldInput = new JTextField(20);
    private JTextField textFieldOutput = new JTextField(20);
    private String[] elements = new String[]{"Фаренгейт", "Цельсий", "Кельвин"};
    @SuppressWarnings("unchecked")
    private JComboBox initCombo = new JComboBox(elements);
    @SuppressWarnings("unchecked")
    private JComboBox finalCombo = new JComboBox(elements);

    public MyView() {

        Controller controller = new Controller(this);
        setLayout(new FlowLayout());
        JButton button = new JButton("Конвертировать");
        button.addActionListener(controller);
        initCombo.setSelectedIndex(0);
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
        add(button);
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

    @Override
    public int getInputChoice() {
        return initCombo.getSelectedIndex();
    }

    @Override
    public int getOutputChoice() {
        return finalCombo.getSelectedIndex();
    }

    @Override
    public void showTemperatureErrorMessage() {
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