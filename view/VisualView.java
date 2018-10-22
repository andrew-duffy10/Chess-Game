package view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import model.BoardModel;

/**
 * Implementation of the View interface.
 */
public class VisualView extends JFrame implements View{
  private BoardPanel display; // JPanel that displays pieces on a checkered canvas
  private BoardModel model; // Current working model
  private int fromLoc; // The first slot selected for a piece move
  private int toLoc; // The second slot selected for a piece move


  /**
   * Constructs a VisualView JFrame, initializes fromLoc and toLoc to -1 (an invalid slot number)
   * and sets up a readable display that exits on close. Assigns a BoardPanel (JPanel) to this
   * JFrame.
   * @param model the initial model with piece positions
   */
  public VisualView(BoardModel model) {
    super();
    this.model = model;
    fromLoc = -1;
    toLoc = -1;
    display = new BoardPanel();
    setSize(815, 838);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.add(display);
    display.addMouseListener(new PieceSelector());
    display.setVisible(true);
  }


  /**
   * Action Listener for Mouse actions, MousePressed is the only implemented action.
   * When the mouse is pressed, repaint the canvas with the new fromLoc or toLoc (depending on
   * previous mouse press).
   */
  public class PieceSelector implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
      if (fromLoc < 0) {
        fromLoc = (e.getX() / 100) + (e.getY() / 100) * 8;
        display.repaint(model.getPieces(),fromLoc,-1, model.isChecked());
      } else {
        toLoc = (e.getX() / 100) + (e.getY() / 100) * 8;
        model.setChecked(false);
        draw();
      }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

  }



  @Override
  public void draw() {
    super.setVisible(true);
    display.repaint(model.getPieces(), fromLoc, toLoc, model.isChecked());
    if (toLoc >= 0) {
      model.movePiece(fromLoc, toLoc);
      display.repaint(model.getPieces(), -1, toLoc, model.isChecked());

      fromLoc = -1;
      toLoc = -1;
    }


  }

}
