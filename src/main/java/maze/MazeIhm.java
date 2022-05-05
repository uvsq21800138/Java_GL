package maze;

import java.io.IOException;
import java.util.Scanner;

public class MazeIhm {
  private Scanner input;
  private Labyrinth maze;
  private ActionFactory factory;

  public MazeIhm() {
    this.input = new Scanner(System.in);
    this.maze = Labyrinth.getInstance();
    this.factory = ActionFactory.init();
  }

  public Action nextAction() throws IOException {
    String e = input.nextLine();
    Action a = factory.getAction(e);
    if (a == null) throw new IOException();
    else return a;
  }

  public void affiche() {
    System.out.println("Labyrinth : " + maze.history());
  }

  public void run() {
    System.out.print("Par où voulez-vous aller?\n> ");
    Action action = null;
    while (!(action instanceof ActionQuit)) {
      try {
        action = this.nextAction();
        maze.run(action);
        if (!(action instanceof ActionQuit)) {
          this.affiche();
          System.out.print("Par où voulez-vous aller?\n> ");
        }
      } catch (IOException e1) {
        System.out.println("Mauvais input : nord | sud | est | ouest | undo | quit");
        System.out.print("Par où voulez-vous aller?\n> ");
      }
    }
    this.input.close();
  }

  public static void main(String[] args) {
    MazeIhm ihm = new MazeIhm();
    ihm.run();
  }
}
