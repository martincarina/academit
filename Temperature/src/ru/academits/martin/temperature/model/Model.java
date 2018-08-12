package ru.academits.martin.temperature.model;

public class Model {

    private static final double ZERO_FAHRENHEIT = 32;
    private static final double ZERO_CELSIUS = 0;
    private static final double ZERO_KELVIN = 273.15;

    private double valueFahrenheit = ZERO_FAHRENHEIT;
    private double valueCelsius = ZERO_CELSIUS;
    private double valueKelvin = ZERO_KELVIN;

    public double getValueFahrenheit() {
        return valueFahrenheit;
    }


    public void setValueFahrenheit(double value) {
        valueFahrenheit = value;
        valueCelsius = (valueFahrenheit - ZERO_FAHRENHEIT) * 5 / 9;
        valueKelvin = valueCelsius + ZERO_KELVIN;
    }

    public double getValueCelsius() {
        return valueCelsius;
    }

    public void setValueCelsius(double value) {
        valueCelsius = value;
        valueFahrenheit = valueCelsius * 9 / 5 + ZERO_FAHRENHEIT;
        valueKelvin = valueCelsius + ZERO_KELVIN;
    }

    public double getValueKelvin() {
        return valueKelvin;
    }

    public void setValueKelvin(double value) {
        valueKelvin = value;
        valueCelsius = valueKelvin - ZERO_KELVIN;
        valueFahrenheit = valueCelsius * 9 / 5 + ZERO_CELSIUS;
    }
}
