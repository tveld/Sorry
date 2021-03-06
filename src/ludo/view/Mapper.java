package ludo.view;

import ludo.model.GamePiece;

/**
 * A class that handles the conversion between the logical model and the display
 * model.
 * 
 * @author Katie Mulder
 *
 */
public class Mapper {
  /**
   * A constant representing the board array size.
   */
  private static final int SINGLE_ARRAY_SIZE = 88;
  /**
   * A constant representing the size of a board size.
   */
  private static final int BOARD_SIZE = 15;
  /**
   * A constant representing index 0.
   */
  private static final int INDEX_0 = 0;
  /**
   * A constant representing index 1.
   */
  private static final int INDEX_1 = 1;
  /**
   * A constant representing index 4.
   */
  private static final int INDEX_4 = 4;
  /**
   * A constant representing index 5.
   */
  private static final int INDEX_5 = 5;
  /**
   * A constant representing index 6.
   */
  private static final int INDEX_6 = 6;
  /**
   * A constant representing index 7.
   */
  private static final int INDEX_7 = 7;
  /**
   * A constant representing index 8.
   */
  private static final int INDEX_8 = 8;
  /**
   * A constant representing index 9.
   */
  private static final int INDEX_9 = 9;
  /**
   * A constant representing index 10.
   */
  private static final int INDEX_10 = 10;
  /**
   * A constant representing index 13.
   */
  private static final int INDEX_13 = 13;
  /**
   * A constant representing index 14.
   */
  private static final int INDEX_14 = 14;
  /**
   * An array that maps a position in the 2D array into the LudoModel position.
   */
  private Position[] positionMappings;
  /**
   * An array that maps a position in the LudoModel array into the 2D board.
   */
  private int[][] indexMappings;

