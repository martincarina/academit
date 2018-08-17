package ru.academits.martin.temperature.controller;

public interface IScale {

    String getNAME();

    double getValue();

    void setValue(double value);

    double convertToCelsius();

    void convertFromCelsius(double valueCelsius);
}
