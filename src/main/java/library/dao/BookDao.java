package library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import library.Author;
import library.Book;

public class BookDao implements Dao<Book> {
  private final Connection connection;

  public BookDao(Connection connection) {
    this.connection = connection;
  }

  @Override
  public boolean create(Book book) {
    try {
      PreparedStatement psInsert = connection.prepareStatement("INSERT INTO books VALUES(?, ?)");
      psInsert.setString(1, book.getTitle());
      psInsert.setInt(2, book.getIdISBN());
      psInsert.executeUpdate();

      psInsert = connection.prepareStatement("INSERT INTO authors VALUES(?, ?)");
      for (Author author : book.getListAuthor()) {
        psInsert.setString(1, author.getName());
        psInsert.setString(2, author.getEmail());
        psInsert.executeUpdate();
      }

      psInsert = connection.prepareStatement("INSERT INTO write VALUES(?, ?)");
      for (Author author : book.getListAuthor()) {
        psInsert.setString(1, author.getName());
        psInsert.setString(2, book.getTitle());
        psInsert.executeUpdate();
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Lecture de la BD uniquement au premier niveau.
   *
   * <p>L'auteur et ses livres avec leurs auteurs (seulement les noms).
   *
   * @param identifier nom de l'auteur
   * @return l'auteur créé avec les livres
   */
  @Override
  public Optional<Book> find(String identifier) {
    Author author = null;
    Book book = null;
    try {
      PreparedStatement psInsert =
          connection.prepareStatement("SELECT * FROM books WHERE title = ?");
      psInsert.setString(1, identifier);
      ResultSet rs = psInsert.executeQuery();
      if (rs.next()) {
        book = new Book(rs.getString(1), rs.getInt(2));

        psInsert = connection.prepareStatement("SELECT * FROM write WHERE book = ?");
        psInsert.setString(1, book.getTitle());
        rs = psInsert.executeQuery();
        while (rs.next()) {
          psInsert = connection.prepareStatement("SELECT * FROM authors WHERE name = ?");
          psInsert.setString(1, rs.getString(1));
          rs = psInsert.executeQuery();
          if (rs.next()) {
            author = new Author(rs.getString(1), rs.getString(2));

            psInsert = connection.prepareStatement("SELECT * FROM write WHERE author = ?");
            psInsert.setString(1, author.getName());
            rs = psInsert.executeQuery();
            while (rs.next()) {
              psInsert = connection.prepareStatement("SELECT * FROM books WHERE title = ?");
              rs = psInsert.executeQuery();
              author.addBook(new Book(rs.getString(1), rs.getInt(2)));
            }
          }
          book.addAuthor(author);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Optional.empty();
    }
    return Optional.ofNullable(book);
  }

  @Override
  public boolean update(Book book) {
    try {
      PreparedStatement ps =
          connection.prepareStatement("UPDATE books SET isbn = ? WHERE title = ?");
      ps.setInt(1, book.getIdISBN());
      ps.setString(2, book.getTitle());
      ps.executeUpdate();

      ps = connection.prepareStatement("DELETE FROM write WHERE author = ?");
      ps.setString(1, book.getTitle());
      ps.executeUpdate();

      ps = connection.prepareStatement("INSERT INTO authors VALUES(?, ?)");
      for (Author author : book.getListAuthor()) {
        ps.setString(1, author.getName());
        ps.setString(2, author.getEmail());
        ps.executeUpdate();
      }

      ps = connection.prepareStatement("INSERT INTO write VALUES(?, ?)");
      for (Author author : book.getListAuthor()) {
        ps.setString(1, author.getName());
        ps.setString(2, book.getTitle());
        ps.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  @Override
  public void delete(Book book) {
    try {
      for (Author author : book.getListAuthor()) {
        if (author.getBiblio().size() == 1) {
          PreparedStatement ps = connection.prepareStatement("DELETE FROM authors WHERE name = ?");
          ps.setString(1, author.getName());
          ps.executeUpdate();
        }
      }
      PreparedStatement ps = connection.prepareStatement("DELETE FROM write WHERE book = ?");
      ps.setString(1, book.getTitle());
      ps.executeUpdate();

      ps = connection.prepareStatement("DELETE FROM books WHERE title = ?");
      ps.setString(1, book.getTitle());
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
