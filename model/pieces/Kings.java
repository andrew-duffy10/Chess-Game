package model.pieces;

import java.util.BitSet;



import static model.BoardModelImpl.getSideBoard;


/**
 * A set of Kings on a gameboard.
 */
public class Kings extends PieceAbstraction {

  /**
   * Constructor for a set of Kings
   * @param isWhite are these Kings white?
   */
  public Kings(boolean isWhite) {
    super();
    super.isWhite = isWhite;

  }


  /**
   * Checks if the given slot is being attacked by any piece of a different color to this
   * King.
   * @param slot      the slot being checked for attackers
   * @param pieces    the Pieces on the board
   * @return          is this slot being attacked?
   */
  public boolean isChecked(int slot,Pieces[] pieces) {
    boolean isChecked = false;
    if (isWhite) {
      for (int idx = 6; idx < 12; idx++) {
        Pieces piece = pieces[idx];
        int bit = 0;
        if (!(piece instanceof Pawns)) {
          while (bit >= 0) {
            if (piece.canMove(piece.getBitBoard().nextSetBit(bit), slot,
                    getSideBoard(!isWhite, pieces), getSideBoard(isWhite, pieces))) {
              isChecked = true;
            }
            bit = piece.getBitBoard().nextSetBit(bit + 1);
          }
        } else {
          while (bit >= 0) {
            if (piece.getBitBoard().get(slot - 7)
                    || piece.getBitBoard().get(slot - 9)) {
              isChecked = true;
            }
            bit = piece.getBitBoard().nextSetBit(bit + 1);
          }
        }
      }
    } else {
      for (int idx = 0; idx < 6; idx++) {
        Pieces piece = pieces[idx];
        int bit = 0;
        if (!(piece instanceof Pawns)) {
          while (bit >= 0) {
            if (piece.canMove(piece.getBitBoard().nextSetBit(bit), slot,
                    getSideBoard(isWhite, pieces), getSideBoard(!isWhite, pieces))) {
              isChecked = true;
            }
            bit = piece.getBitBoard().nextSetBit(bit + 1);
          }
        } else {
          while (bit >= 0) {
            if (piece.getBitBoard().get(slot + 7)
                    || piece.getBitBoard().get(slot + 9)) {
              isChecked = true;
            }
            bit = piece.getBitBoard().nextSetBit(bit + 1);
          }
        }
      }
    }

    return isChecked;
  }

  /**
   * Checks if this King can castle given two selected slots.
   * @param fromSlot    the slot occupied by the King
   * @param toSlot      the slot occupied by the Rook
   * @param alBoard     the combined Bitset of all allied pieces
   * @param pieces      all pieces on the board
   * @return            is the move from fromSlot to toSlot a valid castle?
   */
  public boolean canCastle(int fromSlot, int toSlot, BitSet alBoard, Pieces[] pieces) {
    Kings temp = new Kings(isWhite);
    temp.getBitBoard().set(fromSlot);

    if (isChecked(getBitBoard().nextSetBit(0),pieces) || hasMoved != 0) {
      return false;
    } else if (Math.abs(fromSlot - toSlot) == 3) {
      for (int idx = 1; idx < 4; idx++) {
        temp.getBitBoard().set(fromSlot + idx);
        temp.getBitBoard().set(fromSlot + (idx - 1), false);
        if (temp.isChecked(temp.getBitBoard().nextSetBit(0),pieces)) {
          return false;
        }
      }
      if (pieces[9].getBitBoard().get(7) && !isWhite
              && !alBoard.get(6) && !alBoard.get(5) && fromSlot == 4){
        return true;
      } else if (pieces[3].getBitBoard().get(63) && isWhite
              && !alBoard.get(62) && !alBoard.get(61) && fromSlot == 60) {
        return true;
      } else {
        return false;
      }
    } else if (Math.abs(fromSlot - toSlot) == 4) {
      for (int idx = 1; idx < 5; idx++) {
        temp.getBitBoard().set(fromSlot - idx);
        temp.getBitBoard().set(fromSlot - (idx - 1), false);
        if (temp.isChecked(temp.getBitBoard().nextSetBit(0),pieces)) {
          return false;
        }
      }
      if (pieces[9].getBitBoard().get(0) && !isWhite
              && !alBoard.get(1) && !alBoard.get(2) && !alBoard.get(3) && fromSlot == 4){
        return true;
      } else if (pieces[3].getBitBoard().get(56) && isWhite
              && !alBoard.get(57) && !alBoard.get(58) && !alBoard.get(59) && fromSlot == 60) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }


  @Override
  public boolean canMove(int fromSlot, int toSlot, BitSet opBoard, BitSet alBoard) {
    if (Math.abs(fromSlot - toSlot) == 1 && !alBoard.get(toSlot)) {
      return true;
    } else if ((Math.abs(fromSlot - toSlot) == 8
            || Math.abs(fromSlot - toSlot) == 7
            || Math.abs(fromSlot - toSlot) == 9)
            && !alBoard.get(toSlot)){
      return true;
    } else {
      return false;
    }
  }
}
