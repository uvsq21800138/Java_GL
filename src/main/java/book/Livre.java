package book;

import java.time.LocalDate;
import java.util.List;

public class Livre {

  /** Titre du livre. */
  private String titre;
  /** Nom de l'auteur du livre. */
  private String auteur;
  /** Date de la parution du livre. Par défaut le 1er Janvier 1990. */
  private LocalDate dateParution;
  /** Liste des chapitres du livre. Au moins un chapitre. */
  private List<Chapitre> chapitreList;

  /**
   * Constructeur du livre qui va utiliser le pattern Builder.
   *
   * @param builder instance de <code>LivreBuilder</code>
   */
  public Livre(LivreBuilder builder) {
    this.titre = builder.titre;
    this.auteur = builder.auteur;
    this.dateParution = builder.dateParution;
    this.chapitreList = builder.chapitreList;
  }

  public String getTitre() {
    return titre;
  }

  public String getAuteur() {
    return auteur;
  }

  public LocalDate getDateParution() {
    return dateParution;
  }

  public List<Chapitre> getChapitreList() {
    return chapitreList;
  }

  /**
   * Construit la table des matières.
   *
   * <p>Utilise StringBuilder pour être traité comme une concaténation de caractère en liste.
   *
   * @return la table des matière sous une forme fixée
   */
  public String getTableMatieres() {
    StringBuilder table = new StringBuilder();
    table.append(String.format("%s(", this.titre));
    int index = 1;
    for (Chapitre chap : this.chapitreList) {
      if (index > 1) {
        table.append(", ");
      }
      table.append(String.format("%d. %s", index, chap.getTitre()));
      index += 1;
    }
    table.append(")");
    return table.toString();
  }
}
