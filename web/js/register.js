$(document).ready(function (){

    $('#reg-button').click(function (){
        
        var sha256 = sjcl.hash.sha256.hash($('#password-register').val());
        var sha256_hexa = sjcl.codec.hex.fromBits(sha256);
        
        $('#password-hash-register').val(sha256_hexa);
        
        if (fValidateForm() == true){
            fServerComunnication();
        }
        
        return false;
    });

});

function fServerComunnication() {
    var data = $('#form-register').serialize();

        var ajaxRequest = $.ajax({
           type: "POST",
           dataType: "json",
           data: data,
           url: "../../UserRegisterController"
        });
        
        $.when(ajaxRequest).done(function(evt){
            
            if(evt == "1"){
                alert("Usuario cadastrado com sucesso!");
                window.location.href = "../login";
            }
            else{
                alert("Usuario ja cadastrado");
                window.location.reload(true);
            }
        });
}

function fValidateForm() {
    var a = document.forms['form-login']['email-register'].value;
    var b = document.forms['form-login']['password-register'].value;
    var c = document.forms['form-login']['ps-confirmation-register'].value;
    var d = document.forms['form-login']['name-register'].value;
    var e = document.forms['form-login']['date-register'].value;
    
    if (a == "" || b == "" || c == "" || d == "" || e == ""){
        alert("Por favor, preencha todos os campos.");
        return false;
    }
    return true;
}