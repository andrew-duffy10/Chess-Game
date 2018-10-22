package model.pieces;

import java.util.BitSet;

/**
 * A set of Bishops on a gameboard.
 */
public class Bishops extends PieceAbstraction {

  /**
   * Constructor for a set of Bishops
   * @param isWhite are these Bishops white?
   */
  public Bishops(boolean isWhite) {
    super();
    super.isWhite = isWhite;
  }


  @Override
  public boolean canMove(int fromSlot, int toSlot, BitSet opBoard, BitSet alBoard) {
    if (fromSlot == toSlot) {
      return false;
    } else {
      if ((Math.abs(toSlot - fromSlot) % 7) == 0) {
        int increment;
        if (toSlot - fromSlot < 0) {
          increment = -7;
        } else {
          increment = 7;
        }

        boolean isBlocked = false;

        for (int idx = fromSlot + increment; idx != toSlot; idx = idx + increment) {
          if (opBoard.get(idx)
                  || alBoard.get(idx)) {
            isBlocked = true;
          }
        }

        if (isBlocked
                || alBoard.get(toSlot)) {
          return false;
        } else {
          return true;
        }

      } else if ((Math.abs(toSlot - fromSlot) % 9) == 0) {
        int increment;
        if (toSlot - fromSlot < 0) {
          increment = -9;
        } else {
          increment = 9;
        }

        boolean isBlocked = false;

        for (int idx = fromSlot + increment; idx != toSlot; idx = idx + increment) {
          if (opBoard.get(idx)
                  || alBoard.get(idx)) {
            isBlocked = true;
          }
        }

        if (isBlocked
                || alBoard.get(toSlot)) {
          return false;
        } else {
          return true;
        }
      } else {
        return false;
      }
    }
  }
}
