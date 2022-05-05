package library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import library.Author;
import library.Book;

public class BookDao extends Dao<Book> {
  private final Connection connection;

  public BookDao(Connection connection) {
    this.connection = connection;
  }

  @Override
  public boolean create(Book book) {
    try {
      PreparedStatement psInsert = connection.prepareStatement("INSERT INTO books VALUES(?, ?)");
      psInsert.setInt(1, book.getIdISBN());
      psInsert.setString(2, book.getTitle());
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
   * Lecture de la BD uniquement pour les relation direct au livre.
   *
   * <p>Le livre avec son ou ses auteurs (sans leur bibliographie).
   *
   * @param identifier id ISBN de l'auteur sous forme de String
   * @return l'auteur créé avec les livres
   */
  @Override
  public Optional<Book> find(String identifier) {
    Author author = null;
    Book book = null;
    List<Author> tmp = new ArrayList<>();
    try {
      /* Recherche le livre par son titre */
      PreparedStatement psInsert =
          connection.prepareStatement("SELECT * FROM books WHERE isbn = ?");
      psInsert.setInt(1, Integer.valueOf(identifier));
      ResultSet rs1 = psInsert.executeQuery();
      if (rs1.next()) {
        /* Recherche les auteurs associés au livre */
        psInsert = connection.prepareStatement("SELECT * FROM write WHERE book = ?");
        psInsert.setInt(1, rs1.getInt(1));
        ResultSet rs2 = psInsert.executeQuery();
        while (rs2.next()) {
          /* Pour chaque auteur, recherche email */
          psInsert = connection.prepareStatement("SELECT * FROM authors WHERE name = ?");
          psInsert.setString(1, rs2.getString(1));
          ResultSet rs3 = psInsert.executeQuery();
          if (rs3.next()) {
            author = new Author(rs3.getString(1), rs3.getString(2));
          }
          /* conserve temporairement les auteur en liste */
          tmp.add(author);
        }
        /* Créé l'instance du livre */
        book = new Book(rs1.getString(2), rs1.getInt(1), tmp.get(0));
        for (int i = 1; i < tmp.size(); i++) {
          book.addAuthor(tmp.get(i));
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
          connection.prepareStatement("UPDATE books SET title = ? WHERE isbn = ?");
      ps.setString(1, book.getTitle());
      ps.setInt(2, book.getIdISBN());
      ps.executeUpdate();

      ps = connection.prepareStatement("DELETE FROM write WHERE book = ?");
      ps.setInt(1, book.getIdISBN());
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
      ps.setInt(1, book.getIdISBN());
      ps.executeUpdate();

      ps = connection.prepareStatement("DELETE FROM books WHERE isbn = ?");
      ps.setInt(1, book.getIdISBN());
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
