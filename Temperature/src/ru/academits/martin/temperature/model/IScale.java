package ru.academits.martin.temperature.model;

public interface IScale {

    String getNAME();

    double getValue();

    void setValue(double value);

    double convertToCelsius();

    void convertFromCelsius(double valueCelsius);
}
