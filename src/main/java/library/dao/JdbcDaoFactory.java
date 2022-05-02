package library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** La classe <code>JdbcDaoFactory</code> permet de créer des DAO basés sur JDBC. */
public class JdbcDaoFactory {

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

  private final Connection connection;

  /** Constructeur. */
  public JdbcDaoFactory(Connection connection) throws SQLException {
    this.connection =
        DriverManager.getConnection(
            DbUrl.MySql.getUrl(), DbUrl.MySql.getUser(), DbUrl.MySql.getPw());
  }

  public AuthorDao getAuthorDao() {
    return new AuthorDao(connection);
  }
}
