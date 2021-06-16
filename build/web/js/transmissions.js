$(document).ready(function(){

    $.when(request).done(function(evt){

        for(let i = 0; i < evt.length; i++){
            let card = ''
            card += '<div transmissionID="' + evt[i]["id"] + '" class="podcast-card" podcastID="' + evt[i]["podcast_id"] + '">'
            card +=     '<div class="podcast-img">'
            card +=         '<p class="live">Ao vivo</p>'
            card +=         '<img class="podcast-img" src="../../dynamic_src/transmissions/'+ evt[i]["id"] + '/thumbnail.png">'
            card +=     '</div>'
            card +=     '<p class="transmission-title">' + evt[i]["title"] + '</p>'
            card +=     '<p class="podcast-name">' + evt[i]["name"] + '</p>'
            card +=     '<p class="podcast-viewers">' + handleViewers(evt[i]["viewers"]) + ' Assistindo</p>'
            card += '</div>'

            $(".podcasts").append(card);

        }
        

    })

    $("body").on("click", ".podcast-card", function(){

        var podcastID = $(this).attr("transmissionID");
        window.location.href = "../podcast/?=" + podcastID;

    })

    $("body").on("click", ".podcast-name", function(){

        var podcastID = $(this).parents(".podcast-card").attr("podcastID");
        window.location.href = "../podcast_page/?=" + podcastID;

        return false;

    })

    function handleViewers(viewers){
        if(viewers > 1000){
            return Math.floor(viewers / 1000) + "k";
        }
        return viewers;
    }

})