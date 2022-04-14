package book;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import org.junit.Test;

public class LivreTest {
  @Test
  public void testTableMatieres() {
    Chapitre chap1 =
        new Chapitre(
            "Le Survivant",
            "Mr et Mrs Dursley, qui habitaient au 4, Privet Drive, avaient toujours affirmé "
                + "avec la plus grande fierté qu'ils étaient parfaitement normaux, merci pour eux.");
    Chapitre chap2 =
        new Chapitre(
            "Une vitre disparait",
            "Il s'était passé près de dix ans depuis que les Dursley avaient trouvé au saut "
                + "du lit leur neveu devant la porte, mais Privet Drive n'avait quasiment pas changé.");
    Chapitre chap3 =
        new Chapitre(
            "Les lettres de nulle part",
            "La fuite du boa brésilien valut à Harry la plus longue punition qu'il eût jamais reçue.");
    Chapitre chap4 =
        new Chapitre(
            "Le gardien des clefs",
            "BOUM !BOUM ! On frappa à nouveau. Dudley se réveilla en sursaut.");
    Chapitre chap5 =
        new Chapitre(
            "Le chemin de traverse",
            "Harry se réveilla de bonne heure le lendemain matin. "
                + "Il savait qu'il faisait jour, mais il garda les yeux fermés.");
    Livre HarryPotter =
        new LivreBuilder("Harry Potter à l'école des sorciers", "J. K. Rowling", chap1)
            .setDateParution(LocalDate.of(1997, 6, 26))
            .addChapitre(chap2)
            .addChapitre(chap3)
            .addChapitre(chap4)
            .addChapitre(chap5)
            .build();
    assertEquals(
        "Harry Potter à l'école des sorciers("
            + "1. Le Survivant, "
            + "2. Une vitre disparait, "
            + "3. Les lettres de nulle part, "
            + "4. Le gardien des clefs, "
            + "5. Le chemin de traverse)",
        HarryPotter.getTableMatieres());
  }
}
