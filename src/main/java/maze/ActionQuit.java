package maze;

public class ActionQuit implements Action {

  @Override
  public void execute(Labyrinth path) {
    path.finish();
  }
}
