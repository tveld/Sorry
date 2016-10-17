package ludo.model;

import java.util.Random;

public class LudoModel {
  /**
   * The size of the board array in the model.
   */
  private static final int BOARD_SIZE = 88;

  private int currentPlayer;

  static final int[] START_POSITIONS = { 72, 76, 80, 84 };
  static final int[] PIECE_ENTER_POSITIONS = { 0, 13, 26, 39 };
  static final int[] SAFE_ADJACENT_POSITIONS = { 50, 11, 24, 37 };
  static final int[] SAFE_POSITION_0 = { 52, 57, 62, 67 };

  Random rand;

  BoardSquare[] board;
  Player[] playerList;

  boolean gameWon;

  /**
   * Default constructor for LudoModel. Initializes random number generator,
   * sets up the board, creates the players, and sets the current player to RED.
   */
  public LudoModel() {
    rand = new Random();

    gameWon = false;

    playerList = new Player[4];
    playerList[Player.RED] = new Player(Player.RED);
    playerList[Player.BLUE] = new Player(Player.BLUE);
    playerList[Player.GREEN] = new Player(Player.GREEN);
    playerList[Player.YELLOW] = new Player(Player.YELLOW);

    setUpBoard();

    currentPlayer = Player.RED;
  }

  /**
   * This method creates the board and sets the starting positions of the game
   * pieces.
   */
  public void setUpBoard() {
    board = new BoardSquare[BOARD_SIZE];

    // Create the regular board squares
    for (int i = 0; i < SAFE_POSITION_0[Player.RED]; i++) {
      board[i] = new BoardSquare(BoardSquare.REGULAR, Player.ALL, i);
    }

    // Create the red safe squares
    for (int i = SAFE_POSITION_0[Player.RED]; i < SAFE_POSITION_0[Player.BLUE]; i++) {
      board[i] = new BoardSquare(BoardSquare.SAFE, Player.RED, i);
    }

    // Create the blue safe squares
    for (int i = SAFE_POSITION_0[Player.BLUE]; i < SAFE_POSITION_0[Player.GREEN]; i++) {
      board[i] = new BoardSquare(BoardSquare.SAFE, Player.BLUE, i);
    }

    // Create the green safe squares
    for (int i = SAFE_POSITION_0[Player.GREEN]; i < SAFE_POSITION_0[Player.YELLOW]; i++) {
      board[i] = new BoardSquare(BoardSquare.SAFE, Player.GREEN, i);
    }

    // Create the yellow safe squares
    for (int i = SAFE_POSITION_0[Player.YELLOW]; i < START_POSITIONS[Player.RED]; i++) {
      board[i] = new BoardSquare(BoardSquare.SAFE, Player.YELLOW, i);
    }

    // Create the red start squares
    int counter = 0;
    for (int i = START_POSITIONS[Player.RED]; i < START_POSITIONS[Player.BLUE]; i++) {
      board[i] = new BoardSquare(BoardSquare.START, Player.RED, i);
      board[i].setGamePiece(playerList[Player.RED].getGamePiece(counter));
      counter++;
    }
    // Create the blue start squares
    counter = 0;
    for (int i = START_POSITIONS[Player.BLUE]; i < START_POSITIONS[Player.GREEN]; i++) {
      board[i] = new BoardSquare(BoardSquare.START, Player.BLUE, i);
      board[i].setGamePiece(playerList[Player.BLUE].getGamePiece(counter));
      counter++;
    }
    // Create the green start squares
    counter = 0;
    for (int i = START_POSITIONS[Player.GREEN]; i < START_POSITIONS[Player.YELLOW]; i++) {
      board[i] = new BoardSquare(BoardSquare.START, Player.GREEN, i);
      board[i].setGamePiece(playerList[Player.GREEN].getGamePiece(counter));
      counter++;
    }
    // Create the yellow start squares
    counter = 0;
    for (int i = START_POSITIONS[Player.YELLOW]; i < BOARD_SIZE; i++) {
      board[i] = new BoardSquare(BoardSquare.START, Player.YELLOW, i);
      board[i].setGamePiece(playerList[Player.YELLOW].getGamePiece(counter));
      counter++;
    }

    for (int i = 0; i < 4; i++) {
      board[PIECE_ENTER_POSITIONS[i]].setSquareType(BoardSquare.START_POSITION);
      board[SAFE_ADJACENT_POSITIONS[i]].setSquareType(BoardSquare.SAFE_ADJACENT);
    }
  }

