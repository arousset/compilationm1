
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
    

}
