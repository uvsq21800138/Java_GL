package library.dao;

import java.util.Optional;

/**
 * Interface DAO.
 *
 * @param <T> type d'objet
 */
public interface Dao<T> {

  boolean create(T objet);

  Optional<T> find(String identifier);

  boolean update(T objet);

  void delete(T objet);
}
