function displayBooks(books) {
    var $tbody = $("tbody");
    console.log(books[0].name);
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
function findAll() {
    $.get('/books', {}, function(result) {
        displayBooks(result);
    });
}

function populateByName(name) {
    $.get('/booksByName', {name:name}, function(result) {
        displayBooks(result);
        $('#findByName').val('');
    });
}

function populateByGenre(genre) {
    $.get('/booksByGenre', {genre:genre}, function(result) {
        displayBooks(result);
        $('#findByAuthor').val('');
    });
}

function populateByAuthor(author) {
    $.get('/booksByAuthor', {author:author}, function(result) {
        displayBooks(result);
    });
}

function sellBook(transaction) {
    $.ajax('/sellBook', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        data: JSON.stringify(transaction),
        dataType: 'text',
        success: function() {
            findAll();
            $('#isbn,#quantity').val('');
        }
    });
}
$(function() {
    $('button').click(function() {
        switch(this.id)
        {
            case "findByName":
                populateByName($('#name').val());
                break;

            case "findByAuthor":
                populateByAuthor($('#author').val());
                break;

            case "findByGenre":
                populateByGenre($('#genre').val());
                break;

            case "findAllBooks":
                findAll();
                break;

            case "sellBook":
                sellBook({
                    'isbn': $('#isbn').val(),
                    'quantity': $('#quantity').val()
                });
                break;


        }
        return false;
    });
});