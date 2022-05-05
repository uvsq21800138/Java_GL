package maze;

public enum App {;
  public static void main (String[] args) {
    MazeIhm ihm = new MazeIhm();
    System.out.println("# Entrée dans le Labyrinthe #");
    ihm.run();
    System.out.println("Vous avez pris la sortie de secours ;)");
  }
}
