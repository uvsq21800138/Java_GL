package doc;

import java.util.ArrayList;
import java.util.List;

public class Document {
  private String titre;
  private String auteur;
  private List<Section> listSection;

  public Document(String titre, String auteur) {
    this.titre = titre;
    this.auteur = auteur;
    this.listSection = new ArrayList<>();
  }
  /**
   * Ajoute la section si absente.
   *
   * @param section section à ajouter
   */
  public void addSection(Section section) {
    if (!listSection.contains(section)) listSection.add(section);
  }

  /**
   * Retire la section si présente.
   *
   * @param section section à retirer
   */
  public void removeSection(Section section) {
    if (listSection.contains(section)) listSection.remove(section);
  }

  /**
   * Teste si le Document contient un élément.
   *
   * @param e élément à rechercher
   * @return <code>true</code> si il est présent dans le contenue
   */
  public boolean contains(Element e) {
    return listSection.contains(e);
  }

  /** Renvoie le nombre de caractères de ses sections. */
  public int nbChar() {
    int sum = 0;
    for (Section e : listSection) {
      sum += e.nbChar();
    }
    return sum;
  }

  /** Renvoie le nombre de section qu'il contient. */
  public int nbSection() {
    int sum = 0;
    for (Section e : listSection) {
      sum += e.nbSection();
    }
    return sum;
  }
}
