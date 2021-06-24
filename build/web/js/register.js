$(document).ready(function (){

    $('#reg-button').click(function (){
        
        var sha256 = sjcl.hash.sha256.hash($('#password-register').val());
        var sha256_hexa = sjcl.codec.hex.fromBits(sha256);
        
        $('#password-hash-register').val(sha256_hexa);
        
        if (fValidateForm() == true){
            fServerComunnication();
        } else {
            window.location.reload(true);
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
    var campos = $("#form-register").serializeArray();
    
    for (let i = 0; i < campos.length; i++){
        if(campos[i].value == ""){
            alert("Por favor, preencha todos os campos!");
            return false;
        }
    }
    
    if(campos[1].value != campos[2].value){
        alert("As senhas devem ser iguais!");
        return false;
    }
    
    return true;
}