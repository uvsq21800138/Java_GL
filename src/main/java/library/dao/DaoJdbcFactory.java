package library.dao;

import java.sql.Connection;
import library.Author;
import library.Book;

public class DaoJdbcFactory extends DaoAbstractFactory{
  private Connection connection;

  public DaoJdbcFactory(Connection connection)  {
    this.connection = connection;
  }


  @Override
  public Connection getConnection() {
    return connection;
  }

  @Override
  public Dao<Author> getAuthorDao() {
    return new AuthorDao(connection);
  }

  @Override
  public Dao<Book> getBookDao() {
    return new BookDao(connection);
  }
}
