package ru.academits.martin.temperature.controller;

public class FahrenheitScale implements IScale {

    private static final String NAME = "Фаренгейт";
    private static final double ABSOLUTE_ZERO = -459.67;
    private static final double ZERO_FAHRENHEIT = 32;

    private double value;

    public String getNAME() {
        return NAME;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void setValue(double value) throws NumberFormatException {
        if (value < ABSOLUTE_ZERO) {
            throw new NumberFormatException();
        }
        this.value = value;
    }

    @Override
    public double convertToCelsius() {
        return (this.value - ZERO_FAHRENHEIT) * 5 / 9;
    }

    @Override
    public void convertFromCelsius(double valueCelsius) {
        this.value = valueCelsius * 9 / 5 + ZERO_FAHRENHEIT;
    }
}