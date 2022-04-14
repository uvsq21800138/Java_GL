package book;

public class Chapitre {
  /** Titre du chapitre. */
  private String titre;
  /** Contenue du chapitre. */
  private String contenue;

  /* Constructeur simple. */
  public Chapitre(String titre, String contenue) {
    this.titre = titre;
    this.contenue = contenue;
  }

  /* Seule fonction de Getter nécessaire. */
  public String getTitre() {
    return titre;
  }
}
