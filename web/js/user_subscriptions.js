$(document).ready(function (){

    var request = $.ajax({

        method: "POST",
        dataType: "json",
        url: "../../GetSubscriptionsController"

    })

    $.when(request).done(function(evt){

        for(let i = 0; i < evt.length; i++){
            let card = '';
            card += '<a href="../podcast_page/?=' + evt[i]["id"] + '" class="podcast-card">'
            card +=     '<img class="podcast-img" src="../../dynamic_src/podcasts/'+ evt[i]["id"] + '/banner.png">'
            card +=     '<p class="podcast-name">' + evt[i]["name"] + '</p>'
            card +=     '<div class="genre">' + evt[i]["category"] + '</div>'
            card += '</a>'

            $(".podcasts").append(card)
        }

    })


})