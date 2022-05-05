package maze;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/** Test à la fois les classes implémentant Action et ActionFactory. */
public class ActionTest {
  Labyrinth maze;
  ActionFactory factory;

  /** Avant chaque test. */
  @Before
  public void setup() {
    maze = Labyrinth.getInstance();
    factory = ActionFactory.init();
  }

  /** Après chaque test. */
  @After
  public void unset() {
    maze.finish();
  }

  @Test
  public void shouldConstructTest() {
    assertTrue(factory.getAction("nord") instanceof ActionDo);
    assertTrue(factory.getAction("est") instanceof ActionDo);
    assertTrue(factory.getAction("sud") instanceof ActionDo);
    assertTrue(factory.getAction("ouest") instanceof ActionDo);
    assertTrue(factory.getAction("undo") instanceof ActionUndo);
    assertTrue(factory.getAction("quit") instanceof ActionQuit);
  }

  @Test
  public void shouldDoneTest() {
    maze.run(factory.getAction("nord"));
    assertEquals("[Nord]", maze.history());
    maze.run(factory.getAction("est"));
    assertEquals("[Nord, Est]", maze.history());
    maze.run(factory.getAction("sud"));
    assertEquals("[Nord, Est, Sud]", maze.history());
    maze.run(factory.getAction("ouest"));
    assertEquals("[Nord, Est, Sud, Ouest]", maze.history());
  }

  @Test
  public void shouldUndoneTest() {
    maze.run(factory.getAction("nord"));
    maze.run(factory.getAction("est"));
    assertEquals("[Nord, Est]", maze.history());
    maze.run(factory.getAction("undo"));
    assertEquals("[Nord]", maze.history());
  }

  @Test
  public void shouldUndoneIfEmptyTest() {
    maze.run(factory.getAction("undo"));
    assertEquals("vide", maze.history());
  }

  @Test
  public void shouldQuitTest() {
    maze.run(factory.getAction("nord"));
    maze.run(factory.getAction("est"));
    assertEquals("[Nord, Est]", maze.history());
    maze.run(factory.getAction("quit"));
    assertEquals("vide", maze.history());
  }
}
