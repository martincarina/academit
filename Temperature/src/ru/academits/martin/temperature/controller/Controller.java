package ru.academits.martin.temperature.controller;

import ru.academits.martin.temperature.model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private Model model = new Model();
    private IView view1;
    private double valueCelsius;

    public Controller(IView view) {
        view1 = view;
    }

    @Override
    public void actionPerformed(ActionEvent event) throws NumberFormatException {
        try {
            //перевод введенного значения в число
            double degree = Double.parseDouble(view1.getInputText());
            //определяем входную шкалу(контроллер)
 //           IScale inputScale = view1.getInputScale(view1.getInputChoice());
            IScale inputScale = view1.getInputScale();
            //устанавливаем в шкалу прочитанное значение(контроллер)
            inputScale.setValue(degree);

        //TODO Сделать через модель, интерфейс и классы шкал перенести в модель
            //перевод значения в Цельсий(это работа модели)
            valueCelsius = inputScale.convertToCelsius();

      /*      if (view1.getInputChoice() == 0) {
                model.setValueFahrenheit(degree);
            } else if (view1.getInputChoice() == 1) {
                model.setValueCelsius(degree);
            } else {
                model.setValueKelvin(degree);
            }*/


            RefreshView();
        } catch (NumberFormatException e) {
            view1.showInputErrorMessage();
        }
    }

    private void RefreshView() {
        //определяем шкалу результата(контроллер)
//        IScale outputScale = view1.getOutScale(view1.getOutputChoice());
        IScale outputScale = view1.getOutputScale();
        //перевод значения из Цельсия в нужную шкалу(модель)
        outputScale.convertFromCelsius(valueCelsius);
        //установка значения результата(контроллер)
        view1.setOutputDegree(outputScale.getValue());



 /*       if (model.getValueKelvin() < 0) {
            view1.showTemperatureErrorMessage();
        } else {
            if (view1.getOutputChoice() == 0) {
                view1.setOutputDegree(model.getValueFahrenheit());
            } else if (view1.getOutputChoice() == 1) {
                view1.setOutputDegree(model.getValueCelsius());
            } else {
                view1.setOutputDegree(model.getValueKelvin());
            }
        }*/
    }
}
