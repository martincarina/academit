package ru.academits.martin.temperature.controller;

import ru.academits.martin.temperature.model.IScale;

public interface IView {

    void setOutputDegree(double value);

    String getInputText();

    IScale getInputScale();

    IScale getOutputScale();

    void showInputErrorMessage();

    void showTemperatureErrorMessage();//TODO
}
