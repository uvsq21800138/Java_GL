package maze;

public class ActionDo implements Action {
  private Direction move;

  public ActionDo(Direction direction)  {
    this.move = direction;
  }

  @Override
  public void execute(Labyrinth path) {
    path.setMoves(move);
  }
}
