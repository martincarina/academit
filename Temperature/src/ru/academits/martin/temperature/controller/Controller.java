package ru.academits.martin.temperature.controller;

import ru.academits.martin.temperature.model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller  implements ActionListener {

    private Model model = new Model();
    private IView view1;

    public Controller(IView view) {
        view1 = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (view1.getInputChoice() == 0) {
            model.setValueFahrenheit(view1.getInputDegree());
        } else if (view1.getInputChoice() == 1) {
            model.setValueCelsius(view1.getInputDegree());
        } else {
           model.setValueKelvin(view1.getInputDegree());
        }
  //      RefreshView();
    }

  /*  private void RefreshView() {
        view1.SetCelsius(model.getValueCelsius());
        view1.SetFarenheit(model.getValueFahrenheit());
    }*/
}
