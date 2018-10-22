package model.pieces;

import java.util.BitSet;

/**
 * A representation of a set of pieces on a chessboard that are the same color and type.
 */
public interface Pieces {

  /**
   * Access the bitboard of this piece. Allows the setting/getting of any
   * index in the BitSet.
   *
   * @return            a BitSet representing the slots filled by this Piece-type
   */
  BitSet getBitBoard();

  /**
   * Access the color of this Piece instance.
   *
   * @return            a boolean representing the color of this set of Pieces
   */
  boolean isWhite();

  /**
   * Determine if this Piece-set has a Piece at a given slot and if it is able to move to
   * another given slot.
   *
   * @param fromSlot    the starting slot
   * @param toSlot      the destination slot
   * @param opBoard     the combined BitSet of all enemy pieces
   * @param alBoard     the combined BitSet of all friendly pieces
   * @return            a boolean value representing the legality of the given piece movement
   */
  boolean canMove(int fromSlot, int toSlot, BitSet opBoard, BitSet alBoard);

  /**
   * Access how many moves this piece has made by returning the stored hasMoved value.
   *
   * @return            the int representing how many moves this piece has made
   */
  int hasMoved();

  /**
   * Set the moved value of this Piece to a new value.
   *
   * @param moved       the new moved value
   */
  void setMoved(int moved);

}
