
import junit.framework.Assert;
import junit.framework.TestCase;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anais
 */
public class TestSymbole extends TestCase{

    public void testCompNom(){
        Symbole s = new Symbole("nom", "valeur", "genre", "type", "conteneur","portee");
       Assert.assertTrue(s.compNom("nom"));
    }

     public void testCompVal(){
        Symbole s = new Symbole("nom", "valeur", "genre", "type", "conteneur","portee");
       Assert.assertTrue(s.compVal("valeur"));
    }

      public void testCompGenre(){
        Symbole s = new Symbole("nom", "valeur", "genre", "type", "conteneur","portee");
       Assert.assertTrue(s.compGenre("genre"));
    }

       public void testCompType(){
        Symbole s = new Symbole("nom", "valeur", "genre", "type", "conteneur","portee");
       Assert.assertTrue(s.compType("type"));
    }

        public void testCompPortee(){
        Symbole s = new Symbole("nom", "valeur", "genre", "type", "conteneur","portee");
       Assert.assertTrue(s.compPortee("portee"));
    }

       public void testCompConteneur(){
        Symbole s = new Symbole("nom", "valeur", "genre", "type", "conteneur","portee");
       Assert.assertTrue(s.compConteneur("conteneur"));
    }
}
