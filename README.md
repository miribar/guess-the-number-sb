**guess-the-number-sb game flow:**

* Player types in name  --> Clicks 'Start a new game' --> Guesses area presented.

* Player types in first guess for 4-digits number --> Clicks 'Check' --> A new row is added to guesses table with result:
<br>- how many digits in right place 
<br>- how many digits in wrong place.

* If all 4 digits are guessed right --> message is presented "You are a new winner! Click the 'Reset gameboard' to play again."

* The DB table high_scores will only store the top 10 winners, so:
<br>- If high_scores is not full yet, every new winner will be added to the table.
<br>- If high_scores is full, only a winner with a lower/equal number of guesses will replace an existing winner in the table.

* Every winner will see the top 10 winners, stored in the DB.

* The player fails to guess the number after 20 attempts --> a message is presented "Game over! Click the 'Reset gameboard' to play again."

* In case of win/lose, when game is over --> 'Reset gameboard' button becomes available --> Clicking it will reset the game for a new player.

* A 'Clear' button will enable an admin (with the correct password) to reset (delete) the high_scores table content.
