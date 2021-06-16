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

    $.when(request).done(function(evt){

        $(".podcast-name").text(evt[0]["name"])
        $(".podcast-subscribers").text(handleSubscribers(evt[0]["subscribers"]) + " inscritos")
        $(".profile-picture").attr("src", "../../dynamic_src/podcasts/" + evt[0]["id"] + "/profile.png")

        if(evt[2][0] != undefined){
            $(".subscribe-button").css("background-color", "green")
            $(".subscribe-button").text("Inscrito!")

        }
        
        for(let i = 0; i < evt[1].length; i++){
          
            let card = ''
            card += '<a href="../podcast/?='+ evt[1][i]["id"] + '" class="podcast-card">'
            card +=     '<img class="podcast-img" src="../../dynamic_src/recorded/'+ evt[1][i]["id"] + '/thumbnail.png">'
            card +=     '<p class="transmission-title">' + evt[1][i]["title"] + '</p>'
            card += '</a>'

            $(".podcasts").append(card);
            $(".podcast-card").eq(i).attr("podcast-id", evt[1][i]["id"]);

        }

        if(evt[3] == undefined){
            $(".podcast-card-large").remove();
        }
        else{
            $(".podcast-card-large").attr("href", "../podcast/?=" + evt[3]["id"])
            $("#transmission-image-large").attr("src", "../../dynamic_src/transmissions/" + evt[3]["id"] + "/thumbnail.png")
            $("#transmission-title-large").text(evt[1]["title"]);
            $("#transmission-viewers-large").text(handleViewers(evt[3]["viewers"]) + " assistindo");
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