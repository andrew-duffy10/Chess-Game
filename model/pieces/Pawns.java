package model.pieces;

import java.util.BitSet;

/**
 * A set of Pawnss on a gameboard.
 */
public class Pawns extends PieceAbstraction {
  private int justJumped; // Did this piece make a double-jump last move?

  /**
   * Constructor for a set of Pawns
   * @param isWhite are these Pawns white?
   */
  public Pawns(boolean isWhite) {
    super();
    super.isWhite = isWhite;
    justJumped = -1;
  }

  /**
   * Promote all Pawns on the final rank to Queens.
   * @param pieces    the pieces on the board
   */
  public void promote(Pieces[] pieces) {
    if (isWhite) {
      for (int idx = 0; idx < 8; idx++) {
        if (bitBoard.get(idx)) {
          bitBoard.set(idx, false);
          pieces[4].getBitBoard().set(idx);
        }
      }
    } else {
      for (int idx = 56; idx < 64; idx++) {
        if (bitBoard.get(idx)) {
          bitBoard.set(idx, false);
          pieces[10].getBitBoard().set(idx);
        }
      }
    }
  }

  /**
   * Checks if this move is a legal En Passant.
   * @param fromSlot    the slot occupied by the attacking Pawn
   * @param toSlot      the slot behind the justJumped Pawn
   * @param pieces      the pieces on the board
   * @return            is this a valid En Passant?
   */
  public boolean canPoisson(int fromSlot, int toSlot, Pieces[] pieces) {
    if (isWhite && pieces[6].getBitBoard().get(toSlot + 8) && ((Pawns)pieces[6]).getJustJumped() ==
            toSlot + 8 && (fromSlot - 7 == toSlot || fromSlot - 9 == toSlot)) {
      return true;
    } else if (!isWhite && pieces[0].getBitBoard().get(toSlot - 8) &&
            ((Pawns)pieces[0]).getJustJumped() == toSlot - 8&& (fromSlot + 7 ==
            toSlot || fromSlot + 9 == toSlot)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Getter for the int justJumped (did this pawn make a double jump last move?).
   * @return    the number of moves made by this piece
   */
  public int getJustJumped() {
    return justJumped;
  }

  /**
   * Setter for the int justJumped.
   * @param jumped    new justJumped value
   */
  public void setJustJumped(int jumped) {
    justJumped = jumped;
  }

  @Override
  public boolean canMove(int fromSlot, int toSlot, BitSet opBoard, BitSet alBoard) {
    if (isWhite) {
      if (opBoard.get(toSlot) && (fromSlot - 7 == toSlot || fromSlot - 9 == toSlot)) {
        return true;
      } else if (fromSlot - 8 == toSlot && !alBoard.get(toSlot) && !opBoard.get(toSlot)) {
        return true;
      } else if (fromSlot <= 55 && fromSlot >= 48 && fromSlot - 16 == toSlot
              && !opBoard.get(toSlot)) {
        justJumped = toSlot;
        return true;
      } else {
        return false;
      }

    } else {
      if (opBoard.get(toSlot) && (fromSlot + 7 == toSlot || fromSlot + 9 == toSlot)) {
        return true;
      } else if (fromSlot + 8 == toSlot && !alBoard.get(toSlot) && !opBoard.get(toSlot)) {
        return true;
      } else if (fromSlot <= 15 && fromSlot >= 8 && fromSlot + 16 == toSlot
              && !opBoard.get(toSlot)) {
        justJumped = toSlot;
        return true;
      } else {
        return false;
      }
    }

  }
}
