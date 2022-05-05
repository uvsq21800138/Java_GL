package maze;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LabyrinthTest {
  Labyrinth maze;

  /** Avant chaque test. */
  @Before
  public void setup() {
    maze = Labyrinth.getInstance();
  }

  /** Après chaque test. */
  @After
  public void unset() {
    maze.finish();
  }

  @Test
  public void shouldRunTest() {
    maze.setMoves(Direction.Nord);
    assertEquals("[Nord]",maze.history());
    maze.setMoves(Direction.Est);
    assertEquals("[Nord, Est]",maze.history());
    maze.unsetMoves();
    assertEquals("[Nord]",maze.history());
    maze.setMoves(Direction.Est);
    maze.setMoves(Direction.Est);
    assertEquals("[Nord, Est, Est]",maze.history());
    maze.finish();
    assertEquals("vide",maze.history());
  }

}
