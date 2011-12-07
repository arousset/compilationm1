
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
        Object[] c={1,2},d={3,4,4};
        int a = tas.Tas_allouerTableau("c", "entier", 2);
        for(int i=0;i<2;i++){
            tas.Tas_affecterValeur(a, i, c[i]);
        }

        int b = tas.Tas_allouerTableau("d", "entier", 3);
        for(int i=0;i<3;i++){
            tas.Tas_affecterValeur(b, i, d[i]);
        }
        Assert.assertFalse(tas.Tas_verifEspaceLibre()==tas.tailleTas);
    }
}