  /**
   * This method simulates rolling a dice.
   * 
   * @return The random integer representing the dice roll
   */
  public int rollDice() {
    return rand.nextInt(6) + 1;
  }

  /**
   * This method advances the player counter and returns current player.
   * 
   * @return The new current player
   */
  public int nextPlayerTurn() {
    currentPlayer = (currentPlayer + 1) % 4;
    return currentPlayer;
  }

  /**
   * This method moves a  piece from oldPos to newPos if it is a valid move.
   * 
   * @param oldPos The current position of the piece
   * @param newPos The desired position of the piece
   * @param diceRoll The number of squares the move is supposed to be
   * @return True if the move was successful, false if it failed
   */
  public boolean move(int oldPos, int newPos, int diceRoll) {
    if (validMove(oldPos, newPos, diceRoll)) {
      GamePiece movePiece = board[oldPos].getGamePiece();
      board[oldPos].setGamePiece(null);
      board[newPos].setGamePiece(movePiece);
     
      return true;
    } else {
      return false;
    }
  }

  /**
   * Check if all Ludo rules are satisfied before moving.
   * 
   * @param int
   *          oldPos, the old position we are attempting to move from
   * @param int
   *          newPos, the new position we are attempting to move to
   * @param int
   *          diceRoll, the number of positions to move forward
   * @return boolean, true if can't move, false if can move
   */
  private boolean validMove(int oldPos, int newPos, int diceRoll) {

    if (isOccupied(oldPos, newPos)) {
      return false;

    } else if (cantMove(oldPos, newPos, diceRoll)) {
      return false;

    }
    return true;
  }

  /**
   * Check if the new position is occupied.
   * 
   * @param int
   *          oldPos, the old position we are attempting to move from
   * @param int
   *          newPos, the new position we are attempting to move to
   * @return boolean, true if can't move, false if can move
   */
  private boolean isOccupied(int oldPos, int newPos) {
    GamePiece atNew = board[newPos].getGamePiece();

    // check if a piece is in newPos.
    if (atNew != null) {

      int playerAtNew = atNew.getPlayer();

      // current player already there. Illegal move.
      if (playerAtNew == currentPlayer) {
        return true;

        // need move piece to start. Still legal move.
      } else {
        // move current piece to start
        GamePiece atOld = board[oldPos].getGamePiece();
        atOld.setPosition(START_POSITIONS[atOld.getPlayer()] + atOld.getPieceNumber());
        return false;
      }
    }
    return false;
  }

  /**
   * Check if a player can't move piece.
   * 
   * @param int
   *          oldPos, the old position we are attempting to move from
   * @param int
   *          newPos, the new position we are attempting to move to
   * @param int
   *          diceRoll, the number of positions to move forward
   * @return boolean, true if can't move, false if can move
   */
  private boolean cantMove(int oldPos, int newPos, int diceRoll) {
    // check if in start position
    if (oldPos == PIECE_ENTER_POSITIONS[currentPlayer]) {

      // need a roll greater than 6 to leave start
      if (diceRoll < 6) {
        return true;

        // can't move backwards
      } else if (newPos < oldPos) {
        return true;
      }
    }
    // make sure piece is moved correct number of spaces
    if (oldPos + diceRoll != newPos) {
    	return true;
    }
    // piece cannot move passed home position
    if (newPos >= SAFE_POSITION_0[currentPlayer+1] || newPos >= START_POSITIONS[0]) {
    	return true;
    }
    // check if piece moved passed safe spots
    if (oldPos <= SAFE_ADJACENT_POSITIONS[currentPlayer] && newPos > SAFE_ADJACENT_POSITIONS[currentPlayer] % 50) {
    	return true;
    }
    // check if player win
    
    return false;
  }

  /**
   * A getter for a player, player number must be between 0 and 3.
   * 
   * @param playerNumber
   *          The number of the player to return
   * @return The player
   */
  public Player getPlayer(int playerNumber) {
    if (playerNumber >= 0 && playerNumber <= 3) {
      return playerList[playerNumber];
    } else {
      return null;
    }
  }

  /**
   * Getter for the current player.
   * 
   * @return The current player
   */
  public int getCurrentPlayer() {
    return currentPlayer;
  }

  /**
   * Getter for the game status.
   * 
   * @return The gameWon variable
   */
  public boolean getGameWon() {
    return gameWon;
  }
}
