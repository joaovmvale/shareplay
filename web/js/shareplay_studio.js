$(document).ready(function(){

    $(".studio-option").click(function(){

        $(".studio-page").css("display", "none");

    })

    $("#create-podcast").click(function(){

        $(".create-podcast").css("display", "flex");

    })

    $("#see-my-podcasts").click(function(){

        $(".my-podcasts").css("display", "flex");

    })


    //Load Podcasts

    loadUserPodcasts();
    
    function loadUserPodcasts(){

        var request = $.ajax({

            method: "POST",
            dataType: "json",
            url: "../../GetUserPodcastsController",
    
        })
    
        $.when(request).done(function(evt){

            for(let i = 0; i < evt.length; i++){
                let card = '';
                card += '<a href="../podcast_page/?=' + evt[i]["id"] + '" class="podcast-card">'
                card +=     '<img class="podcast-img" src="../../dynamic_src/podcasts/'+ evt[i]["id"] + '/banner.png">'
                card +=     '<p class="podcast-name">' + evt[i]["name"] + '</p>'
                card +=     '<div class="genre">' + evt[i]["category"] + '</div>'
                card += '</a>'
    
                $(".my-podcasts").append(card)
            }

        })

    }


    $(".confirm-profile").click(function(){

        $.ajax({

            method: "POST",
            dataType: "json",
            url: "../../CreatePodcastController",
            data: $(".create-podcast").serialize()

        })

        alert("Podcast criado com sucesso!");
        window.location.reload();

        return false;

    })

    $(".cancel-profile").click(function(){

        $(".studio-page").css("display", "none");
        $(".my-podcasts").css("display", "flex");

        return false;

    })


})