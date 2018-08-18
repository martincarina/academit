package ru.academits.martin.temperature.controller;

import ru.academits.martin.temperature.model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private Model model = new Model();
    private IView view1;

    public Controller(IView view) {
        view1 = view;
    }

    @Override
    public void actionPerformed(ActionEvent event) throws NumberFormatException {
        try {
            double degree = Double.parseDouble(view1.getInputText());

            try {
                view1.getInputScale().setValue(degree);
            } catch (IllegalArgumentException e1) {
                view1.showTemperatureErrorMessage();
                return;
            }

            model.convertTemperature(view1.getInputScale(), view1.getOutputScale());
            refreshView();
        } catch (NumberFormatException e) {
            view1.showInputErrorMessage();
        }
    }

    private void refreshView() {
        view1.setOutputDegree(view1.getOutputScale().getValue());
    }
}