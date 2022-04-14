package doc;

public class Paragraphe implements Element {

  /** Texte contenue dans le paragraphe. */
  private String contenue;

  /** Constructeur. */
  public Paragraphe(String contenue) {
    this.contenue = contenue;
  }

  public String getContenue() {
    return contenue;
  }

  /** Test si est l'élément cherché. */
  @Override
  public boolean contains(Element e) {
    return false; // ne peut contenir que du texte
  }

  /** Renvoie le nombre de caractères de son contenue. */
  @Override
  public int nbChar() {
    return contenue.length();
  }

  /** Renvoie le nombre de section contenue comme null. */
  @Override
  public int nbSection() {
    return 0;
  }
}
