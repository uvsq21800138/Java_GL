package system;

public interface Sujet {
  public void inscrire(Observateur obs);
  public void desinscrire(Observateur obs);
  public void notifier();
}
