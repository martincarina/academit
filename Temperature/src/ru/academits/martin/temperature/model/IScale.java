package ru.academits.martin.temperature.model;

public interface IScale {

    String getName();

    double convertToCelsius(double value);

    double convertFromCelsius(double valueCelsius);
}
