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
This is not a finished build, see Future Updates/Known Issues.

--------------------------
Design Decisions
--------------------------
Model: The Model is used to keep track of the state of the board. An instance of this Model consists of a
        set of Pieces (and some helpful values). The main function of this class is the movePiece method, 
        which mutates the values stored in one of the Pieces and in turn 'moves' the piece. There are 12
        Pieces stored in this Model. Each represents not a single piece, but every piece of that type and
        color on the board. This is done by using Java's BitSet class. Each piece gets a 64-bit BitSet, 
        each bit representing a slot on the Chess board. A zero in a BitSet (or false) represents an empty
        slot. A one (or true) means there is a piece of that type on the board. The Piece class is abstracted
        into the different types of Chess pieces so that the canMove method correctly outputs whether the
        move is valid or not (except for a few special moves, which are handled in the movePiece method).
        

View: The View is used to display the state of the board. An instance of this View has two parts, a Frame and 
      a Panel. The Frame acts as a container for the Panel and handles all
      user input (mouse clicks). When the user clicks somewhere in the frame, the values are passed into the
      Panel's repaint method. The Panel then uses those values to draw the active Pieces in the game
      on a checkered board. When a Piece is selected, the slot that it resides in turns Orange. If a King
      is checked, the slot that it resides in turns Red. Otherwise, the slots are White and Black. The Piece 
      PNG's are from lichess.org.

Controller: Initializes a Model with default Piece locations, then constructs a View with that Model.
            Calling the play() method from the Controller class will call draw() on the View and begin the game.
            


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
                                

