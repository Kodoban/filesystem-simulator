'use strict'

function _raisePopup(aUrl) {
    $.ajax({
        type: "GET",
        url: aUrl,

        success: function(response) {
            $('#filesystemPopup').append(response)
            for (let element of $(".blurrable")) {
                element.classList.toggle("active");
            }

            $('#filesystemPopup').addClass("active");
        },
        error: function(error) {
            console.log(error);
        }
    })
}

function raiseCreateFilesystemPopup() {
    _raisePopup("/dashboard/popup-create");
}

function raiseDeleteFilesystemPopup(uuid, name) {
    _raisePopup(`/dashboard/popup-delete/${uuid}`);

    // $("#popupText").text(`You are about to delete the system <b>${name}</b>. This action is not reversible. Proceed?`);
}

function createFilesystem() {

    if ($.trim($("#filesystemName").val()) === "") {
        $("#popupText").after(
            "<p>Filename cannot be empty</p>"
        );
        return;
    }

    $.ajax({
        type: "POST",
        url: "/dashboard",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            "owner": {
                "id": $("#ownerId").val()
            },
            "name": $("#filesystemName").val()
        }),

        success: function(response) {
            _removePopup();
            $('#filesystems').append(response);
        },
        error: function(error) {
            console.log(error);
            _removePopup();
        }
    })
}

function deleteFilesystem(uuid) {

    $.ajax({
        type: "DELETE",
        url: `/dashboard/${uuid}`,
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify({
            "uuid": uuid
        }),

        success: function(response) {
            _removePopup();
            $(`#${uuid}`).remove();

        },
        error: function(error) {
            console.log(error);
            _removePopup();
        }
    })
}

function cancelPopupAction() {
    _removePopup();
}

function _removePopup() {

    $('#filesystemPopup').removeClass("active");
    $("#filesystemPopup").children().remove();

    for (let element of $(".blurrable")) {
        element.classList.remove("active");
    }
}

function openFilesystemPage(uuid) {

}