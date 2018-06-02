const app = {           //this variable holds the players array and has the base endpoint
    players : [],
    guesses : [],
    baseURL : 'rest/api'
};

var gameId;

//==== Action functions   ====//

//Starts a new game and captures its gameId, shows guesses table
function startNewGame() {
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            gameId = this.responseText;             //gameId variable receives the new gameId from the POST request
            addPlayer();
            show(element("guesses-area"));
        }
    };
    xhttp.open("POST", app.baseURL + "/creategame", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

//-----------------------------//

function addPlayer() {
    const player = {
        playerName : element("player-name").value,
    };
    log(player);
    if (player.playerName == null || player.playerName.length == 0) {
        alert("Please fill in player's name");
        return;
    }

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            app.players = JSON.parse(this.responseText);
            log("All players: " + this.responseText);
        }
    };
    xhttp.open("POST", app.baseURL + "/addplayer", true);
    xhttp.send();
}

//-----------------------------//

//Checks each guess on the server with the gameId, increases num of guesses
function checkTheGuess() {
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
           app.guesses = JSON.parse(this.responseText);
           log("All guesses: " + this.responseText);

        }
    };
    xhttp.open("GET", app.baseURL + "/check-the-guess/" + gameId + "/" + element("guess").value, true);
    xhttp.setRequestHeader("Content-type", "application/json");
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

function createHighScoresTable() {
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
        tbl_str += "<td>" + player.playerGuesses + "</td>";
        tbl_str += "</tr>";
    }
    tbl_str += "</table>";
    element("high-scores-table").innerHTML = tbl_str;
    show(element('high-scores-table'));
}

//-----------------------------//

function showAll() {
    // hide(element('high-scores-table'));
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            createHighScoresTable();
        }
    };
    //This builds the GET request. We create AJAX requests with REST endpoint(communication protocol against the server)
    xhttp.open("GET", app.baseURL + "/getscores", true);
    xhttp.send();
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
    element.style.visibility = 'visible';
}

function element(elementId) {
    return document.getElementById(elementId);
}

function log(item) {
    console.log(item);
}

// TODO: Write win & lose scenarios
// TODO: Use GeneralResponse to wrap everything with exceptions handling