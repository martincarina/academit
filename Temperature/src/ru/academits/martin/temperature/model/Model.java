package ru.academits.martin.temperature.model;

public class Model {
    public double convertTemperature(IScale scale1, IScale scale2, double degree) {
        double valueCelsius = scale1.convertToCelsius(degree);
        return scale2.convertFromCelsius(valueCelsius);
    }
}