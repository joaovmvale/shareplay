$(document).ready(function (){

    $('#login-button').click(function (){

        var data = $('#form-login').serialize();

        var ajaxRequest = $.ajax({
           type: "POST",
           dataType: "json",
           data: data,
           url: "../../UserLoginController"
        });
        
        $.when(ajaxRequest).done(function(evt){
            
            if(evt[0] == undefined){
                alert("Login ou senha inválidos")
            }
            else{
                alert(evt[0]["id"])
            }

        })

        return false;
    });

});
