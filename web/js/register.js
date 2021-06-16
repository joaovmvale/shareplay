$(document).ready(function (){

    $('#reg-button').click(function (){

        var data = $('#form-register').serialize();

        var ajaxRequest = $.ajax({
           type: "POST",
           dataType: "json",
           data: data,
           url: "../../UserRegisterController"
        });

        return false;
    });

});