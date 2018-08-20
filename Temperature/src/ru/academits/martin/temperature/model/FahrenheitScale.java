package ru.academits.martin.temperature.model;

public class FahrenheitScale implements IScale {

    private static final String NAME = "Фаренгейт";
    private static final double ABSOLUTE_ZERO = -459.67;
    private static final double ZERO_FAHRENHEIT = 32;

    public String getName() {
        return NAME;
    }

    @Override
    public double convertToCelsius(double value) {
        if (value < ABSOLUTE_ZERO) {
            throw new IllegalArgumentException("Температура не может быть ниже абсолютного нуля.");
        }
        return (value - ZERO_FAHRENHEIT) * 5 / 9;
    }

    @Override
    public double convertFromCelsius(double valueCelsius) {
        return valueCelsius * 9 / 5 + ZERO_FAHRENHEIT;
    }
}