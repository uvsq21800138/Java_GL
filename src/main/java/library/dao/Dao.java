package library.dao;

import java.util.Optional;

/**
 * Interface DAO.
 *
 * @param <T> type d'objet
 */
public abstract class Dao<T> {

  public abstract boolean create(T objet);

  public abstract Optional<T> find(String identifier);

  public abstract boolean update(T objet);

  public abstract void delete(T objet);
}
