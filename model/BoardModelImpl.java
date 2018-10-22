package model;

import java.util.BitSet;

import model.pieces.Bishops;
import model.pieces.Kings;
import model.pieces.Knights;
import model.pieces.Pawns;
import model.pieces.Pieces;
import model.pieces.Queens;
import model.pieces.Rooks;

/**
 * Implementation of the BoardModel interface.
 */
public class BoardModelImpl implements BoardModel {

  // The pieces on this board
  Pieces wPawns, wKnights, wBishops, wRooks, wQueens, wKings,
          bPawns, bKnights, bBishops, bRooks, bQueens, bKings;

  // Is a king on this board checked?
  boolean isChecked;

  // Is it whites turn to move a piece?
  boolean whitesMove;

  /**
   * Constructor for a BoardModel. Initializes the pieces with starting values in their BitSets.
   */
  public BoardModelImpl() {
    isChecked = false;
    whitesMove = true;
    wPawns = new Pawns(true);
    bPawns = new Pawns(false);

    wKnights = new Knights(true);
    bKnights = new Knights(false);

    wBishops = new Bishops(true);
    bBishops = new Bishops(false);

    wRooks = new Rooks(true);
    bRooks = new Rooks(false);

    wQueens = new Queens(true);
    bQueens = new Queens(false);

    wKings = new Kings(true);
    bKings = new Kings(false);

    bPawns.getBitBoard().set(8, 16);
    bKnights.getBitBoard().set(1);
    bKnights.getBitBoard().set(6);
    bBishops.getBitBoard().set(2);
    bBishops.getBitBoard().set(5);
    bRooks.getBitBoard().set(0);
    bRooks.getBitBoard().set(7);
    bQueens.getBitBoard().set(3);
    bKings.getBitBoard().set(4);
    wPawns.getBitBoard().set(48, 56);
    wKnights.getBitBoard().set(62);
    wKnights.getBitBoard().set(57);
    wBishops.getBitBoard().set(61);
    wBishops.getBitBoard().set(58);
    wRooks.getBitBoard().set(63);
    wRooks.getBitBoard().set(56);
    wQueens.getBitBoard().set(59);
    wKings.getBitBoard().set(60);


  }

  @Override
  public void setChecked(boolean checked) {
    this.isChecked = checked;
  }

  @Override
  public boolean isChecked() {
    return isChecked;
  }

  @Override
  public Pieces[] getPieces() {
    return new Pieces[]{wPawns, wKnights, wBishops, wRooks, wQueens, wKings,
            bPawns, bKnights, bBishops, bRooks, bQueens, bKings};
  }

  /**
   * EXPERIMENT WITH BITSET FUNCTIONALITY:
   *
   * This function combines the BitSet's for every piece of a given color
   * into one BitSet
   * @param isWhite   the color of the pieces to be combined
   * @param pieces    the Pieces to be combined
   * @return          the combined BitSet
   */
  public static BitSet getSideBoard(boolean isWhite, Pieces[] pieces) {
    BitSet Board = new BitSet(64);
    if (isWhite) {
      for (int idx = 0; idx < 5; idx++) {
        Board.or(pieces[idx].getBitBoard());
      }
    } else {
      for (int idx = 6; idx < 12; idx++) {
        Board.or(pieces[idx].getBitBoard());
      }
    }
    return Board;
  }


