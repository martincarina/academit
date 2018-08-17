package ru.academits.martin.temperature.controller;

public interface IView {

    void setOutputDegree(double value);

    String getInputText();

/*    int getInputChoice();

    int getOutputChoice();

    IScale getInputScale(int number);

    IScale getOutScale(int number);*/

    IScale getInputScale();

    IScale getOutputScale();

    void showInputErrorMessage();

    void showTemperatureErrorMessage();//TODO
}
