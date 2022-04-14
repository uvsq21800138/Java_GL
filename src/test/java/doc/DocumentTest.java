package doc;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DocumentTest {
  Document HarryPotter;
  int nbChar;
  int nbSection;

  @Before
  public void setup() {
    nbChar = 0;
    nbSection = 0;
    String p11 = "Mr et Mrs Dursley, qui habitaient au 4, Privet Drive, ";
    nbChar += p11.length();
    String p12 = "avaient toujours affirmé ";
    nbChar += p12.length();
    String p13 = "avec la plus grande fierté qu'ils étaient ";
    nbChar += p13.length();
    String p14 = "parfaitement normaux, merci pour eux.";
    nbChar += p14.length();
    String p15 = "Jamais quiconque n'aurait imaginé qu'ils puissent se trouver";
    nbChar += p15.length();
    String p16 = " impliqués dans quoi que ce soit d'étrange ou de mystérieux.";
    nbChar += p16.length();
    String p17 = "Ils n'avaient pas de temps à perdre avec des sornettes.";
    nbChar += p17.length();
    Section s11 = new Section("");
    nbSection += 1;
    s11.addElem(new Paragraphe(p11));
    s11.addElem(new Paragraphe(p12));
    s11.addElem(new Paragraphe(p13));
    s11.addElem(new Paragraphe(p14));
    Section s12 = new Section("");
    nbSection += 1;
    s12.addElem(new Paragraphe(p15));
    s12.addElem(new Paragraphe(p16));
    s12.addElem(new Paragraphe(p17));
    Section chap1 = new Section("Le Survivant");
    nbSection += 1; nbChar += chap1.getTitre().length();
    chap1.addElem(s11);
    chap1.addElem(s12);
    String p21 = "Il s'était passé près de dix ans depuis que les Dursley ";
    nbChar += p21.length();
    String p22 = "avaient trouvé au saut du lit leur neveu devant la porte, ";
    nbChar += p22.length();
    String p23 = "mais Privet Drive n'avait quasiment pas changé.";
    nbChar += p23.length();
    Section chap2 = new Section("Une vitre disparait");
    nbSection += 1; nbChar += chap2.getTitre().length();
    chap2.addElem(new Paragraphe(p21));
    chap2.addElem(new Paragraphe(p22));
    chap2.addElem(new Paragraphe(p23));
    String p31 = "La fuite du boa brésilien valut à Harry ";
    nbChar += p31.length();
    String p32 = "la plus longue punition qu'il eût jamais reçue.";
    nbChar += p32.length();
    Section chap3 = new Section("Les lettres de nulle part");
    nbSection += 1; nbChar += chap3.getTitre().length();
    chap3.addElem(new Paragraphe(p31));
    chap3.addElem(new Paragraphe(p32));
    HarryPotter = new Document("Harry Potter à l'école des sorciers", "J. K. Rowling");
    HarryPotter.addSection(chap1);
    HarryPotter.addSection(chap2);
    HarryPotter.addSection(chap3);
  }

  @Test
  public void nombreCaracteresTest() {
    assertEquals(nbChar, HarryPotter.nbChar());
  }

  @Test
  public void nombreSectionsTest() {
    assertEquals(nbSection, HarryPotter.nbSection());
  }
}
