
import junit.framework.Assert;
import junit.framework.TestCase;



/**
 *
 * @author anais
 */
public class TestTableSymboles extends TestCase {
/*
    public void testExist(){
        TableSymbole ts = new TableSymbole();
        Symbole sy= new Symbole("nom", "valeur","genre","type", "conteneur", "portee");
        ts.add(sy);
        Assert.assertTrue(ts.exist(sy));
    }
*/
   public void testDel(){
        TableSymbole ts = new TableSymbole();
        Symbole sy= new Symbole("nom", "valeur","genre","type", "conteneur", "portee");
        ts.add(sy);
        ts.del(sy);
        Assert.assertTrue(ts.exist(sy));
   }
}
