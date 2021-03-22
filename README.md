# MyMinesweeper

In this project I try to create the minesweeper game based on the version from Ubuntu. User has four modes of the game (let's call them easy, medium, hard and custom).
Custom mode enables user to create the game board which is compatible with the minesweeper rules. In every mode timer measure a duration of a game. Results are stored in sqlite
database, and there are presented by sorted list ordered by time. The timer begins measure time after first click on the game board. At any time during the game user can pause it
or change the level of difficulty. In case of loss user can play again thanks to the "Play again" button.
