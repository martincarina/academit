package ru.academits.martin.temperature.controller;

import ru.academits.martin.temperature.model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private Model model = new Model();
    private IView view;

    public Controller(IView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent event) throws NumberFormatException {
        try {
            double degree = Double.parseDouble(view.getInputText());
            try {
                view.setOutputDegree(model.convertTemperature(view.getInputScale(), view.getOutputScale(), degree));
            } catch (IllegalArgumentException e1) {
                view.showTemperatureErrorMessage();
            }
        } catch (NumberFormatException e) {
            view.showInputErrorMessage();
        }
    }
}