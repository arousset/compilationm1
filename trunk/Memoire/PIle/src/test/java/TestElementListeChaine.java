
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
public class TestElementListeChaine extends TestCase {
    public void testGetVal(){
        Symbole s= new Symbole("nom", "valeur", "genre", "type", "conteneur","portee");
        ElementListeChaine e= new ElementListeChaine(s);

        Assert.assertEquals(e.getVal(),s);
    }


    public void testGetNext(){
     Symbole s= new Symbole("nom", "valeur", "genre", "type", "conteneur","portee");
     ElementListeChaine n= new ElementListeChaine(new Symbole("no", "valer", "gene", "tye", "contneur","potee"));
     ElementListeChaine p= new ElementListeChaine(new Symbole("n4o", "vale4r", "4gene", "t4ye", "4contneur","pot4ee"));

     ElementListeChaine e= new ElementListeChaine(s,p,n);

     Assert.assertEquals(e.getNext(), n);

    }
    public void testGetPrec(){
         Symbole s= new Symbole("nom", "valeur", "genre", "type", "conteneur","portee");
         ElementListeChaine n= new ElementListeChaine(new Symbole("no", "valer", "gene", "tye", "contneur","potee"));
         ElementListeChaine p= new ElementListeChaine(new Symbole("n4o", "vale4r", "4gene", "t4ye", "4contneur","pot4ee"));

         ElementListeChaine e= new ElementListeChaine(s,p,n);

         Assert.assertEquals(e.getPrec(), p);

    }

    public void testAdd(){
         Symbole s= new Symbole("nom", "valeur", "genre", "type", "conteneur","portee");
         ElementListeChaine n= new ElementListeChaine(new Symbole("no", "valer", "gene", "tye", "contneur","potee"));
         ElementListeChaine p= new ElementListeChaine(new Symbole("n4o", "vale4r", "4gene", "t4ye", "4contneur","pot4ee"));

         ElementListeChaine e= new ElementListeChaine(s,p,n);

         Symbole as= new Symbole("ndom", "vdaleur", "gednre", "typde", "codnteneur","portdee");

        e.add(as);
        Assert.assertEquals(e.recherche(as).getVal(), as);

    }

    public void testRecherche(){
         Symbole s= new Symbole("nom", "valeur", "genre", "type", "conteneur","portee");
         ElementListeChaine n= new ElementListeChaine(new Symbole("no", "valer", "gene", "tye", "contneur","potee"));
         ElementListeChaine p= new ElementListeChaine(new Symbole("n4o", "vale4r", "4gene", "t4ye", "4contneur","pot4ee"));
         ElementListeChaine e= new ElementListeChaine(s,p,n);

         Assert.assertEquals(e.recherche(s).getVal(),s);

    }
/*
    public void testDel(){
         Symbole s= new Symbole("nom", "valeur", "genre", "type", "conteneur","portee");
         ElementListeChaine n= new ElementListeChaine(new Symbole("no", "valer", "gene", "tye", "contneur","potee"));
         ElementListeChaine p= new ElementListeChaine(new Symbole("n4o", "vale4r", "4gene", "t4ye", "4contneur","pot4ee"));

         ElementListeChaine e= new ElementListeChaine(s,p,n);

         Symbole as= new Symbole("ndom", "vdaleur", "gednre", "typde", "codnteneur","portdee");

        e.add(as);
        e.add(s);
        Assert.assertEquals(e.recherche(s),"null");
    }*/
}
