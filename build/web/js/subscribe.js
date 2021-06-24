$(document).ready(function(){

    $(".subscribe-button").click(function(){

        let request1 = $.ajax({

            method: "POST",
            dataType: "json",
            url: "../../CheckLoginStatusController"
    
        });

        $.when(request1).done(function(evt){
            if(evt == false)
                window.location.href = "../login/"
            else{

                let request2 = $.ajax({

                    method: "POST",
                    dataType: "json",
                    url: "../../SubscribeController",
                    data:{
                        podcastID: podcastID
                    }
        
                })
        
                $.when(request2).fail(function(){
                    window.location.reload();
                })

            }

        })


        return false;

    })


})