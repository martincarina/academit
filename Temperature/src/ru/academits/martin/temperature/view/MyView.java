package ru.academits.martin.temperature.view;

import ru.academits.martin.temperature.controller.Controller;
import ru.academits.martin.temperature.controller.IView;

import javax.swing.*;
import java.awt.*;

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
        JLabel labelInput = new JLabel("Поле ввода:");
        JLabel labelOutput = new JLabel("Результат:");
        add(initCombo);
        add(labelInput);
        add(textFieldInput);
        add(finalCombo);
        add(labelOutput);
        add(textFieldOutput);
        textFieldOutput.setEditable(false);
        add(button);
    }

    @Override
    public void setInputDegree(double value) {
        textFieldInput.setText(Double.toString(value));
    }

    @Override
    public void setOutputDegree(double value) {
        textFieldOutput.setText(Double.toString(value));
    }

    @Override
    public double getInputDegree() throws NumberFormatException{
        //TODO возможно, проверку надо вынести в контроллер(тогда там же строку в число преобразовыать и добавить showDialog в интерфейс
//        return Double.parseDouble(textFieldInput.getText());
        try {
            return Double.parseDouble(textFieldInput.getText());
        } catch (NumberFormatException e) {
            showDialog();
            throw new NumberFormatException("Неверный формат данных");
        }
    }

    @Override
    public int getInputChoice() {
        return initCombo.getSelectedIndex();
    }

    @Override
    public int getOutputChoice() {
        return finalCombo.getSelectedIndex();
    }

  /*  private static boolean isNumber(String s) throws NumberFormatException {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }*/
  private void showDialog(){
      JOptionPane.showMessageDialog(this,"Неверный формат данных. Ввведите число.", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
      textFieldInput.setText(null);
      textFieldOutput.setText(null);
  }

}
