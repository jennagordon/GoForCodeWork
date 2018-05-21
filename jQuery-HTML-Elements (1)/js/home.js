$(document).ready(function () {
  $('h1').addClass('text-center');
  $('h2').addClass('text-center');
  $('.myBannerHeading').addClass('page-header');
  $('.myBannerHeading').removeClass('myBannerHeading');
  $('#yellowHeading').text('The Yellow Team');
  $('#orangeTeamList').css('background-color', 'orange');
  $('#blueTeamList').css('background-color', 'blue');
  $('#redTeamList').css('background-color', 'red');
  $('#yellowTeamList').css('background-color', 'yellow');
  $('#yellowTeamList').append('<li>Joseph Banks</li>');
  $('#yellowTeamList').append('<li>Simon Jones</li>');
  $('#oops h1').hide();
  $('#footerPlaceholder').remove();
  $('#footer').append('<p>Jenna Gordon: jenna.gordon@libertymutual.com</p>').css('font-family', 'Courier');
  $('#footer p').css('font-size', '24px');

});
