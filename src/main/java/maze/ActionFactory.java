package maze;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Factory pour stocker et gérer les appels des fonctions.
 */
public class ActionFactory{
  private final Map<String , Action> actions ;
  private ActionFactory() {
    this.actions = new HashMap<>();
  }

  public void addAction ( String name, Action action ) {
    this.actions.put(name, action);
  }

  public Action getAction( String name ) {
    if( this.actions.containsKey(name) ) {
      return this.actions.get(name);
    }
    return null;
  }

  public static ActionFactory init() {
    ActionFactory factory = new ActionFactory ();
    factory.addAction("nord", new ActionDo(Direction.Nord));
    factory.addAction("sud", new ActionDo(Direction.Sud));
    factory.addAction("est", new ActionDo(Direction.Est));
    factory.addAction("ouest", new ActionDo(Direction.Ouest));
    factory.addAction("quit", new ActionQuit());
    factory.addAction("undo", new ActionUndo());
    return factory;
  }
}