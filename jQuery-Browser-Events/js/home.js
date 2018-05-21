$(document).ready(function () {
  $('#akronInfoDiv').hide();
  $('#minneapolisInfoDiv').hide();
  $('#louisvilleInfoDiv').hide();

  $('#akronButton').on('click', function() {
    $('#akronInfoDiv').show();
    $('#mainInfoDiv').hide();
    $('#minneapolisInfoDiv').hide();
    $('#louisvilleInfoDiv').hide();
    $('#akronWeather').hide();
  });

  $('#mainButton').on('click', function() {
    $('#mainInfoDiv').show();
    $('#akronInfoDiv').hide();
    $('#minneapolisInfoDiv').hide();
    $('#louisvilleInfoDiv').hide();
  });

  $('#minneapolisButton').on('click', function() {
    $('#minneapolisInfoDiv').show();
    $('#akronInfoDiv').hide();
    $('#mainInfoDiv').hide();
    $('#louisvilleInfoDiv').hide();
    $("#minneapolisWeather").hide();
  });

  $('#louisvilleButton').on('click', function() {
    $('#louisvilleInfoDiv').show();
    $('#akronInfoDiv').hide();
    $('#mainInfoDiv').hide();
    $('#minneapolisInfoDiv').hide();
    $('#louisvilleWeather').hide();
  });

  $('#akronWeatherButton').on('click', function() {
    $('#akronWeather').toggle('slow');
  });

  $('#minneapolisWeatherButton').on('click', function() {
    $('#minneapolisWeather').toggle('slow');
  });

  $('#louisvilleWeatherButton').on('click', function() {
    $('#louisvilleWeather').toggle('slow');
  });

  $('.table tr td').hover(
    function() {
      $(this).parent().css('background-color', 'WhiteSmoke');

    },
    function() {
      $(this).parent().css('background-color', '');
    }
  )

});
