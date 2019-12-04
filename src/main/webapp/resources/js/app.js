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
    
    $("#listallbooks").on("click", function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/listallbooks',
            contentType: 'application/json',
            success: function (data) {
                $("#usermessagediv").html(data);
            }
        });
    });
    
    $("#listavailablebooks").on("click", function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/listavailablebooks',
            contentType: 'application/json',
            success: function (data) {
                $("#usermessagediv").html(data);
            }
        });
    });
    
    $("#listallstudents").on("click", function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/listallstudents',
            contentType: 'application/json',
            success: function (data) {
                $("#usermessagediv").html(data);
            }
        });
    });
    
    $("#usermessagediv").on("click", ".liststudentbooksbutton", function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/student/'+$(this).attr("studentid"),
            contentType: 'application/json',
            success: function (data) {
              $("#usermessagediv").html(data);
            }
        });
    });
    
    $("#usermessagediv").on("click", ".rentbooktostudentbutton", function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/rentbook/'+$(this).attr("booktorentid"),
            contentType: 'application/json',
            success: function (data) {
              $("#usermessagediv").html(data);
            }
        });
    });
    
    $("#usermessagediv").on("click", ".rentbooktostudentconfirm", function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/assignbooktostudent/'+$(this).attr("bookId")+'/'+$(this).attr("userId"),
            contentType: 'application/json',
            success: function (data) {
              $("#usermessagediv").html(data);
            }
        });
    });
    
    $("#usermessagediv").on("click", ".returnbook", function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/returnbook/'+$(this).attr("bookId")+'/'+$(this).attr("userId"),
            contentType: 'application/json',
            success: function (data) {
              $("#usermessagediv").html(data);
            }
        });
    });
});