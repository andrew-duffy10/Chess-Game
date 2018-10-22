package model;

import model.pieces.Pieces;

/**
 * Represents a Chess board. Contains a group of BitSets that each represent a type of piece
 * and the positions of each instance of that piece-type on the chess board.
 */
public interface BoardModel {

  /**
   * Getter for the list of pieces contained in this board
   * @return an array of pieces on this board
   */
  Pieces[] getPieces();

  /**
   * Getter for the checked state of the kings on this board
   * @return is a king on this board checked?
   */
  boolean isChecked();

  /**
   * Setter for the checked state of the kings on this board
   * @param checked the new value for this board's checked state
   */
  void setChecked(boolean checked);

  /**
   * Moves the piece from the slot fromSlot to the slot toSlot
   * @param fromSlot  the slot with a piece to be moved
   * @param toSlot    the destination slot
   */
  void movePiece(int fromSlot, int toSlot);

}
