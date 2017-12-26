// Cette fonction permet de mettre sous forme HTML le contenu des child row
function format(rowData) {
    var div = $('<div/>')
            .addClass('loading')
            .text('Loading...');

    $.ajax({
        url: '/api/staff/details',
        data: {
            name: rowData.name
        },
        dataType: 'json',
        success: function (json) {
            div
                    .html(json.html)
                    .removeClass('loading');
        }
    });

    return div;
}

// Options de la datatable
$(document).ready(function () {

    var table = $('#listeLegere').DataTable({
        "aLengthMenu": [[25, 50, 100], [25, 50, 100]],
        "iDisplayLength": 25,
        "columns": [
            {
                "className": 'details-control',
                "orderable": false,
                "data": null,
                "defaultContent": ''
            },
            {"data": "nom"},
            {"data": "ville"},
            {"data": "equipe"}
        ],
        // Applique le langage français à la datatable
        "language":
                {
                    "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/French.json"
                },
        "order": [[1, 'asc']]
    });

    $('#listeLegere tbody').on('click', 'td.details-control', function () {
        var tr = $(this).parents('tr');
        var row = table.row(tr);

        if (row.child.isShown()) {
            // Cette partie est déjà ouverte -- la fermer
            row.child.hide();
            tr.removeClass('shown');
        } else {
            row.child(format(row.data())).show();
            tr.addClass('shown');
        }
    });
});