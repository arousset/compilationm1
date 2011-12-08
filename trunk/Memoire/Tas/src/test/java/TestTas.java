
import java.util.logging.Level;
import java.util.logging.Logger;
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
        try {
            Tas_Tas tas = new Tas_Tas();
            Object[] c = {1, 2};
            Object[] d = {3, 4, 4};
            int a = tas.Tas_allouerTableau("c", "entier", 2);
            for (int i = 0; i < 2; i++) {
                tas.Tas_affecterValeur(a, i, c[i]);
            }
            int b = tas.Tas_allouerTableau("d", "entier", 3);
            for (int i = 0; i < 3; i++) {
                tas.Tas_affecterValeur(b, i, d[i]);
            }
            Assert.assertFalse(tas.Tas_verifEspaceLibre() == tas.tailleTas);
        } catch (Tas_ExceptionEspaceDispo ex) {
           }
    }

    public void testVerifCaseLibre(){
        try {
            Tas_Tas tas = new Tas_Tas();
            Object[] c = {1, 2};
            Object[] d = {3, 4, 4};
            int a = tas.Tas_allouerTableau("c", "entier", 2);
            for (int i = 0; i < 2; i++) {
                tas.Tas_affecterValeur(a, i, c[i]);
            }
            int b = tas.Tas_allouerTableau("d", "entier", 3);
            for (int i = 0; i < 3; i++) {
                tas.Tas_affecterValeur(b, i, d[i]);
            }
            Assert.assertFalse(tas.Tas_verifCaseLibre(b));
        } catch (Tas_ExceptionEspaceDispo ex) {
            }
    }

    public void testAffecterValeur(){
        try {
            Tas_Tas tas = new Tas_Tas();
            Object[] c = {1, 2};
            Object[] d = {3, 4, 4};
            int a = tas.Tas_allouerTableau("c", "entier", 2);
            for (int i = 0; i < 2; i++) {
                tas.Tas_affecterValeur(a, i, c[i]);
            }
            Assert.assertEquals(tas.Tas_recupValeur(a, 1), c[1]);
        } catch (Tas_ExceptionEspaceDispo ex) {
             }
    }

    public void testAllouerTableau(){
        try {
            Tas_Tas tas = new Tas_Tas();
            Object[] c = {1, 2};
            Object[] d = {3, 4, 4};
            int a = tas.Tas_allouerTableau("c", "entier", 2);
            for (int i = 0; i < 2; i++) {
                tas.Tas_affecterValeur(a, i, c[i]);
            }
            int b = tas.Tas_allouerTableau("d", "entier", 3);
            for (int i = 0; i < 3; i++) {
                tas.Tas_affecterValeur(b, i, d[i]);
            }
            Assert.assertFalse(a == b);
        } catch (Tas_ExceptionEspaceDispo ex) {
           }
    }

       public void testGarbageCollector(){
        try {
            Tas_Tas tas = new Tas_Tas();
            Object[] c = {1, 2};
            Object[] d = {3, 4, 4};
            int a = tas.Tas_allouerTableau("c", "entier", 2);
            for (int i = 0; i < 2; i++) {
                tas.Tas_affecterValeur(a, i, c[i]);
            }
            int b = tas.Tas_allouerTableau("d", "entier", 3);
            for (int i = 0; i < 3; i++) {
                tas.Tas_affecterValeur(b, i, d[i]);
            }
            tas.Tas_supprimerTableau(a);
            int avant = tas.Tas_verifEspaceLibre();
            tas.Tas_garbageCollector();
            int apres = tas.Tas_verifEspaceLibre();
            Assert.assertTrue(avant == apres);
        } catch (Tas_ExceptionEspaceDispo ex) {
           }
       }

       public void testDump(){
        try {
            Tas_Tas tas = new Tas_Tas();
            Object[] c = {1, 2};
            Object[] d = {3, 4, 4};
            int a = tas.Tas_allouerTableau("c", "entier", 2);
            for (int i = 0; i < 2; i++) {
                tas.Tas_affecterValeur(a, i, c[i]);
            }
            int b = tas.Tas_allouerTableau("d", "entier", 3);
            for (int i = 0; i < 3; i++) {
                tas.Tas_affecterValeur(b, i, d[i]);
            }
            tas.Tas_dump();
            Assert.assertTrue(tas.tas == null);
        } catch (Tas_ExceptionEspaceDispo ex) {
             }
       }

       public void testSubdivisionEspacesLibres(){
           Tas_Tas tas = new Tas_Tas();
           tas.Tas_garbageCollector();
           tas.Tas_subdivisionEspacesLibres(tas.tailleTas, tas.espaceLibre, false);
           int n=tas.Tas_pouissance(tas.tailleTas, false);
           Assert.assertTrue(tas.getEspacesVides().size()== n);
       }
    public void testIncrementerref(){
        try {
            Tas_Tas tas = new Tas_Tas();
            Object[] t = {1, 2};
            Object[] t1 = {3, 4, 4};
            int a = tas.Tas_allouerTableau("t", "entier", 2);
            for (int i = 0; i < 2; i++) {
                tas.Tas_affecterValeur(a, i, t[i]);
            }
            int b = tas.Tas_allouerTableau("t1", "entier", 3);
            for (int i = 0; i < 3; i++) {
                tas.Tas_affecterValeur(b, i, t1[i]);
            }
            tas.Tas_incrementerNbref(a, "t", b, "t1");
            a = b;
            Assert.assertEquals(tas.Tas_recupValeur(a, 0), tas.Tas_recupValeur(b, 0));
        } catch (Tas_AdresseTableauInconnue ex) {
        } catch (Tas_ExceptionEspaceDispo ex) {
        }

    }
}
