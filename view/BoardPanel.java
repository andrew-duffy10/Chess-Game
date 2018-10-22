package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.*;

import model.pieces.Kings;
import model.pieces.Pieces;

/**
 * The canvas that paints a checkered board and adds the chess pieces to the correct locations
 * from the model.
 */
public class BoardPanel extends JPanel {
  private Pieces[] pieces; // The pieces to be painted
  private int fromLoc; // The first selected location in a piece move
  private int toLoc; // The second selected location in a piece move
  private boolean checked; // Is either king checked?
  private BufferedImage blackBishop; // Black Bishop png
  private BufferedImage blackKing; // Black King png
  private BufferedImage blackKnight; // Black Knight png
  private BufferedImage blackPawn; // Black Pawn png
  private BufferedImage blackQueen; // Black Queen png
  private BufferedImage blackRook; // Black Rook png
  private BufferedImage whiteBishop; // White Bishop png
  private BufferedImage whiteKing; // White King png
  private BufferedImage whiteKnight; // White Knight png
  private BufferedImage whitePawn; // White Pawn png
  private BufferedImage whiteQueen; // White Queen png
  private BufferedImage whiteRook; // White Rook png
  private BufferedImage image; // Mutable image


  /**
   * Constructs a BoardPanel.
   */
  BoardPanel() {
    super();
    setLayout(null);
    try {
      // Assigns every piece's png from source folder.
      blackBishop = ImageIO.read(new File("src/view/piecepng/black_bishop.png"));
      blackKing = ImageIO.read(new File("src/view/piecepng/black_king.png"));
      blackKnight = ImageIO.read(new File("src/view/piecepng/black_knight.png"));
      blackPawn = ImageIO.read(new File("src/view/piecepng/black_pawn.png"));
      blackQueen = ImageIO.read(new File("src/view/piecepng/black_queen.png"));
      blackRook = ImageIO.read(new File("src/view/piecepng/black_rook.png"));
      whiteBishop = ImageIO.read(new File("src/view/piecepng/white_bishop.png"));
      whiteKing = ImageIO.read(new File("src/view/piecepng/white_king.png"));
      whiteKnight = ImageIO.read(new File("src/view/piecepng/white_knight.png"));
      whitePawn = ImageIO.read(new File("src/view/piecepng/white_pawn.png"));
      whiteQueen = ImageIO.read(new File("src/view/piecepng/white_queen.png"));
      whiteRook = ImageIO.read(new File("src/view/piecepng/white_rook.png"));
    } catch (IOException error) {
      throw new IllegalStateException("Invalid png.");
    }
    checked = false;

  }


  /**
   * Repaints the canvas with the given pieces, selected slots, and isChecked parameter
   *
   * @param pieces    the chess pieces to be drawn
   * @param fromLoc   the first selected slot in a piece move (-1 if none selected)
   * @param toLoc     the second selected slot in a piece move (-1 if none selected)
   * @param isChecked is either king checked?
   */
  public void repaint(Pieces[] pieces, int fromLoc, int toLoc, boolean isChecked) {
    this.pieces = pieces;
    this.fromLoc = fromLoc;
    this.toLoc = toLoc;
    this.checked = isChecked;
    repaint();
  }


  @Override
  public void paintComponent(Graphics g) throws IllegalArgumentException {
    super.paintComponent(g);
    boolean isWhite = false;
    Color color;

    // Draw the checkered board
    for (int x = 0; x < 8; x++) {
      isWhite = !isWhite;
      for (int y = 0; y < 8; y++) {
        if (isWhite) {
          color = Color.GRAY;
        } else {
          color = Color.DARK_GRAY;
        }
        g.setColor(color);
        g.fillRect(x * 100, y * 100, 100, 100);
        isWhite = !isWhite;
      }
    }

    // Is there a piece selected? If so, change the slot it resides in to the color orange.
    if (fromLoc >= 0) {

      int x = fromLoc % 8;
      int y = fromLoc / 8;
      color = Color.orange;
      g.setColor(color);
      g.fill3DRect(x * 100, y * 100, 100, 100, true);
    }


    // Assign the mutable image to the correct png (for this piece).
    for (int idx = 0; idx < 12; idx++) {
      switch (idx) {
        case 0:
          image = whitePawn;
          break;
        case 1:
          image = whiteKnight;
          break;
        case 2:
          image = whiteBishop;
          break;
        case 3:
          image = whiteRook;
          break;
        case 4:
          image = whiteQueen;
          break;
        case 5:
          image = whiteKing;
          break;
        case 6:
          image = blackPawn;
          break;
        case 7:
          image = blackKnight;
          break;
        case 8:
          image = blackBishop;
          break;
        case 9:
          image = blackRook;
          break;
        case 10:
          image = blackQueen;
          break;
        case 11:
          image = blackKing;
          break;

        default:
          break;
      }

      // Draw the current piece on the board at the correct location
      int currentBit = 0;
      while (pieces[idx].getBitBoard().nextSetBit(currentBit) != -1) {
        int pX;
        int pY;
        if (pieces[idx].getBitBoard().nextSetBit(currentBit) == fromLoc
                && toLoc >= 0) {
          pX = toLoc % 8;
          pY = toLoc / 8;


        } else {
          pX = pieces[idx].getBitBoard().nextSetBit(currentBit) % 8;
          pY = pieces[idx].getBitBoard().nextSetBit(currentBit) / 8;
        }
        g.drawImage(image, pX * 100 + 12, pY * 100 + 12,
                75, 75, null);
        currentBit = 1 + pieces[idx].getBitBoard().nextSetBit(currentBit);
      }


    }

    //is a King checked? If so, change the color of the square under the checked King to red.
    if (checked) {
      int wK = pieces[5].getBitBoard().nextSetBit(0);
      if (((Kings) pieces[5]).isChecked(wK, pieces)) {
        int x = wK % 8;
        int y = wK / 8;
        color = Color.red;
        g.setColor(color);
        g.fill3DRect(x * 100, y * 100, 100, 100, true);
        image = whiteKing;
        g.drawImage(image, x * 100 + 12, y * 100 + 12,
                75, 75, null);
      } else {
        int x = wK % 8;
        int y = wK / 8;
        color = Color.red;
        g.setColor(color);
        g.fill3DRect(x * 100, y * 100, 100, 100, true);
        image = blackKing;
        g.drawImage(image, x * 100 + 12, y * 100 + 12,
                75, 75, null);
      }
    }

  }


}





