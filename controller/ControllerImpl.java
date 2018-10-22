package controller;

import model.BoardModel;
import model.BoardModelImpl;
import view.VisualView;


/**
 * Implementation of the Controller interface.
 */
public class ControllerImpl implements Controller {
  private BoardModel model; // The model containing the board and pieces.
  private VisualView view; // The view that displays the model in a specified way (Java Swing).

  /**
   * Constructs a ControllerImpl().
   */
  public ControllerImpl() {
    model = new BoardModelImpl();
    view = new VisualView(model);


  }

  @Override
  public void play() {

    view.draw();

  }



}
