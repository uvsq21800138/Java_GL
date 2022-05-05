package maze;

import java.util.ArrayList;
import java.util.List;

public class Labyrinth {

  /** Instance unique */
  private static Labyrinth INSTANCE;
  /** Liste de mouvement */
  private List<Direction> moves;

  /** Constructeur privé */
  private Labyrinth() {
    this.moves = new ArrayList<>();
  }

  /** Atteindre l'instance du singleton */
  public static Labyrinth getInstance() {
    if (INSTANCE == null) { // Crée l’instance au premier appel
      INSTANCE = new Labyrinth();
    }
    return INSTANCE;
  }

  /**
   * Ajout un nouveau mouvement.
   *
   * @param d Direction du mouvement
   */
  public void setMoves(Direction d) {
    INSTANCE.moves.add(d);
  }

  /** Retire le dernier mouvement. */
  public void unsetMoves() {
    if (!INSTANCE.moves.isEmpty()) INSTANCE.moves.remove(INSTANCE.moves.size() - 1);
  }

  /** Vide l'historique. */
  public void finish() {
    INSTANCE.moves.clear();
  }

  /**
   * Affiche l'historique.
   *
   * @return une chaine de caractères
   */
  public String history() {
    if (INSTANCE.moves.isEmpty()) return "vide";
    else return INSTANCE.moves.toString();
  }

  public void run(Action action){
    if (action != null) {
      action.execute(INSTANCE);
    }
  }

  /** Exemple d'exécution */
  public static void main(String[] arg) {
    getInstance().setMoves(Direction.Nord);
    System.out.println(getInstance().history());
    getInstance().unsetMoves();
    System.out.println(getInstance().history());
    getInstance().finish();
  }
}
