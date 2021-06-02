
/**
 * A class modelling a tic-tac-toe (noughts and crosses, Xs and Os) game.
 * 
 * @author Alfred Akinkoye
 * @version November 8, 2012
 */

public class TicTacToe
{
   public static final String PLAYER_X = "X"; // player using "X"
   public static final String PLAYER_O = "O"; // player using "O"
   public static final String EMPTY = " ";  // empty cell
   public static final String TIE = "T"; // game ended in a tie
 
   public String player;   // current player (PLAYER_X or PLAYER_O)

   private String winner;   // winner: PLAYER_X, PLAYER_O, TIE, EMPTY = in progress

   public int numFreeSquares = 9; // number of squares still free
   public boolean soundOn = true;
   IntroGUI intro;
   /** 
    * Constructs a new Tic-Tac-Toe board.
    */
   public TicTacToe()
   {
       player = PLAYER_X;
   }

   /**
    * Sets everything up for a new game.  Marks all squares in the Tic Tac Toe board as empty,
    * and indicates no winner yet, 9 free squares and the current player is player X.
    */
   public void clearBoard()
   {
      player = PLAYER_X;     // Player X always has the first turn.
      numFreeSquares = 9;
   }


   /**
    * Plays one game of Tic Tac Toe.
    */

   public void playGame()
   {
        clearBoard(); // clear the board
        intro = new IntroGUI(); //run the game intro
   }
   
}
