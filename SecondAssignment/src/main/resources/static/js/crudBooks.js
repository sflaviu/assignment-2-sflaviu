function displayBooks(books) {
    var $tbody = $('tbody');
    $tbody.empty();
    for(var i in books) {
        var book = books[i];
        var $row = $('<tr>');
        $('<td>').html(book.name).appendTo($row);
        $('<td>').html(book.author.name).appendTo($row);
        $('<td>').html(book.isbn).appendTo($row);
        $('<td>').html(book.genre).appendTo($row);
        $('<td>').html(book.price).appendTo($row);
        $('<td>').html(book.quantity).appendTo($row);
        $row.appendTo($tbody);
    }
}

function displayResult(result)
{
    var list = "";
    var rez=JSON.parse(result);
    for (var i = 0; i < rez.length; i++) {
        list +="<li>"+rez[i]+"</li>";
    }

    $("#result").append(list);

}

function refreshBooks() {
    $.get('/books', {}, function(result) {
        displayBooks(result);
    });
}

function addBook(book) {
    $.ajax('/createBook', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        data: JSON.stringify(book),
        dataType: 'text',
        success: function(result) {
            refreshBooks();
            $('#name,#isbn, #genre,#price,#quantity').val('');
            displayResult(result);
        }
    });
}

function updateBook(book) {
    $.ajax('/updateBook', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        data: JSON.stringify(book),
        dataType: 'text',
        success: function(result) {
            refreshBooks();
            $('#name,#isbn, #genre,#price,#quantity').val('');
            displayResult(result);
        }
    });
}

function deleteBook(delName) {
    $.ajax('/deleteBook', {
        type: 'POST',
        data: delName,
        success: function() {
            refreshBooks();
            $('#delName').val('');
        }
    });
}



function populateByGoogleName(name) {
    $.get('/booksByGoogle', {name:name}, function(result) {
        displayBooks(result);
        $('#findByGoogle').val('');
    });
}

function generateReport(report) {
    $.ajax('/generateReport', {
        type: 'POST',
        data: report,
        success: function() {
        }
    });
}

function saveSuggestion(googleISBN)
{
    $.ajax('/saveSuggestion', {
        type: 'POST',
        data: googleISBN,
        success: function() {
        }
    });
}

$(function() {
    refreshBooks();
    $('button').click(function() {
        switch(this.id) {
            case "create":
                addBook({
                    'name': $('#name').val(),
                    'authorId': $('#author').val(),
                    'isbn': $('#isbn').val(),
                    'genre': $('#genre').val(),
                    'price': $('#price').val(),
                    'quantity': $('#quantity').val()
                });
                break;
            case "delete":
                    deleteBook($('#delName').val());
                    break;
            case "update":
               updateBook({
                   'name': $('#name').val(),
                   'authorId': $('#author').val(),
                   'isbn': $('#isbn').val(),
                   'genre': $('#genre').val(),
                   'price': $('#price').val(),
                   'quantity': $('#quantity').val()
               });
            case "findByGoogle":
                populateByGoogleName($('#googleName').val());
                break;
            case "generateReport":
                generateReport($('#report').val());
                break;
            case "saveByGoogle":
                saveSuggestion($('#googleISBN').val());
                break;
        }
        return false;
    });
});