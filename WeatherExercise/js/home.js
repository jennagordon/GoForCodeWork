$(document).ready(function () {
    var key = '55be7c94a9ae1be2069781207d761677';


    $('#get-weather-button').click(function (event) {
        // we use event.preventDefault(); because we didn't declare type=button in html
        event.preventDefault();

        var haveValidationErrors = checkAndDisplayValidationErrors($('#weather-form').find('input'));
        if(haveValidationErrors) {
            return false;
        }

        displayCurrentConditions();

        displayFiveDayForecast()


    });
});

    function displayCurrentConditions() {
        var key = '55be7c94a9ae1be2069781207d761677';
        var url = 'http://api.openweathermap.org/data/2.5/weather?zip=' + $('#zipCode').val() + ',us&APPID=' + key + '&units=' + $('#unit').val();

        $.ajax({
            type: 'GET',
            url: url,
            success: function (weather) {
                $('#current-conditions').empty();
                $('#temp-humidity-wind').empty();
                // get the weather for city
                var city = weather.name;
                var description = weather.weather[0].description;
                var temp = weather.main.temp;
                var humidity = weather.main.humidity;
                var wind = weather.wind.speed;


                $('#current-conditions').append('<h1 class="col-lg-12">Current Conditions in ' + city + '</h1>').show();
                $('#showIcon').html('<img src= "http://openweathermap.org/img/w/' + weather.weather[0].icon + '.png">').append(description).show();
                if ($('#unit').val() == 'imperial') {
                    $('#temp-humidity-wind').append('<ul> Temperature: ' + temp + 'F</ul>').show();
                } else {
                    $('#temp-humidity-wind').append('<ul> Temperature: ' + temp + 'C</ul>').show();
                }

                $('#temp-humidity-wind').append('<ul>Humidity: ' + humidity + '%</ul>').show();
                if ($('#unit').val() == 'imperial') {
                    $('#temp-humidity-wind').append('<ul>Wind: ' + wind + ' miles/hour</ul>').show();
                } else {
                    $('#temp-humidity-wind').append('<ul>Wind: ' + wind + ' meters/hour</ul>').show();
                }


            },
            error: function () {
                $('#errorMessages')
                    .append($('<li>')
                        .attr({class: 'list-group-item list-group-item-danger'})
                        .text('Error calling web service. Please try again later.'));
            }
        });

    }

    function displayFiveDayForecast() {
        var key = '55be7c94a9ae1be2069781207d761677';
        $.ajax({
            type: 'GET',
            url: 'http://api.openweathermap.org/data/2.5/forecast?zip=' + $('#zipCode').val() + ',us&APPID=' + key + '&units=' + $('#unit').val(),
            success: function (data) {

                var description = data.list[0].weather[0].description;
                var tempMax = data.list[0].main.temp_max;
                var tempMin = data.list[0].main.temp_min;
                var tempChar;

                var date = new Date(data.list[0].dt_txt),
                    locale = "en-us",
                    month = date.toLocaleString(locale, {month: "long"});



                if ($('#unit').val() == 'imperial') {
                   tempChar = 'F';
                } else {
                    tempChar = 'C';
                }


                $('#five-day-forecast').show();


                $('#day-one').append('<p>' + data.list[0].dt_txt.substring(8, 10) + ' ' + month + '</p>')
                    .append('<img src= "http://openweathermap.org/img/w/' + data.list[0].weather[0].icon + '.png">')
                    .append('<p>' + data.list[0].weather[0].description + '</p>')
                    .append('<p> H ' + data.list[0].main.temp_max + tempChar + '  L ' + data.list[0].main.temp_min + tempChar +'</p>');

                $('#day-two').append('<p>' + data.list[2].dt_txt.substring(8,10) + ' ' + month + '</p>')
                    .append('<img src= "http://openweathermap.org/img/w/' + data.list[2].weather[0].icon + '.png">')
                    .append('<p>' + data.list[2].weather[0].description + '</p>')
                    .append('<p> H ' + data.list[2].main.temp_max + tempChar + '  L ' + data.list[2].main.temp_min + tempChar +'</p>');

                $('#day-three').append('<p>' + data.list[10].dt_txt.substring(8,10)+ ' ' + month + '</p>')
                    .append('<img src= "http://openweathermap.org/img/w/' + data.list[10].weather[0].icon + '.png">')
                    .append('<p>' + data.list[10].weather[0].description + '</p>')
                    .append('<p> H ' + data.list[10].main.temp_max + tempChar + '  L ' + data.list[5].main.temp_min + tempChar +'</p>');

                $('#day-four').append('<p>' + data.list[18].dt_txt.substring(8,10)+ ' ' + month + '</p>')
                    .append('<img src= "http://openweathermap.org/img/w/' + data.list[18].weather[0].icon + '.png">')
                    .append('<p>' + data.list[18].weather[0].description + '</p>')
                    .append('<p> H ' + data.list[18].main.temp_max + tempChar + '  L ' + data.list[18].main.temp_min + tempChar +'</p>');

                $('#day-five').append('<p>' + data.list[26].dt_txt.substring(8,10)+ ' ' + month + '</p>')
                    .append('<img src= "http://openweathermap.org/img/w/' + data.list[26].weather[0].icon + '.png">')
                    .append('<p>' + data.list[26].weather[0].description + '</p>')
                    .append('<p> H ' + data.list[26].main.temp_max + tempChar + '  L ' + data.list[26].main.temp_min + tempChar +'</p>');


            },
            error: function () {
                $('#errorMessages')
                    .append($('<li>')
                        .attr({class: 'list-group-item list-group-item-danger'})
                        .text('Error calling web service. Please try again later.'));

            }
        })


    }

    function checkAndDisplayValidationErrors(input) {

        $('#errorMessages').empty();

        var errorMessages = [];

        input.each(function() {
            if(!this.validity.valid) {
                var errorField= $('label[for=' + this.id + ']').text();
                errorMessages.push(errorField + ' ' + this.validationMessage);
            }
        });
        if (errorMessages.length > 0) {
            $.each(errorMessages, function(index, message){
                $('#errorMessages').append($('<li>').attr({class: 'list-group-item list-group-item-danger'}).text(message));
            });
            return true;
        } else {
            return false;
        }

    }
