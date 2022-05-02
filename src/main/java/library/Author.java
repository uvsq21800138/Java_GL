package library;

import java.util.ArrayList;
import java.util.List;

public class Author {
  private String name;
  private String email;
  private List<Book> biblio;

  public Author(String name, String email) {
    this.name = name;
    this.email = email;
    this.biblio = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public List<Book> getBiblio() {
    return biblio;
  }

  public void addBook(Book book)  {
    if(!biblio.contains(book)) biblio.add(book);
  }

  public String biblioToString() {
    StringBuilder listB = new StringBuilder();
    for (Book b : biblio) {
      listB.append(b.toString()).append(' ');
    }
    return listB.toString();
  }

  @Override
  public String toString() {
    return name + " : email=" + email + "; " + biblio.size() + " livres.";
  }
}