  /**
   * A constructor for the mapper.
   */
  public Mapper() {
    positionMappings = new Position[SINGLE_ARRAY_SIZE];
    indexMappings = new int[BOARD_SIZE][BOARD_SIZE];
    setPositionMappings();
    setIndexMappings();
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        System.out.printf(" %2d", indexMappings[i][j]);
      }
      System.out.println();
    }
    for (int i = 0; i < SINGLE_ARRAY_SIZE; i++) {
      System.out.println(i + ": " + positionMappings[i].getPositionX() + " "
          + positionMappings[i].getPositionY());
    }
  }

  /**
   * A method to set up the position mappings.
   */
  private void setIndexMappings() {
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        indexMappings[i][j] = -1;
      }
    }

    for (int i = 0; i < SINGLE_ARRAY_SIZE; i++) {
      indexMappings[positionMappings[i].getPositionY()][positionMappings[i]
          .getPositionX()] = i;
    }

    for (int i = INDEX_6; i < INDEX_9; i++) {
      for (int j = INDEX_6; j < INDEX_9; j++) {
        indexMappings[i][j] = GamePiece.IN_HOME;
      }
    }
  }

  /**
   * A method to set up the index and position mappings.
   */
  private void setPositionMappings() {
    int currPosition = 0;
    // Set positions 0-5
    for (int i = 1; i <= INDEX_5; i++) {
      positionMappings[currPosition] = new Position(i, INDEX_6);
      currPosition++;
    }
    // Set positions 5-10
    for (int i = INDEX_5; i >= 0; i--) {
      positionMappings[currPosition] = new Position(INDEX_6, i);
      currPosition++;
    }
    // Set position 11
    positionMappings[currPosition] = new Position(INDEX_7, INDEX_0);
    currPosition++;
    // Set positions 12-17
    for (int i = 0; i <= INDEX_5; i++) {
      positionMappings[currPosition] = new Position(INDEX_8, i);
      currPosition++;
    }
    // Set positions 18-23
    for (int i = INDEX_9; i <= INDEX_14; i++) {
      positionMappings[currPosition] = new Position(i, INDEX_6);
      currPosition++;
    }
    // Set position 24
    positionMappings[currPosition] = new Position(INDEX_14, INDEX_7);
    currPosition++;
    // Set positions 25 - 30
    for (int i = INDEX_14; i >= INDEX_9; i--) {
      positionMappings[currPosition] = new Position(i, INDEX_8);
      currPosition++;
    }
    // Set positions 31-36
    for (int i = INDEX_9; i <= INDEX_14; i++) {
      positionMappings[currPosition] = new Position(INDEX_8, i);
      currPosition++;
    }
    // Set position 37
    positionMappings[currPosition] = new Position(INDEX_7, INDEX_14);
    currPosition++;
    // Set positions 38-43
    for (int i = INDEX_14; i >= INDEX_9; i--) {
      positionMappings[currPosition] = new Position(INDEX_6, i);
      currPosition++;
    }
    // Set positions 44-49
    for (int i = INDEX_5; i >= INDEX_0; i--) {
      positionMappings[currPosition] = new Position(i, INDEX_8);
      currPosition++;
    }
    // Set position 50
    positionMappings[currPosition] = new Position(INDEX_0, INDEX_7);
    currPosition++;
    // Set position 51
    positionMappings[currPosition] = new Position(INDEX_0, INDEX_6);
    currPosition++;
    // Set positions 52-56
    for (int i = INDEX_1; i <= INDEX_5; i++) {
      positionMappings[currPosition] = new Position(i, INDEX_7);
      currPosition++;
    }
    // Set position 57-61
    for (int i = INDEX_1; i <= INDEX_5; i++) {
      positionMappings[currPosition] = new Position(INDEX_7, i);
      currPosition++;
    }
    // Set positions 62-66
    for (int i = INDEX_13; i >= INDEX_9; i--) {
      positionMappings[currPosition] = new Position(i, INDEX_7);
      currPosition++;
    }
    // Set positions 67-71
    for (int i = INDEX_13; i >= INDEX_9; i--) {
      positionMappings[currPosition] = new Position(INDEX_7, i);
      currPosition++;
    }

    // Set red player starting positions
    positionMappings[currPosition] = new Position(INDEX_1, INDEX_1);
    currPosition++;
    positionMappings[currPosition] = new Position(INDEX_4, INDEX_1);
    currPosition++;
    positionMappings[currPosition] = new Position(INDEX_1, INDEX_4);
    currPosition++;
    positionMappings[currPosition] = new Position(INDEX_4, INDEX_4);
    currPosition++;

    // Set blue player starting positions
    positionMappings[currPosition] = new Position(INDEX_10, INDEX_1);
    currPosition++;
    positionMappings[currPosition] = new Position(INDEX_13, INDEX_1);
    currPosition++;
    positionMappings[currPosition] = new Position(INDEX_10, INDEX_4);
    currPosition++;
    positionMappings[currPosition] = new Position(INDEX_13, INDEX_4);
    currPosition++;

    // Set green player starting positions
    positionMappings[currPosition] = new Position(INDEX_10, INDEX_10);
    currPosition++;
    positionMappings[currPosition] = new Position(INDEX_13, INDEX_10);
    currPosition++;
    positionMappings[currPosition] = new Position(INDEX_10, INDEX_13);
    currPosition++;
    positionMappings[currPosition] = new Position(INDEX_13, INDEX_13);
    currPosition++;

    // Set yellow player starting positions
    positionMappings[currPosition] = new Position(INDEX_1, INDEX_10);
    currPosition++;
    positionMappings[currPosition] = new Position(INDEX_4, INDEX_10);
    currPosition++;
    positionMappings[currPosition] = new Position(INDEX_1, INDEX_13);
    currPosition++;
    positionMappings[currPosition] = new Position(INDEX_4, INDEX_13);
  }

  /**
   * A method to fetch the index mapping of a given position.
   * 
   * @param positionX
   *          The x coordinate of the position
   * @param positionY
   *          The y coordinate of the position
   * @return THe index of the 1D array
   */
  public final int getIndexMapping(final int positionX, final int positionY) {
    return indexMappings[positionX][positionY];
  }

  /**
   * A method to fetch the index mapping of a given position.
   * 
   * @param position
   *          The x coordinate of the position
   * @return THe position at the index
   */
  public final Position getPositionMapping(final int position) {
    return positionMappings[position];
  }
}
