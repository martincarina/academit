package ru.academits.martin.temperature.controller;

public interface IView {
    /// <summary>/// Вывод градусов Фаренгейта/// </summary>
    void setInputDegree(double value);

    /// <summary>/// Вывод градусов Цельсия/// </summary>
    void setOutputDegree(double value);

    /// <summary>/// Ввод нового значения градусов/// </summary>
    double getInputDegree();

    //выбор шкалы ввода
    int getInputChoice();

    //выбор шкалы вывода результата
    int getOutputChoice();
}
