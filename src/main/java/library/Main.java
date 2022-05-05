package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import library.dao.Dao;
import library.dao.DaoAbstractFactory;
import library.dao.DaoAbstractFactory.DaoType;
import library.dao.DaoAbstractFactory.DbUrl;

public class Main {

  public static void question8() throws Exception {
    Author jkr = new Author("JK. Rowling", "Jk.rowling@gmail.com");
    Author tolkien = new Author("JRR. Tolkien", "Jrr.tolkien@gmail.com");
    Book potter = new Book("Harry Potter", 207054127, jkr);
    Book hobbit = new Book("Hobbit", 225318382, tolkien);
    /* Ajoute tolkien au auteurs d'Harry Potter */
    potter.addAuthor(tolkien);
    tolkien.addBook(potter);
    /* creation de la factory selon les critères de connection */
    DaoAbstractFactory factory = DaoAbstractFactory.getFactory(DaoType.JDBC, DbUrl.MySql);
    Connection connection = factory.getConnection();
    /* Interface statement pour les requêtes de création des tables de la bd */
    Statement statement = connection.createStatement();
    statement.execute(
        "CREATE TABLE authors(name VARCHAR(40), email VARCHAR(64), PRIMARY KEY (name))");
    statement.execute(
        "CREATE TABLE books(isbn int, title VARCHAR(40), PRIMARY KEY (isbn) )");
    statement.execute(
        "CREATE TABLE write(author VARCHAR(40), book VARCHAR(40), PRIMARY KEY (author, book), FOREIGN KEY (author) REFERENCES authors(name), FOREIGN KEY (book) REFERENCES books(isbn) )");
    statement.close();

    /* Création des auteurs avec AuthorDao */
    Dao<Author> authorDao = factory.getAuthorDao();
    authorDao.create(jkr);
    authorDao.create(tolkien);
    /* Création des livres avec BookDao */
    Dao<Book> bookDao = factory.getBookDao();
    bookDao.create(potter);
    bookDao.create(hobbit);

    /* Ne pas oublier de fermer la connection lors des exécutions */
    connection.close();
  }

  public static void question9() throws Exception {
    /* creation de la factory selon les critères de connection */
    DaoAbstractFactory factory = DaoAbstractFactory.getFactory(DaoType.JDBC, DbUrl.MySql);
    Connection connection = factory.getConnection();

    /* La base de données est déjà créé et remplie donc maintenant on recherche 1 élément */

    /* Recherche de JK. Rowling */
    Dao<Author> authorDao = factory.getAuthorDao();
    Optional<Author> res1 = authorDao.find("JK. Rowling");
    if(res1.isEmpty())  {
      System.out.println("Pas de JK. Rowling dans la BD.");
    } else {
      Author jkr = res1.get();
      /* Complète avec les autres auteurs si il y en a? */
      Dao<Book> bookDao = factory.getBookDao();
      for (Book b : jkr.getBiblio()) {
        Optional<Book> res2 = bookDao.find(b.getTitle());
        if (res2.isEmpty()) {
          System.out.println("Pas de "+b.getTitle()+" dans la BD.");
        } else {
          // remplace le livre par son occurrence plus complète (avec auteurs)
          b = res2.get();
        }
      }
    }
    /* Affiche l'auteur avec sa bibliographie complète (noms des autres auteurs inclus) */
    System.out.println(res1.toString());
    /* Ne pas oublier de fermer la connection lors des exécutions */
    connection.close();
  }
}
