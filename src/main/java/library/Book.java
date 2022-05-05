package library;

import java.util.ArrayList;
import java.util.List;

public class Book {
  private Integer idISBN;
  private String title;
  private List<Author> listAuthor;

  public Book(String title, Integer idISBN, Author first) {
    this.title = title;
    this.idISBN = idISBN;
    this.listAuthor = new ArrayList<>();
    this.listAuthor.add(first); // au moins 1 auteur
  }

  public Integer getIdISBN() {
    return idISBN;
  }

  public String getTitle() {
    return title;
  }

  public List<Author> getListAuthor() {
    return listAuthor;
  }

  public void addAuthor(Author author)  {
    if(!listAuthor.contains(author)) listAuthor.add(author);
  }

  /**
   * Affiche les attributs du livre et la liste de ses auteurs (seulement les noms).
   *
   * @return une chaine de caractères
   */
  @Override
  public String toString() {
    StringBuilder listA = new StringBuilder();
    for (Author a : listAuthor) {
      listA.append(a.getName()).append(' ');
    }
    return title + ", ISBN=" + idISBN + ", Auteurs { " + listA.toString() + " }";
  }
}
