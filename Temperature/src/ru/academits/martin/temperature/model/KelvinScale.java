package ru.academits.martin.temperature.model;

public class KelvinScale implements IScale {
    private static final String NAME = "Кельвин";
    private static final double ABSOLUTE_ZERO = 0;
    private static final double ZERO_KELVIN = 273.15;

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public double convertToCelsius(double value) {
        if (value < ABSOLUTE_ZERO) {
            throw new IllegalArgumentException("Температура не может быть ниже абсолютного нуля.");
        }
        return value - ZERO_KELVIN;
    }

    @Override
    public double convertFromCelsius(double valueCelsius) {
        return valueCelsius + ZERO_KELVIN;
    }
}