$(document).ready(function () {
  $('#dvd-table-body').empty();
  loadDvds();

  // create button on main page
  $('#create-dvd-button').click(function () {
      $('#header').hide();
      $('#hr-header').hide();
      $('#dvd-table').hide();
      $('#add-dvd-form').show();
  });

  // cancel button on create dvd page
  $('#add-cancel-button').click(function () {
      $('#header').show();
      $('#hr-header').show();
      $('#dvd-table').show();
      $('#add-dvd-form').hide();
  });

  $('#add-create-dvd-button').click(function () {

    createDvd();
      $('#header').show();
      $('#hr-header').show();
      $('#dvd-table').show();
      $('#add-dvd-form').hide();
  });

    $('#edit-cancel-button').click(function () {
        $('#header').show();
        $('#hr-header').show();
        $('#dvd-table').show();
        $('#edit-dvd-form').hide();
        loadDvds();
    });


    

});

function loadDvds() {
    $('#dvd-table-body').empty();

  var dvdRows = $('#dvd-table-body');
  $.ajax({
    type: 'GET',
    url: 'http://localhost:8080/dvds',
    success: function (dvdsArray) {
      $.each(dvdsArray, function(index, dvd) {
        var title = dvd.title;
        var year = dvd.releaseYear;
        var director = dvd.director;
        var rating = dvd.rating;
        var dvdId = dvd.dvdId;

        var row = '<tr>';
        row += '<td><a>' + title + '</a></td>';
        row += '<td>' + year + '</td>';
        row += '<td>' + director + '</td>';
        row += '<td>' + rating + '</td>';
        row += '<td>' + '<a onclick="getDvdForEdit('+ dvdId +')">Edit</a>' + ' | ' + '<a onclick="deleteDvd('+ dvdId +')">Delete</a>' + '</td>';

        dvdRows.append(row);


      });
    },
    error: function() {

    }
  })

}

function createDvd() {

  $.ajax({
      type: 'POST',
      url: 'http://localhost:8080/dvd',
      data: JSON.stringify({
         title: $('#add-dvd-title').val(),
         releaseYear:$('#add-release-year').val(),
          director: $('#add-director').val(),
          rating: $('#add-rating').val(),
          notes: $('#add-notes').val()
      }),
      headers: {
        'Accept': 'application/json',
          'Content-Type': 'application/json'
      },
      'dataType': 'json',

      success: function () {

          loadDvds();

          $('#add-dvd-title').val('');
          $('#add-release-year').val('');
          $('#add-director').val('');
          $('#add-rating').val('');
          $('#add-notes').val('');


      },
      error: function () {
          $('#errorMessages')
              .append($('<li>')
                  .attr({class: 'list-group-item list-group-item-danger'})
                  .text('Error calling web service. Please try again later.'));
      }
  })

}

function deleteDvd(dvdId) {

    if(window.confirm('Are you sure you want to delete this Dvd from your collection?'))
    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/dvd/' + dvdId,
        success: function () {
            loadDvds();
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({class: 'list-group-item list-group-item-danger'})
                    .text('Error calling web service. Please try again later.'));
        }

    })

}


function getDvdForEdit(dvdId) {

    $('#header').hide();
    $('#hr-header').hide();
    $('#dvd-table').hide();
    $('#edit-dvd-form').show();


        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/dvd/' + dvdId,
            success: function (data, status) {
                $('#edit-dvd-title').val(data.title);
                $('#edit-release-year').val(data.releaseYear);
                $('#edit-director').val(data.director);
                $('#edit-rating').val(data.rating);
                $('#edit-notes').val(data.notes);
                $('#edit-contact-id').val(data.dvdId);

            },
            error: function () {
                $('#errorMessages')
                    .append($('<li>')
                        .attr({class: 'list-group-item list-group-item-danger'})
                        .text('Error calling web service. Please try again later.'));
            }
        });




}


