package ru.academits.martin.temperature.controller;

public interface IView {

    void setOutputDegree(double value);

    String getInputText();

    int getInputChoice();

    int getOutputChoice();

    void showInputErrorMessage();

    void showTemperatureErrorMessage();
}
