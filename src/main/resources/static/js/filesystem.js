'use strict'

function _raisePopup(aUrl) {
    $.ajax({
        type: "GET",
        url: aUrl,

        success: function(response) {
            $('#filePopup').append(response)
            for (let element of $(".blurrable")) {
                element.classList.toggle("active");
            }

            $('#filePopup').addClass("active");
        },
        error: function(error) {
            console.log(error);
        }
    })
}

function raiseCreateFilePopup() {
    _raisePopup("/system/popup-create-file");
}

// function raiseDeleteFilePopup(id) {
//     _raisePopup(`/system/popup-delete-file/${id}`);
// }

function cancelPopupAction() {
    _removePopup();
}

function _removePopup() {

    $('#filePopup').removeClass("active");
    $("#filePopup").children().remove();

    for (let element of $(".blurrable")) {
        element.classList.remove("active");
    }
}

function createFile() {

    if ($.trim($("#fileName").val()) === "") {
        $("#popupText").after(
            "<p>Filename cannot be empty</p>"
        );
        return;
    }

    let postUrl, content;
   
    switch( $('input[name="filetypeOptions"]:checked').val() ) {
        case 'file':
            postUrl = "/system/file";
            content = "Some content for now, add later";
            break;

        case 'directory':
            postUrl = "/system/directory";
            break;

        default:
            console.log("Incorrect input type");
            return;
    }

    $.ajax({
        type: "POST",
        url: postUrl,
        contentType: "application/json; charset=utf-8",
        // content is ignored for directory, TODO: Check if it's recommended to do it this way
        data: JSON.stringify({
            "name": $("#fileName").val(),
            "parent": {
                "id": $("div.current-directory").attr("directory-id")
            },
            "content": content
        }),

        success: function(response) {
            _removePopup();
            $('#dirContents').append(response);
        },
        error: function(error) {
            console.log(error);
            _removePopup();
        }
    });

}

function deleteFile(id) {

    $.ajax({
        type: "DELETE",
        url: `/system/${id}`,
        contentType: "application/json; charset=utf-8",

        success: function(response) {
            $(`#${id}`).remove();

        },
        error: function(error) {
            console.log(error);
        }
    })
}