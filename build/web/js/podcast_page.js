$(document).ready(function(){

    const url = new URLSearchParams(window.location.search);
    podcastID = url.get('');

    var request = $.ajax({

        method: "POST",
        dataType: "json",
        url: "../../GetPodcastPageController",
        data: {
            podcastID: podcastID
        }

    })

    $.when(request).done(function(evt1){
        
        let request1 = $.ajax({

            method: "POST",
            dataType: "json",
            url: "../../CheckLoginStatusController"
    
        });

        $.when(request1).done(function(evt2){

            if(evt2 == true && evt1[2][0] != undefined){
                $(".subscribe-button").css("background-color", "green")
                $(".subscribe-button").text("Inscrito!")
            }

        })

        $(".podcast-name").text(evt1[0]["name"])
        $(".podcast-subscribers").text(handleSubscribers(evt1[0]["subscribers"]) + " inscritos")
        $(".profile-picture").attr("src", "../../dynamic_src/podcasts/" + evt1[0]["id"] + "/profile.png")

    
        for(let i = 0; i < evt1[1].length; i++){
          
            let card = ''
            card += '<a href="../podcast/?='+ evt1[1][i]["id"] + '" class="podcast-card">'
            card +=     '<img class="podcast-img" src="../../dynamic_src/recorded/'+ evt1[1][i]["id"] + '/thumbnail.png">'
            card +=     '<p class="transmission-title">' + evt1[1][i]["title"] + '</p>'
            card += '</a>'

            $(".podcasts").append(card);
            $(".podcast-card").eq(i).attr("podcast-id", evt1[1][i]["id"]);

        }

        if(evt1[3] == undefined){
            $(".podcast-card-large").remove();
        }
        else{
            $(".podcast-card-large").attr("href", "../podcast/?=" + evt1[3]["id"])
            $("#transmission-image-large").attr("src", "../../dynamic_src/transmissions/" + evt1[3]["id"] + "/thumbnail.png")
            $("#transmission-title-large").text(evt1[1]["title"]);
            $("#transmission-viewers-large").text(handleViewers(evt1[3]["viewers"]) + " assistindo");
        }


    })

    function handleViewers(viewers){
        if(viewers > 1000){
            return Math.floor(viewers / 1000) + "k";
        }
        return viewers;
    }

    function handleSubscribers(subscribers){

        if(subscribers > 1000000){
            return (subscribers / 1000000).toFixed(2) + " mi de";
        }
        if(subscribers > 100000){
            return Math.floor(subscribers / 1000) + "k de";
        }
        if(subscribers > 1000){
            return (subscribers / 1000).toFixed(2) + "k de";
        }

        return subscribers;

    }

})