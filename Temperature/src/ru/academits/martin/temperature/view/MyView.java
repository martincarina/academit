package ru.academits.martin.temperature.view;

import ru.academits.martin.temperature.controller.*;
import ru.academits.martin.temperature.model.CelsiusScale;
import ru.academits.martin.temperature.model.FahrenheitScale;
import ru.academits.martin.temperature.model.IScale;
import ru.academits.martin.temperature.model.KelvinScale;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MyView extends JFrame implements IView {
    private JTextField textFieldInput = new JTextField(20);
    private JTextField textFieldOutput = new JTextField(20);
    private ArrayList<IScale> scales = new ArrayList<>(Arrays.asList(new FahrenheitScale(), new CelsiusScale(), new KelvinScale()));
    private JComboBox<String> initCombo = new JComboBox<>(getScalesNames(scales));
    private JComboBox<String> finalCombo = new JComboBox<>(getScalesNames(scales));

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

    private String[] getScalesNames(ArrayList<IScale> scales) {
        String[] elements = new String[scales.size()];
        for (int i = 0; i < scales.size(); i++) {
            elements[i] = scales.get(i).getName();
        }
        return elements;
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

    @Override
    public IScale getInputScale() {
        return scales.get(initCombo.getSelectedIndex());
    }

    @Override
    public IScale getOutputScale() {
        return scales.get(finalCombo.getSelectedIndex());
    }

    @Override
    public void showTemperatureErrorMessage() {
        JOptionPane.showMessageDialog(this, "Не существует температуры ниже абсолютного нуля. Введите другое значение.", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
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