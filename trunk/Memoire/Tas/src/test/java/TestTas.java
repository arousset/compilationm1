
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
public class TestTas extends TestCase{

    public void testVerifEspaceLibre(){
        Tas_Tas tas = new Tas_Tas();
        int[] c={},d={};
        int a = tas.Tas_allouerTableau("c", "entier", 20);
        for(int i=0;i<20;i++){
            c[i]=3*i;
            tas.Tas_affecterValeur(a, i, c[i]);
        }

        int b = tas.Tas_allouerTableau("d", "entier", 40);
        for(int i=0;i<40;i++){
            d[i]=7*i;
            tas.Tas_affecterValeur(b, i, d[i]);
        }
        Assert.assertFalse(tas.Tas_verifEspaceLibre()==tas.tailleTas);
    }
}
