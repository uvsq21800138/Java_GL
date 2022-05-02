package system;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScenarioTest {
  Consommateur c1;
  Consommateur c2;
  Consommateur c3;
  Producteur p1;

  @Before
  public void setup() {
    // Création de consommateurs
    c1 = new Consommateur("Arthur");
    c2 = new Consommateur("Ben");
    c3 = new Consommateur("Claire");
    // Création de producteur
    p1 = new Producteur("T-shirt", 5.99, 10);
    // Ajout de monnaie aux consommateurs
    c1.addMonnaie(50.0);
    c2.addMonnaie(10.0);
    c3.addMonnaie(20.0);
    // Inscription
    c1.sinscrire(p1);
    c2.sinscrire(p1);
  }

  @Test
  public void scenario1test() {
    int etatP1 = p1.getStock();
    double etatC1 = c1.getMonnaie();
    double etatC2 = c2.getMonnaie();
    double etatC3 = c3.getMonnaie();
    // Vente au dessus des moyens du consommateur
    c2.acheter(p1, 2); //vend réellement 1 produit
    assertEquals(Integer.valueOf(etatP1 - 1), p1.getStock());
    assertEquals(Double.valueOf(etatC2 - 5.99), c2.getMonnaie());
    etatP1 = p1.getStock();
    // Vente sans être abonné
    c3.acheter(p1,3); //vend réellement 3 produits
    assertEquals(Integer.valueOf(etatP1 - 3), p1.getStock());
    assertEquals(Double.valueOf(etatC3 - 3 * 5.99), c3.getMonnaie());
    etatP1 = p1.getStock();
    // Vente au-dessus des stock du producteur
    c1.acheter(p1, 8); //vend réellement 6 produits
    assertEquals(Integer.valueOf(etatP1 - 6), p1.getStock()); // égale à 0
    assertEquals(Double.valueOf(etatC1 - 6 * 5.99), c1.getMonnaie());
    etatP1 = p1.getStock();
    // Production aléatoire
    p1.produire();
    assertTrue(etatP1 < p1.getStock());
  }
}