  @Override
  public void movePiece(int fromSlot, int toSlot) {
    Pieces selected = null;
    Pieces destination = null;

    // Mark the selected Piece and the destination Piece if they exist
    // If the selected slot doesn't have a piece, break
    // If the destination slot doesn't have a piece, continue
    for (Pieces piece : getPieces()) {
      if (piece.getBitBoard().get(fromSlot)) {
        selected = piece;
      } else if (piece.getBitBoard().get(toSlot)) {
        destination = piece;
      }
    }

    // Checks if the selected Piece is the right color for whose turn it is
    if (selected != null && whitesMove == selected.isWhite()) {

      // Set the Pawns justJumped variable to show that they didn't just move (for passant)
      if (!(selected instanceof Pawns)) {
        ((Pawns) bPawns).setJustJumped(-1);
        ((Pawns) wPawns).setJustJumped(-1);
      }

      // Checks if the move is a legal En Passant and executes the move
      else if (selected.isWhite() && ((Pawns) selected).canPoisson(fromSlot, toSlot,
              getPieces())) {
        selected.getBitBoard().set(toSlot);
        selected.getBitBoard().set(fromSlot, false);
        bPawns.getBitBoard().set(toSlot + 8, false);
        ((Pawns) bPawns).setJustJumped(-1);
        ((Pawns) wPawns).setJustJumped(-1);
        whitesMove = !whitesMove;
      }

      // Checks if the move is a legal En Passant and executes the move
      else if (!selected.isWhite() && ((Pawns) selected).canPoisson(fromSlot, toSlot,
              getPieces())) {
        selected.getBitBoard().set(toSlot);
        selected.getBitBoard().set(fromSlot, false);
        wPawns.getBitBoard().set(toSlot - 8, false);
        ((Pawns) bPawns).setJustJumped(-1);
        ((Pawns) wPawns).setJustJumped(-1);
        whitesMove = !whitesMove;
      } else {
        ((Pawns) bPawns).setJustJumped(-1);
        ((Pawns) wPawns).setJustJumped(-1);
      }

      // Moves the selected piece to the destination and vacates the destination slot
      if (selected.canMove(fromSlot, toSlot, getSideBoard(!selected.isWhite(),
              getPieces()),
              getSideBoard(selected.isWhite(), getPieces()))) {
        if (destination != null) {
          if (selected.isWhite() != destination.isWhite()) {
            selected.getBitBoard().set(fromSlot, false);
            selected.getBitBoard().set(toSlot);
            destination.getBitBoard().set(toSlot, false);
            selected.setMoved(selected.hasMoved() + 1);
            whitesMove = !whitesMove;
          }
        } else {
          selected.getBitBoard().set(fromSlot, false);
          selected.getBitBoard().set(toSlot);
          selected.setMoved(selected.hasMoved() + 1);
          whitesMove = !whitesMove;
        }
      }

      // If the King was moved into a slot where it is checked, return the King
      // to its previous slot
      if (selected instanceof Kings
              && ((Kings) selected).isChecked(selected.getBitBoard().nextSetBit(0),
              getPieces())) {
        selected.getBitBoard().set(fromSlot);
        selected.getBitBoard().set(toSlot, false);
        whitesMove = !whitesMove;
        selected.setMoved(selected.hasMoved() + 1);
        if (destination != null) {
          destination.getBitBoard().set(toSlot);
        }
      }

      // Checks if the move was a castle, if it is set the respective King and Rook to
      // their correct slots
      if (selected instanceof Kings && ((Kings) selected).canCastle(fromSlot, toSlot,
              getSideBoard(selected.isWhite(), getPieces()), getPieces())) {
        if (Math.abs(fromSlot - toSlot) == 3) {

          selected.getBitBoard().set(toSlot - 1);
          selected.getBitBoard().set(fromSlot, false);
          destination.getBitBoard().set(toSlot, false);
          destination.getBitBoard().set(toSlot - 2);
          selected.setMoved(selected.hasMoved() + 1);
          whitesMove = !whitesMove;
        } else if (Math.abs(fromSlot - toSlot) == 4) {
          selected.getBitBoard().set(toSlot + 2);
          selected.getBitBoard().set(fromSlot, false);
          destination.getBitBoard().set(toSlot, false);
          destination.getBitBoard().set(toSlot + 3);
          selected.setMoved(selected.hasMoved() + 1);
          whitesMove = !whitesMove;
        }
      }


      // Checks that if a King is checked when the turn starts, that King is no longer checked
      // after the turn. If it is, nullify the move
      if ((!whitesMove &&
              ((Kings)wKings).isChecked(wKings.getBitBoard().nextSetBit(0),
              getPieces()))
              || (whitesMove && ((Kings)bKings).isChecked(
              bKings.getBitBoard().nextSetBit(0), getPieces()))) {
        selected.getBitBoard().set(fromSlot);
        selected.getBitBoard().set(toSlot, false);
        whitesMove = !whitesMove;
        selected.setMoved(selected.hasMoved() + 1);
        if (destination != null) {
          destination.getBitBoard().set(toSlot);
        }
      }

      if (selected instanceof Pawns) {
        ((Pawns) selected).promote(getPieces());
      }

    }


    // If after a move a King is checked, adjust the model to show that there is a check by
    // mutating the isChecked value
    if ((whitesMove && ((Kings)wKings).isChecked(wKings.getBitBoard().nextSetBit(0),
            getPieces()))
            || (!whitesMove && ((Kings)bKings).isChecked(
            bKings.getBitBoard().nextSetBit(0), getPieces()))) {
      isChecked = true;
    }

  }
}
