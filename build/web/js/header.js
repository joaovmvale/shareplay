$(document).ready(function(){

    var dropdownStatus = false;

    if(localStorage["user"] != undefined){
        let userData = JSON.parse(localStorage.getItem("user"));
        $("#dropdown-user-picture").attr("src", "../../dynamic_src/user_info/" + userData["id"] + "/user_picture.png");
        $("#dropdown-username").text(userData["name"]);
    }

    $(".user").click(function(){

        if(localStorage["user"] == undefined){
            window.location.href = "../login/";
        }
        else{
            if(dropdownStatus == false){
     
                $(".dropdown-menu").css("display", "block");
                dropdownStatus = true;
            }
            else{
                $(".dropdown-menu").css("display", "none");
                dropdownStatus = false;
            }
        }
    })

    $("#leave").click(function(){

        localStorage.removeItem("user");

        $.ajax({

            method: "POST",
            url: "../../UserDisconnectController"
    
        });
<<<<<<< HEAD

=======
        
>>>>>>> login
        window.location.reload(true);

    });

    $("#search-input").on("input", function(){

        if($("#search-input").val() == ""){
            $("#search-button").css("cursor", "not-allowed");
        }
        else{
            $("#search-button").css("cursor", "pointer");
        }

    })

    $("#search-input").keypress(function(event){
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if(keycode == '13'){
            if($("#search-input").val() != ""){
                window.location.href = "../podcasts/?="+ $("#search-input").val();
            }
        }
    });

    $("#search-button").click(function(){

        if($("#search-input").val() != ""){
            window.location.href = "../podcasts/?="+ $("#search-input").val();

        }
       
    })


    
});