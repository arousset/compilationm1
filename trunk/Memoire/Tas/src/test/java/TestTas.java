
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

    public void testVerifCaseLibre(){
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

        Assert.assertFalse(tas.Tas_verifCaseLibre(b));
    }

    public void testAffecterValeur(){
         Tas_Tas tas = new Tas_Tas();
         Object[] c={1,2},d={3,4,4};
        int a = tas.Tas_allouerTableau("c", "entier", 2);
        for(int i=0;i<2;i++){
            tas.Tas_affecterValeur(a, i, c[i]);
        }

        Assert.assertEquals(tas.Tas_recupValeur(a,1),c[1]);
    }

    public void testAllouerTableau(){
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
        Assert.assertFalse(a== b);
    }

       public void testGarbageCollector(){
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
        tas.Tas_supprimerTableau(a);
        int  avant=tas.Tas_verifEspaceLibre();
        tas.Tas_garbageCollector();
        int apres=tas.Tas_verifEspaceLibre();

        Assert.assertTrue(avant==apres);
       }

       public void testDump(){
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
            tas.Tas_dump();
            Assert.assertTrue(tas.tas==null);
       }

       public void testSubdivisionEspacesLibres(){
           Tas_Tas tas = new Tas_Tas();
           tas.Tas_garbageCollector();
           tas.Tas_subdivisionEspacesLibres(tas.tailleTas, tas.espaceLibre, false);
           int n=tas.Tas_pouissance(tas.tailleTas, false);
           Assert.assertTrue(tas.getEspacesVides().size()== n);
       }
}
