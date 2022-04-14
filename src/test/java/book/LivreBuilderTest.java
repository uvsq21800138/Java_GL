package book;

import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LivreBuilderTest {

  /** Test la construction simple de <code>LivreBuilder</code>. */
  @Test
  public void testCreationLivreDefaut() {
    Chapitre chap1 =
        new Chapitre(
            "PREMIER CHAPITRE",
            "Lorsque j’avais six ans j’ai vu, une fois, une magnifique image,"
                + " dans un livre sur la Forêt Vierge qui s’appelait « Histoires Vécues ».");
    Livre PetitPrince = new LivreBuilder("Le Petit Prince", "Antoine de St-Exupéry", chap1).build();
    assertEquals("Le Petit Prince", PetitPrince.getTitre());
    assertEquals("Antoine de St-Exupéry", PetitPrince.getAuteur());
    assertEquals(LocalDate.of(1900, 1, 1), PetitPrince.getDateParution());
    assertEquals(1, PetitPrince.getChapitreList().size());
  }

  /** Test TOUTES les possibilités de construction de <code>LivreBuilder</code>. */
  @Test
  public void testCreationLivreComplet() {
    Chapitre chap1 =
        new Chapitre(
            "PREMIER CHAPITRE",
            "Lorsque j’avais six ans j’ai vu, une fois, une magnifique image,"
                + " dans un livre sur la Forêt Vierge qui s’appelait « Histoires Vécues ».");
    Chapitre chap2 =
        new Chapitre(
            "CHAPITRE II",
            "J’ai ainsi vécu seul, sans personne avec qui parler véritablement,"
                + " jusqu’à une panne dans le désert du Sahara, il y a six ans.");
    Livre PetitPrince =
        new LivreBuilder("Le Petit Prince", "Antoine de St-Exupéry", chap1)
            .setDateParution(LocalDate.of(1943, 4, 6))
            .addChapitre(chap2)
            .build();
    assertEquals("Le Petit Prince", PetitPrince.getTitre());
    assertEquals("Antoine de St-Exupéry", PetitPrince.getAuteur());
    assertEquals(LocalDate.of(1943, 4, 6), PetitPrince.getDateParution());
    assertEquals(2, PetitPrince.getChapitreList().size());
  }
}
