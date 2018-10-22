# Chess-Game by Andrew J. Duffy
--------------------------
Motivation/Introduction
--------------------------
This project was made as a way of exploring different java libraries and common Data Structures, 
while also practicing the implementation of a BitBoard (a common representation for chess
boards). I broke the process of running a 'Chess Game' into three components: a Model, View, 
and Controller. The Model is the representation of the state of the board, in this case a collection
of pieces. The View takes in a Model and displays it. Normally, that is all the View would do. However, in 
this case I chose to implement Java Swing's ActionListener functionality so the View is also used to mutate
the Model, which would normally be the Controller's job. Here, the Controller simply constructs and passes the initial 
Board-state to the View.

--------------------------
Development Status
--------------------------
The development of this project ongoing.

--------------------------
Design Decisions
--------------------------
Model: 

View:

Controller:


--------------------------
Future Updates/Known issues
--------------------------
1. Add AI functionality          - Allow for 3rd party Chess engines to input moves
                                   and play against a user. This should be available 
                                   at multiple difficulty levels.
2. Add "Pick Sides" option       - Allow the user to pick which side is displayed at 
                                   the bottom of the screen.
3. Add a piece-type selector for pawn promotions - Display a list of promotion types to the user when
                                                   a pawn is promoted instead of defaulting to Queen.
4. Add a Checkmate screen        - Currently the game ends with a soft-mate, no moves
                                   can be made and no message is displayed. Add a
                                   "Game Over!" message when Checkmate occurs.
                                

