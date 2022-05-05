package library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import library.Author;
import library.Book;

public class AuthorDao extends Dao<Author> {
  private final Connection connection;

  public AuthorDao(Connection connection) {
    this.connection = connection;
  }

  @Override
  public boolean create(Author author) {
    try {
      PreparedStatement psInsert = connection.prepareStatement("INSERT INTO authors VALUES(?, ?)");
      psInsert.setString(1, author.getName());
      psInsert.setString(2, author.getEmail());
      psInsert.executeUpdate();

      psInsert = connection.prepareStatement("INSERT INTO books VALUES(?, ?)");
      for (Book book : author.getBiblio()) {
        psInsert.setInt(1, book.getIdISBN());
        psInsert.setString(2, book.getTitle());
        psInsert.executeUpdate();
      }

      psInsert = connection.prepareStatement("INSERT INTO write VALUES(?, ?)");
      for (Book book : author.getBiblio()) {
        psInsert.setString(1, author.getName());
        psInsert.setInt(2, book.getIdISBN());
        psInsert.executeUpdate();
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Lecture de la BD uniquement les relation direct à l'auteur.
   *
   * <p>L'auteur et ses livres (sans leurs autres auteurs).
   *
   * @param identifier nom de l'auteur
   * @return l'auteur créé avec les livres
   */
  @Override
  public Optional<Author> find(String identifier) {
    Author author = null;
    Book book = null;
    try {
      /* Recherche le l'auteur par son nom */
      PreparedStatement psInsert =
          connection.prepareStatement("SELECT * FROM authors WHERE name = ?");
      psInsert.setString(1, identifier);
      ResultSet rs = psInsert.executeQuery();
      if (rs.next()) {
        /* Créé l'instance de l'auteur */
        author = new Author(rs.getString(1), rs.getString(2));

        /* Recherche les livres écrits par l'auteur */
        psInsert = connection.prepareStatement("SELECT * FROM write WHERE author = ?");
        psInsert.setString(1, identifier);
        rs = psInsert.executeQuery();
        while (rs.next()) {
          /* Pour chaque livre, recherche son ISBN */
          psInsert = connection.prepareStatement("SELECT * FROM books WHERE isbn = ?");
          psInsert.setInt(1, rs.getInt(2));
          rs = psInsert.executeQuery();
          if (rs.next()) {
            book = new Book(rs.getString(2), rs.getInt(1), author);
          }
          author.addBook(book);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Optional.empty();
    }
    return Optional.ofNullable(author);
  }

  @Override
  public boolean update(Author author) {
    try {
      PreparedStatement ps =
          connection.prepareStatement("UPDATE authors SET email = ? WHERE name = ?");
      ps.setString(1, author.getEmail());
      ps.setString(2, author.getName());
      ps.executeUpdate();

      ps = connection.prepareStatement("DELETE FROM write WHERE author = ?");
      ps.setString(1, author.getName());
      ps.executeUpdate();

      ps = connection.prepareStatement("INSERT INTO books VALUES(?, ?)");
      for (Book book : author.getBiblio()) {
        ps.setInt(1, book.getIdISBN());
        ps.setString(2, book.getTitle());
        ps.executeUpdate();
      }

      ps = connection.prepareStatement("INSERT INTO write VALUES(?, ?)");
      for (Book book : author.getBiblio()) {
        ps.setString(1, author.getName());
        ps.setInt(2, book.getIdISBN());
        ps.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  @Override
  public void delete(Author author) {
    try {
      for (Book book : author.getBiblio()) {
        if (book.getListAuthor().size() == 1) {
          PreparedStatement ps = connection.prepareStatement("DELETE FROM books WHERE isbn = ?");
          ps.setInt(1, book.getIdISBN());
          ps.executeUpdate();
        }
      }
      PreparedStatement ps = connection.prepareStatement("DELETE FROM write WHERE author = ?");
      ps.setString(1, author.getName());
      ps.executeUpdate();
      ps = connection.prepareStatement("DELETE FROM authors WHERE name = ?");
      ps.setString(1, author.getName());
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
