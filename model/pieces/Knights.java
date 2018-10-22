package model.pieces;

import java.util.BitSet;

/**
 * A set of Knights on a gameboard.
 */
public class Knights extends PieceAbstraction {

  /**
   * Constructor for a set of Knights
   * @param isWhite are these Knights white?
   */
  public Knights(boolean isWhite) {
    super();
    super.isWhite = isWhite;
  }


  @Override
  public boolean canMove(int fromSlot, int toSlot, BitSet opBoard, BitSet alBoard) {
    if (alBoard.get(toSlot)) {
      return false;
    } else if (Math.abs(fromSlot - toSlot) == 15
            || Math.abs(fromSlot - toSlot) == 17
            || Math.abs(fromSlot - toSlot) == 6
            || Math.abs(fromSlot - toSlot) == 10) {
      return true;
    } else {
      return false;
    }
  }
}
