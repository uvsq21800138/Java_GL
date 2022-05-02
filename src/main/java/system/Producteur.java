package system;

import java.util.ArrayList;
import java.util.List;

public class Producteur implements Sujet {
  private String name;
  private Integer stockProduit;
  private Double prix;
  private List<Observateur> listConsom;

  public Producteur(String name, double prix, int stock) {
    this.name = name;
    this.prix = prix;
    this.stockProduit = stock;
    this.listConsom = new ArrayList<Observateur>();
  }

  public String getName() {
    return name;
  }

  public Integer getStock() {
    return stockProduit;
  }

  public Double getPrix() {
    return prix;
  }

  /**
   * Produit aléatoirement entre 1 et 100 produits.
   *
   * <p>Utilise Math.random().
   */
  public void produire() {
    double create = 1 + (Math.random() * 99);
    stockProduit = stockProduit + (int) create;
    notifier();
  }

  /**
   * Vend des produits à un Consommateur selon les stocks.
   *
   * @param nb nombre demandé par le consommateur
   * @return nombre réellement vendu
   */
  public int vendre(int nb) {
    int really = 0;
    if (stockProduit >= nb) {
      really = nb;
      stockProduit -= nb;
    } else {
      really = stockProduit;
      stockProduit = 0;
    }
    //notifier();
    return really;
  }

  /**
   * Inscrit un Observateur s'il n'est pas abonné.
   *
   * @param obs un observateur (consommateur)
   */
  @Override
  public void inscrire(Observateur obs) {
    if (!listConsom.contains(obs)) {
      listConsom.add(obs);
    }
  }

  /**
   * Désinscrit un Observateur s'il est abonné.
   *
   * @param obs un observateur (consommateur)
   */
  @Override
  public void desinscrire(Observateur obs) {
    if (listConsom.contains(obs)) {
      listConsom.remove(obs);
    }
  }

  /**
   * Actualise le sujet pour tous les abonnés.
   */
  @Override
  public void notifier() {
    for (int i = 0; i < listConsom.size(); i++) {
      Observateur o = listConsom.get(i);
      o.actualiser(this);
    }
  }
}
