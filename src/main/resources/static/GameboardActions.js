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
        }
    };
    xhttp.open("POST", app.baseURL + "/creategame", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

//-----------------------------//

function addPlayer() {
    var xhttp = new XMLHttpRequest();
    const player = {
        player_name : element("player-name").value,
        guesses : 0
    };

    if (player.player_name == null || player.player_name.length == 0) {
        alert("Please fill in player's name");
        return;
    }

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            app.players = JSON.parse(this.responseText);
            show(element("guesses-area"));
        }
    };
    xhttp.open("POST", app.baseURL + "/addplayer", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify(player));
}

//-----------------------------//

//Checks each guess on the server with the gameId, increases num of guesses in game hashmap
function checkTheGuess() {
    var xhttp = new XMLHttpRequest();
    var input = element("guess").value;

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            app.guesses = JSON.parse(this.responseText);
            log("Current guess: " + this.responseText);
            insertGuessRow();
            checkIfGameWon();
        }
    };
    // Only if input is valid, check the guess on server-side
    if (isNaN(input) || (input === "") || (input === null) || (input.length !== 4) || (input < 0)) {
        alert("Please enter a positive 4-digit number");
        return;
    }
    xhttp.open("GET", app.baseURL + "/check-the-guess/" + gameId + "/" + input, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

//-----------------------------//

function insertGuessRow() {
    var table = element("guesses-table");
    var row = table.insertRow();
    var cell1 = row.insertCell();
    var cell2 = row.insertCell();
    var cell3 = row.insertCell();
    cell1.innerHTML = row.rowIndex;
    cell2.innerHTML = element("guess").value;
    cell3.innerHTML = "Digits in place: <b style='color: green'>" + app.guesses.numDigitsInPlace + "</b>" +
        "<br>Digits not in place: <b style='color: red'>" + app.guesses.numDigitsNotInPlace + "</b>";
}

//-----------------------------//

function checkIfGameWon() {
    var numOfGuesses = element("guesses-table").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length - 1;

    // Game not over & won
    if (numOfGuesses < 2) {
        if (app.guesses.numDigitsInPlace === 4) {
            alert ("Game won, now we save player to DB & show high-scores-table");
        }
    }
    // Win on the 20th guess
    else {
        if (app.guesses.numDigitsInPlace === 4) {
            alert ("Game won, now we save player to DB & show high-scores-table");
        }
        // No win
        else
            alert ("Game over! Try again?");
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
        tbl_str += "<td>" + player.player_name + "</td>";
        tbl_str += "<td>" + player.guesses + "</td>";
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
