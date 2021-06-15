$(document).ready(function (){
    
    $('#login-button').click(function (){
        var sha256 = sjcl.hash.sha256.hash($('#password-login').val());
        var sha256_hexa = sjcl.codec.hex.fromBits(sha256);
        
        $('#password-hash-login').val(sha256_hexa);
        
        if (fValidateForm() == true){
            fServerComunnication();
        }
        return false;
    });

});

function fServerComunnication() {
    var data = $('#form-login').serialize();

        var ajaxRequest = $.ajax({
           type: "POST",
           dataType: "json",
           data: data,
           url: "../../UserLoginController"
        });
        
        $.when(ajaxRequest).done(function(evt){
            
            if(evt[0] == undefined){
                alert("Login ou senha inválidos");
                window.location.reload(true);
            }
            else{
                alert(evt[0]["id"]);
                alert(evt[0]["name"]);
                localStorage.setItem("user", JSON.stringify(evt[0]));
                alert("Seja bem-vindo!");
                window.location.href = "../home";
            }

        });
}

function fValidateForm() {
    var campos = $("#form-login").serializeArray();
    
    for (let i = 0; i < campos.length; i++){
        if(campos[i].value == ""){
            alert("Por favor, preencha todos os campos!")
            return false;
        }
    }
    return true;
}