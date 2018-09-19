document.addEventListener("DOMContentLoaded", function () {
    var convertButton = document.getElementById("convert-button");
    var inputTemperature = document.getElementById("input-temperature");

    var celsiusTemperature = document.getElementById("Celsius-temperature");
    var fahrenheitTemperature = document.getElementById("Fahrenheit-temperature");

    function convertToFahrenheit(temp){
        return temp * 9 / 5 + 32;
    }

    function cleanField(field){
    field.value = "";
    }

    convertButton.addEventListener("click", function () {
        var temperatureC = inputTemperature.value;

        if(isNaN(Number(temperatureC))){
            alert("Введите число!");
            cleanField(inputTemperature);
            cleanField(celsiusTemperature);
            cleanField(fahrenheitTemperature);
            return;
        }
        if(temperatureC < -273.15){
            alert("Температура не может быть ниже абсолютного нуля!");
            cleanField(inputTemperature);
            cleanField(celsiusTemperature);
            cleanField(fahrenheitTemperature);
            return;
        }
        celsiusTemperature.value = temperatureC;
        fahrenheitTemperature.value =  convertToFahrenheit(temperatureC).toFixed(2);
        cleanField(inputTemperature);
    });

});
