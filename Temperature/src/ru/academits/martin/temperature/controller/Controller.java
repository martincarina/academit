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
            if (view1.getInputChoice() == 0) {
                model.setValueFahrenheit(degree);
            } else if (view1.getInputChoice() == 1) {
                model.setValueCelsius(degree);
            } else {
                model.setValueKelvin(degree);
            }
            RefreshView();
        } catch (NumberFormatException e) {
            view1.showInputErrorMessage();
        }
    }

    private void RefreshView() {
        if (model.getValueKelvin() < 0) {
            view1.showTemperatureErrorMessage();
        } else {
            if (view1.getOutputChoice() == 0) {
                view1.setOutputDegree(model.getValueFahrenheit());
            } else if (view1.getOutputChoice() == 1) {
                view1.setOutputDegree(model.getValueCelsius());
            } else {
                view1.setOutputDegree(model.getValueKelvin());
            }
        }
    }
}
