$(document).ready(function(){

    $(".subscribe-button").click(function(){

        var request1 = $.ajax({

            method: "POST",
            dataType: "json",
            url: "../../SubscribeController",
            data:{
                podcastID: podcastID
            }

        })

        $.when(request1).fail(function(){
            window.location.reload();
        })
     
        return false;

    })


})