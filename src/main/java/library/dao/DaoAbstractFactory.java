package library.dao;

import library.Author;
import library.Book;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Fabrique abstraite de DAO.
 */
public abstract class DaoAbstractFactory {

  /** Enumeration des types de DAO. */
  public enum DaoType {
    JDBC
  }

  /** Structure Singleton des paramètres de connexion DB. */
  public enum DbUrl {
    MySql("jdbc:mysql://localhost/exam", "user", "passwd");
    private final String url;
    private final String user;
    private final String pw;

    DbUrl(String url, String user, String pw) {
      this.url = url;
      this.user = user;
      this.pw = pw;
    }

    public String getUrl() {
      return url;
    }

    public String getUser() {
      return user;
    }

    public String getPw() {
      return pw;
    }
  }

  /**
   * Renvoie la fabrique selon le type.
   *
   * @return la factory selon le type dans le properties
   * @throws Exception si une erreur en lecture properties
   */
  public static DaoAbstractFactory getFactory(DaoType type, DbUrl dbUrl) throws Exception {
    if (DaoType.JDBC == type) {
      Connection connection = DriverManager.getConnection(dbUrl.getUrl(),dbUrl.getUser(),dbUrl.getPw());
      return new DaoJdbcFactory(connection);
    }
    return null;
  }

  public abstract Connection getConnection();

  public abstract Dao<Author> getAuthorDao();
  public abstract Dao<Book> getBookDao();
}

