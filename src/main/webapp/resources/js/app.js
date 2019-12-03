document.addEventListener("DOMContentLoaded", function () {    
    //Run endpoint without redirect, call java method and do nothing in case of success
    $("#studenttouser").on("click", function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/studenttouser',
            contentType: 'application/json',
            success: function (data) {
                $("#messagediv").html("Student should be user now");
            }
        });
    });
    
    $("#studenttoadmin").on("click", function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/studenttoadmin',
            contentType: 'application/json',
            success: function (data) {
                $("#messagediv").html("Student should be administrator now");
            }
        });
    });
});