package system;

import java.util.ArrayList;
import java.util.List;

public class Consommateur implements Observateur {
  private String name;
  private Double monnaie;
  private List<Producteur> listProducteur;

  public Consommateur(String name) {
    this.name = name;
    this.monnaie = 0.0;
    this.listProducteur = new ArrayList<>();
  }

  public Double getMonnaie() {
    return monnaie;
  }

  /**
   * Ajoute de la monnaie.
   *
   * @param monnaie ajoute si positif
   */
  public void addMonnaie(Double monnaie) {
    if (monnaie > 0.0) this.monnaie += monnaie;
    else System.out.println("Gain forcément positif.");
  }

  /**
   * Affiche le message du producteur (sujet).
   *
   * @param sujet le sujet devant être un producteur
   */
  @Override
  public void actualiser(Sujet sujet) {
    Producteur p = (Producteur) sujet;
    System.out.println(name+" : Stock de " + p.getName() + " = " + p.getStock().toString());
  }

  /**
   * Acheter des produits à un producteur.
   *
   * @param p1 le producteur
   * @param nb le nombre souhaité
   */
  public void acheter(Producteur p1, int nb) {
    actualiser(p1);
    int tmp = nb;
    // si le nombre de produit est null ou négatif
    if (tmp < 1) {
      tmp = 1;
    }
    // récupère le prix du producteur
    double prix = p1.getPrix();
    // si manque de monnaie
    if (this.monnaie < tmp * prix) {
      tmp = (int) (this.monnaie / prix);
    }
    tmp = p1.vendre(tmp);
    this.monnaie -= tmp * prix;
    actualiser(p1);
  }

  /**
   * Ajoute un producteur à la chaine de notification.
   *
   * @param p producteur
   */
  public void sinscrire(Producteur p) {
    if (!listProducteur.contains(p)) {
      listProducteur.add(p);
    }
    p.inscrire(this);
  }

  /**
   * Retire un producteur à la chaine de notification.
   *
   * @param p producteur
   */
  public void sedesinscrire(Producteur p) {
    if (listProducteur.contains(p)) {
      listProducteur.remove(p);
    }
    p.desinscrire(this);
  }
}
