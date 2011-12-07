
import junit.framework.Assert;
import junit.framework.TestCase;


/**
 *
 * @author anais
 */
public class TestPile extends TestCase {
    public void testSwap(){
        Pile p = new Pile();
BooleenPile b=new BooleenPile("a", new BooleanValue("true"), new GenreVar(), new TypeBoolean(), "a");

    p.getPile().push(b);
    p.getPile().push(new BooleenPile("b", new BooleanValue("true"), new GenreVar(), new TypeBoolean(), "b"));
    p.swap();
    Assert.assertTrue(p.getPile().peek().equals(b));
   
    }
    
    public void testSearchident(){
        Pile p = new Pile();
        BooleenPile b=new BooleenPile("a", new BooleanValue("true"), new GenreVar(), new TypeBoolean(), "a");

        p.getPile().push(b);
        p.getPile().push(new BooleenPile("b", new BooleanValue("true"), new GenreVar(), new TypeBoolean(), "b"));

        Assert.assertEquals(p.searchident("a"),b);
    }
    
     public void testEstPresent(){
        Pile p = new Pile();
        BooleenPile b=new BooleenPile("a", new BooleanValue("true"), new GenreVar(), new TypeBoolean(), "a");

        p.getPile().push(b);
        p.getPile().push(new BooleenPile("b", new BooleanValue("true"), new GenreVar(), new TypeBoolean(), "b"));

        Assert.assertEquals(p.estpresent(b.quadruplet.getIdent()),true);
    }

     public void testSupprimer(){
         Pile p = new Pile();
         BooleenPile b=new BooleenPile("a", new BooleanValue("true"), new GenreVar(), new TypeBoolean(), "a");
         Tas_Tas tas= new Tas_Tas();
          TabPile t=new TabPile("s",new TabValue(2),new GenreTab(),new TypeBoolean(), "2" );

          int c=tas.Tas_allouerTableau(t.quadruplet.getIdent(), "bool", 20);
        for(int i=0;i<20;i++){
            tas.Tas_affecterValeur(c, i, i);
        }
          p.supprimer(t.quadruplet.getIdent(), tas);
          Assert.assertFalse(p.estpresent(t.quadruplet.getIdent()));

     }
}
