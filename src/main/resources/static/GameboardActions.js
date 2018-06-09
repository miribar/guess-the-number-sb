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
            gameId = this.responseText;             //gameId variable receives the new gameId from the POST request
            app.players = JSON.stringify(player);
            show(element("guesses-area"));
        }
    };
    xhttp.open("POST", app.baseURL + "/creategame", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

//-----------------------------//

//Checks each guess on the server with the gameId, increases num of guesses in game hashmap
function checkTheGuess() {
    var xhttp = new XMLHttpRequest();
    var playerName = element("player-name").value;
    var guessInput = element("guess").value;

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            app.guesses = JSON.parse(this.responseText);
            log("Current guess: " + this.responseText);
            insertGuessRow();
            checkIfGameWon();
        }
    };
    // Only if input is valid, check the guess on server-side
    if (isNaN(guessInput) || (guessInput === "") || (guessInput === null) || (guessInput.length !== 4) ||
        (guessInput < 0)) {
        alert("Please enter a positive 4-digit number");
        return;
    }
    xhttp.open("GET", app.baseURL + "/check-the-guess/"
        + playerName + "/"
        + gameId + "/"
        + guessInput, true);
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
    if (numOfGuesses < 20) {
        if (app.guesses.numDigitsInPlace === 4) {
            doWinnerProcedure();
        }
    }
    // Win on the 20th guess attempt
    else  if (numOfGuesses === 20) {
        if (app.guesses.numDigitsInPlace === 4) {
            doWinnerProcedure();
        }
        // No win
        else {
            doLoserProcedure();
        }
    }
}

//-----------------------------//

function doWinnerProcedure() {
    alert ("You are a new winner! Click the 'Reset gameboard' to play again.");
    showHighScores();
    element("start-button").disabled = true;
    element("guess-button").disabled = true;
    element("reset-button").disabled = false;
}

//-----------------------------//

function doLoserProcedure() {
    alert("Game over! Click the 'Reset gameboard' to play again.");
    element("start-button").disabled = true;
    element("guess-button").disabled = true;
    element("reset-button").disabled = false;
}

//-----------------------------//

function resetGameboard() {
        element("player-name").value = "";
        // Delete the rows from guesses table
        var table = element("guesses-table");
        for(var i = table.rows.length - 1; i > 0; i--)
        {
            table.deleteRow(i);
        }
        element("guess").value = "";
        hide(element("guesses-area"));
        hide(element('high-scores-table'));
        hide(element('clear-scores-button'));
        element("start-button").disabled = false;
        element("guess-button").disabled = false;
        element("reset-button").disabled = true;

}

//-----------------------------//

function showHighScores() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            log("All players: " + this.responseText);
            app.players = JSON.parse(this.responseText);
            createHighScoresTable();
        }
    };
    // This builds the GET request.
    // We create AJAX requests with REST endpoint(communication protocol against the server)
    xhttp.open("GET", app.baseURL + "/getscores", true);
    xhttp.send();
}

//-----------------------------//

function createHighScoresTable() {
    var tbl_str = "<table>";
    tbl_str += "<tr>";
    tbl_str += "<th> # </th>";
    tbl_str += "<th> Name  </th>";
    tbl_str += "<th> Number of guesses </th>";
    tbl_str += "</tr>";
    var index = 0;
    for (var i = 0; i < app.players.length; i++) {
        const player = app.players[i];
        index++;
        tbl_str += "<tr>";
        tbl_str += "<td>" + index + "</td>";
        tbl_str += "<td>" + player.player_name + "</td>";
        tbl_str += "<td>" + player.numOfGuesses + "</td>";
        tbl_str += "</tr>";
    }
    tbl_str += "</table>";
    element("high-scores-table").innerHTML = "<h1> High Scores</h1><br>" + tbl_str;
    show(element('high-scores-table'));
    show(element('clear-scores-button'));
}

//-----------------------------//

function clearHighScoresTable() {
    var adminPass = element("admin-pass").value;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            alert(this.responseText);
        }
    };
    if (!adminPass == null || adminPass == "" || adminPass == " ") {
        alert("Please enter a valid Password!");
    } else {
        // This builds the DELETE request.
        xhttp.open("DELETE", app.baseURL + "/deletescores/" + adminPass, true);
        xhttp.send();
    }
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

// TODO: Use GeneralResponse to wrap everything with exceptions handling
// TODO: Implement admin-permissioned clear table option
