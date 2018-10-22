package model.pieces;

import java.util.BitSet;

/**
 * A set of Queens on a gameboard.
 */
public class Queens extends PieceAbstraction {

  /**
   * Constructor for a set of Queens
   * @param isWhite are these Queens white?
   */
  public Queens(boolean isWhite) {
    super();
    super.isWhite = isWhite;
  }


  @Override
  public boolean canMove(int fromSlot, int toSlot, BitSet opBoard, BitSet alBoard) {
    if (fromSlot < 0) {
      return false;
    } else {
      Pieces rookCheck = new Rooks(isWhite);
      rookCheck.getBitBoard().set(fromSlot);
      Pieces bishCheck = new Bishops(isWhite);
      bishCheck.getBitBoard().set(fromSlot);

      return rookCheck.canMove(fromSlot, toSlot, opBoard, alBoard)
              || bishCheck.canMove(fromSlot, toSlot, opBoard, alBoard);
    }
  }
}
