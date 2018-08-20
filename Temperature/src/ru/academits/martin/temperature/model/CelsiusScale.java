package ru.academits.martin.temperature.model;

public class CelsiusScale implements IScale {
    private static final String NAME = "Цельсий";
    private static final double ABSOLUTE_ZERO = -273.15;

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public double convertToCelsius(double value) {
        if (value < ABSOLUTE_ZERO) {
            throw new IllegalArgumentException("Температура не может быть ниже абсолютного нуля.");
        }
        return value;
    }

    @Override
    public double convertFromCelsius(double valueCelsius) {
        return valueCelsius;
    }
}