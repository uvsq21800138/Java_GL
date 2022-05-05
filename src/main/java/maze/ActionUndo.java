package maze;

public class ActionUndo implements Action {

  public ActionUndo() {
  }

  @Override
  public void execute(Labyrinth path) {
    path.unsetMoves();
  }
}
