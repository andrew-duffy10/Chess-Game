package model.pieces;

import java.util.BitSet;

/**
 * Implementation of the Piecea interface.
 */
public abstract class PieceAbstraction implements Pieces {

  protected BitSet bitBoard; // a 64 bit BitSet representing the slots on a Chess Board,
  // 1's are pieces 0's are empty slots
  protected boolean isWhite; // is it Whites' turn?
  protected int hasMoved; // How many times this piece-type has moved


  /**
   * Constructs a new Pieces by initializing a 64 bit BitSet and setting its hasMoved value
   * to 0.
   */
  PieceAbstraction() {
    bitBoard = new BitSet(64);
    hasMoved = 0;
  }

  @Override
  public BitSet getBitBoard () {
    return this.bitBoard;
  }

  @Override
  public boolean isWhite() {
    return isWhite;
  }

  @Override
  public int hasMoved() { return hasMoved;}

  @Override
  public void setMoved(int moved) {
    hasMoved = moved;
  }

}
