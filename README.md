# TicTacToe

Application implements tic-tac-toe algorithm for 3x3 board that should end in at least in a draw.

Tech requirement:
The purpose of this coding assignment is to create a "production" code for the RESTful API service that can play a game of Tic-Tac-Toe on the 3x3 field. User submit new state of the board or a move and service makes its own move and responds with a new state. Being RESTful service it must not contain any state, which means that state of the board must be transmitted in the request and response must include new state. Request and response bodies must be in the json or xml (or both) formats. The task is to design and document API, implement service that supports it in the statically typed language of your choice (JVM based languages preferred). This includes proper building mechanism (maven, ant, etc.), unit tests and if possible code for building docker images for this service. Result should be placed in the github repository that you must create and contain instructions for building and running this micro service. 


Input/output format:
 
  "state" integer; current game state:
           values:  0 - starting
                    1 - in progress
                    2 we won (user lost)
                    3 we lost (user won)
                    4 draw
  "bCross" boolean; what symbol we play in game
           values: true - we play cross ("x"), user plays zeroes ("0")
                   false - user plays cross ("x"), we play zeroes ("0")
  "board" String; game board data
           values: string (size of board)
                   where 'x' stays for each cross move,
                   where '0' stays for each zero move,
                   where ' ' stays for empty cell
           for instance for 3x3 board
           | 0 | 0 | x|
           |   | x |  |
           | x |   |  |
 
  will be represented by string "00x x x  "
 

If the empty string is passed as an input, the game will be started with empty board, 'cross' symbol will be used.
The application throws TttException for the input parameters errors.

Known problems:
1. More test units to be implemented.
2. Game does not check if the amount of '0' and 'x' in the input is logically possible.
3. Algorithm has a flaw when the board is 2/3 full, but will still always end in at least a draw.
