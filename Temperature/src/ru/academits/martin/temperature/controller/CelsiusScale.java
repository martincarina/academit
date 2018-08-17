package ru.academits.martin.temperature.controller;

public class CelsiusScale implements IScale {
    private static final String NAME = "Цельсий";
    private static final double ABSOLUTE_ZERO = -273.15;

    private double value;

    @Override
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
        return value;
    }

    @Override
    public void convertFromCelsius(double valueCelsius) {
        this.value = valueCelsius;
    }
}
