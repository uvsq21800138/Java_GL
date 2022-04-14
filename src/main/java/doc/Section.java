package doc;

import java.util.ArrayList;
import java.util.List;

public class Section implements Element {
  /** Titre de la section. */
  private final String titre;
  /** Contenue en liste d'élément. */
  private final List<Element> contenue;

  public Section(String titre) {
    this.titre = titre;
    this.contenue = new ArrayList<>();
  }

  public String getTitre() {
    return titre;
  }

  public List<Element> getElements() {
    return contenue;
  }

  /**
   * Ajoute l'élément si possible.
   *
   * <p>Impossible si déjà contenue, si lui-même ou si il contient la section présente.
   *
   * @param e élément à ajouter
   */
  public void addElem(Element e) {
    // si ça ne contient pas déjà e, si e ne contient pas ça et si ça n'est pas e
    if (!this.contains(e) && !e.contains(this) && this != e) this.contenue.add(e);
  }

  /**
   * Retire l'élément si présent.
   *
   * @param e élément à retirer
   */
  public void removeElem(Element e) {
    if (this.contains(e)) this.contenue.remove(e);
  }

  /**
   * Teste si la Section contient un élément.
   *
   * @param e élément à rechercher
   * @return <code>true</code> si il est présent dans le contenue
   */
  @Override
  public boolean contains(Element e) {
    // si différent de lui-même et présent dans son contenue
    if (this != e && contenue.contains(e)) {
      return true;
    } else {
      return false;
    }
  }

  /** Renvoie le nombre de caractères de son contenue. */
  @Override
  public int nbChar() {
    int sum = titre.length();
    for (Element e : contenue) {
      sum += e.nbChar();
    }
    return sum;
  }

  /** Renvoie le nombre de section contenue. */
  @Override
  public int nbSection() {
    int sum = 1;
    for (Element e : contenue) {
      sum += e.nbSection();
    }
    return sum;
  }
}
