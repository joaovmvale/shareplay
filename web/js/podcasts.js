$(document).ready(function(){

    
    const url = new URLSearchParams(window.location.search);
    const search = url.get('');

    var request = $.ajax({

        method: "POST",
        dataType: "json",
        url: "../../PodcastsLoadingController",
        data:{
            search: search
        }

    })

    $.when(request).done(function(evt){

        if(evt[0].length == 0 && evt[1].length == 0){
            $(".error-message").css("display", "flex");
        }

        for(let i = 0; i < evt[0].length; i++){
            
            let card = ''
            card += '<div transmissionID="' + evt[0][i]["id"] + '" class="transmission-card" podcastID="' + evt[0][i]["podcast_id"] + '">'
            card +=     '<div class="transmission-img">'
            card +=         '<p class="live">Ao vivo</p>'
            card +=         '<img class="podcast-img" src="../../dynamic_src/transmissions/'+ evt[0][i]["id"] + '/thumbnail.png">'
            card +=     '</div>'
            card +=     '<p class="transmission-title">' + evt[0][i]["title"] + '</p>'
            card +=     '<a href="../podcast_page/?=' + evt[1][i]["id"] + '" class="podcast-name">' + evt[0][i]["name"] + '</a>'
            card +=     '<p class="podcast-viewers">' + handleViewers(evt[0][i]["viewers"]) + ' Assistindo</p>'
            card += '</div>'

            $(".transmissions").append(card)
        }

        for(let i = 0; i < evt[1].length; i++){
            
            let card = '';
            card += '<div podcastID="' + evt[1][i]["id"] +'" class="podcast-card">'
            card +=     '<img class="podcast-img" src="../../dynamic_src/podcasts/'+ evt[1][i]["id"] + '/banner.png">'
            card +=     '<p class="podcast-name">' + evt[1][i]["name"] + '</p>'
            card +=     '<div class="genre">' + evt[1][i]["category"] + '</div>'
            card += '</div>'

            $(".podcasts").append(card)
        }

    })

    function handleViewers(viewers){
        if(viewers > 1000){
            return Math.floor(viewers / 1000) + "k";
        }
        return viewers;
    }

    $("body").on("click", ".genre", function(){

        window.location.href = "../podcasts/?=" + $(this).text()
        return false;

    })

    $("body").on("click", ".podcast-card", function(){
        
        window.location.href = "../podcast_page/?=" + $(this).attr("podcastID")

    })


    $("body").on("click", ".transmission-card", function(){
        
        window.location.href = "../podcast/?=" + $(this).attr("transmissionID")

    })



})