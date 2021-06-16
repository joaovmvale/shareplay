$(document).ready(function(){

    const url = new URLSearchParams(window.location.search);
    const transmissionID = url.get('');

    var request = $.ajax({

        method: "POST",
        dataType: "json",
        url: "../../GetTransmissionController",
        data: {
            transmissionID: transmissionID
        }

    })

    $.when(request).done(function(evt){

        $(".transmission-title").text(evt[0]["title"]);
        $(".transmission-viewers").text(handleViewers(evt[0]["viewers"]) + " assistindo");
        $(".likes").text( handleViewers(evt[0]["likes"]))
        $(".deslikes").text( handleViewers(evt[0]["deslikes"]))
        $(".podcast-channel-image").attr("src", '../../dynamic_src/podcasts/'+ evt[1]["id"] + '/profile.png');
        $(".podcast-channel-name").text(evt[1]["name"]);
        $(".podcast-channel-name").attr("href", "../podcast_page/?=" + evt[1]["id"]);
        $(".podcast-channel-subscribers").text(handleSubscribers(evt[1]["subscribers"]) + " inscritos");
        $(".transmission-description").text(evt[0]["description"])

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