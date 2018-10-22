package model.pieces;

import java.util.BitSet;

/**
 * A set of Rooks on a gameboard.
 */
public class Rooks extends PieceAbstraction{

  /**
   * Constructor for a set of Rooks
   * @param isWhite are these Rooks white?
   */
  public Rooks(boolean isWhite) {
    super();
    super.isWhite = isWhite;
  }


  @Override
  public boolean canMove(int fromSlot, int toSlot, BitSet opBoard, BitSet alBoard) {
    if (fromSlot == toSlot) {
      return false;
    } else {
      if ((Math.abs(toSlot - fromSlot) % 8) == 0) {
        int increment;
        if (toSlot - fromSlot < 0) {
          increment = -8;
        } else {
          increment = 8;
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

      } else if (Math.abs(toSlot - fromSlot) < 8
              && toSlot / 8 == fromSlot / 8) {
        int increment;
        if (toSlot - fromSlot < 0) {
          increment = -1;
        } else {
          increment = 1;
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
