package book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Classe du Pattern Builder pour la classe <code>Livre</code>. */
public class LivreBuilder {
  /** Titre du livre. */
  public String titre;
  /** Nom de l'auteur du livre. */
  public String auteur;
  /** Date de la parution du livre. Par défaut le 1er Janvier 1990. */
  public LocalDate dateParution;
  /** Liste des chapitres du livre. Au moins un chapitre. */
  public List<Chapitre> chapitreList;

  /**
   * Constructeur du livre par défaut .
   *
   * @param titre Titre du livre obligatoire.
   * @param auteur Auteur du livre obligatoire.
   * @param chap1 Premier chapitre obligatoire.
   */
  public LivreBuilder(String titre, String auteur, Chapitre chap1) {
    /* Les paramètres ne peuvent être nuls. */
    if (titre == null | auteur == null | chap1 == null) {
      throw new NullPointerException();
    }

    /* Attributs obligatoires */
    this.titre = titre;
    this.auteur = auteur;
    this.chapitreList = new ArrayList<>(); // permet l'utilisation de fonction add() et contains()
    this.chapitreList.add(chap1);

    /* Attribut optionnel */
    this.dateParution = LocalDate.of(1900, 1, 1);
  }

  /**
   * Méthode d'attribution de la date de parution du livre.
   *
   * @param date Date de parution du livre
   * @return l'instance de <code>LivreBuilder</code>
   */
  public LivreBuilder setDateParution(LocalDate date) {
    /* Le paramètre ne peut être nul. */
    if (date == null) {
      throw new NullPointerException();
    } else {
      this.dateParution = date;
    }
    return this; // Très important !!
  }

  /**
   * Méthode d'ajout d'un chapitre au livre.
   *
   * @param chap Chapitre suivant du livre
   * @return l'instance de <code>LivreBuilder</code>
   */
  public LivreBuilder addChapitre(Chapitre chap) {
    /* Le paramètre ne peut être nul. */
    if (chap == null) {
      throw new NullPointerException();
    } else {
      /* Test si le chapitre n'est pas déjà présent dans le livre */
      if (!chapitreList.contains(chap)) this.chapitreList.add(chap);
    }
    return this; // Très important !!
  }

  /**
   * Méthode fermant la construction avec le pattern Builder.
   *
   * @return l'instance de <code>Livre</code> construite.
   */
  public Livre build() {
    return new Livre(this);
  }
}
