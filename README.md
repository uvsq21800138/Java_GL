# Java_GL
## Exercice de Java (Pattern etc)
>MIN17201 Programmation, Génie Logiciel et Preuve

### Enoncée mai 2021 :

#### Exercice 1 (Pattern Builder): 
Dans le package [book](./src/main/java/book) :
1. déclaration classe [Livre](./src/main/java/book/Livre.java)
1. constructeur [Livre](./src/main/java/book/Livre.java)
1. classe [LivreBuilder](./src/main/java/book/LivreBuilder.java)
1. test unitaire JUnit 4 montrant la création d'un livre : [lien](./src/test/java/book/LivreBuilderTest.java)
1. méthode de génération de la table des matières : [lien](./src/main/java/book/Livre.java)
1. test unitaire JUnit 4 montrant la méthode précédente : [lien](./src/test/java/book/LivreTest.java)

#### Exercice 2 (Pattern Composite):
Dans le package [doc](./src/main/java/doc)
1. un diagramme de classe UML (avec attributs et méthodes) présentant votre solution.
    
    Le diagramme type du pattern composite : 
    ![Pattern Composite](./uml/Pattern_Composite.png)
    
    Le diagramme de l'exercice (pas parfait mais général): 
    ![Diagramme de classe](./uml/Diagramme_de_classe-Document.png)

1. le code Java des classes [Section](./src/main/java/doc/Section.java) et [Paragraphe](./src/main/java/doc/Paragraphe.java)
1. le code Java des autres classes : [Element](./src/main/java/doc/Element.java) ; [Document](./src/main/java/doc/Document.java)
1. un test JUnit 4 vérifiant le calcul du nombre de caractères : [lien](./src/test/java/doc/DocumentTest.java)
1. un test JUnit 4 vérifiant le calcul du nombre de sections : [lien](./src/test/java/doc/DocumentTest.java)

#### Exercice 3 (Pattern Observateur):
Dans le package [system](./src/main/java/system)
1. un diagramme de classe UML (avec attributs et méthodes) présentant votre solution.
    
    Le diagramme type du pattern observateur : 
    ![Pattern Observateur](./uml/Pattern_Observer.png)
       
    Le diagramme de l'exercice (pas parfait mais général): 
    ![Diagramme de classe](./uml/Diagramme_de_classe-Systeme.png)

1. le code Java de la classe [Producteur](./src/main/java/system/Producteur.java)
1. le code Java de la classe [Consommateur](./src/main/java/system/Consommateur.java)
1. un test JUnit 4 illustrant un scénario : [lien](./src/test/java/system/ScenarioTest.java) 