package ru.academits.martin.temperature.model;

public class Model {
    public void convertTemperature(IScale scale1, IScale scale2) {
        double valueCelsius = scale1.convertToCelsius();
        scale2.convertFromCelsius(valueCelsius);
    }
}