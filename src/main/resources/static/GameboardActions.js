const app = {           //this variable holds the events and has the base endpoint
    players : [],
    baseURL : 'rest/api'
};

//==== Action functions   ====//

function startNewGame() {
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            alert(this.responseText);
            //show(element('check-guess'));
            alert("Now we show the guesses area")
            //manageGuesses();
        }
    };
    xhttp.open("POST", app.baseURL + "/creategame", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

function manageGuesses() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            //show(element('check-guess'));
            alert("Now we show the guesses area")
            //manageGuesses();
        }
    };
    xhttp.open("GET", app.baseURL + "/fetchgame/" , true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify(game));
}

function createTable() {
    // if (app.players.length == 0) {
    //     element("high-scores-table").innerHTML = "<h1> No players found !! </h1>";
    //     return;
    // }
    alert ("in create table");
    var tbl_str = "<table>";
    tbl_str += "<tr>";
    tbl_str += "<th> # </th>";
    tbl_str += "<th> Name  </th>";
    tbl_str += "<th> Number of guesses </th>";
    tbl_str += "</tr>";
    for (var i = 0; i < app.players.length; i++) {
        const player = app.players[i];
        tbl_str += "<tr>";
        tbl_str += "<td>" + player.playerId + "</td>";
        tbl_str += "<td>" + player.playerName + "</td>";
        tbl_str += "<td>" + player.numOfGuesses + "</td>";
        tbl_str += "</tr>";
    }
    tbl_str += "</table>";
    element("high-scores-table").innerHTML = tbl_str;
    show(element('high-scores-table'));
}

//-----------------------------//

function showAll() {
    // hide(element('high-scores-table'));
    alert("in show all");
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            log("All players: " + this.responseText);
            app.players = JSON.parse(this.responseText);
            createTable();
        }
    };
    //This builds the GET request. We create AJAX requests with REST endpoint(communication protocol against the server)
    xhttp.open("GET", app.baseURL + "/getscores", true);
    xhttp.send();
}

//-----------------------------//

function winner() {
    var win;
    var game_over;

    if (win && game_over) {
        show(element('get-player-details'));
    }
}

//-----------------------------//

function addPlayer() {
    const player = {
        playerName : element("player-name").value,
        numOfGuesses : game.numOfGuesses
    };
    log(player);
    // if (event.description == null || event.description.length == 0
    //     || event.startDate == null || event.startDate.length == 0) {
    //     alert("Please fill all the fields");
    //     show(element('addEventBtn'));
    //     return;
    // }

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            showAll();
        }
    };
    xhttp.open("POST", app.baseURL + "/addplayer", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify(player));
}

//-----------------------------//

function deletePlayer (playerId) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            log("deleted player: " + this.responseText);
            showAll();
        }
    };
    xhttp.open("DELETE", app.baseURL + "/delete/" + playerId, true);
    xhttp.send();
}


//=====  Helper functions  =====//

function hide(element) {
    element.style.visibility = 'hidden';
}

function show(element) {
    alert(element);
    element.style.visibility = 'visible';
}

function element(elementId) {
    return document.getElementById(elementId);
}

function log(item) {
    console.log(item);
}