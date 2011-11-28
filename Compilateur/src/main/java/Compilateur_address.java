/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ROUSSET Alban & Clement KAWCZAK
 */
public class Compilateur_address {
    /**
     * classe d'accesseur permettant de gerer plus facilement les num address dans les visitors
     */
    private int address;
    int entete;
    private boolean retrait;

    public Compilateur_address() {
        // Bon sa sert pas a grand choses juste initialiser car on commence forcement a 1.
        address = 1;
    }

    
    /* Pour les address */
    public void addAdress(int adda) {
        address += adda;
    }

    public void setAddress(int adda) {
        address = adda;
    }

    public int getAddress() {
        return address;
    }


    /* Pour les Entetes */
    public void addEntete(int adda) {
        entete += adda;
    }

    public void setEntete(int adda) {
        entete = adda;
    }

    public int getEntete() {
        return entete;
    }


    /* Pour les retraits */
    public boolean getRetrait() {
        return retrait;
    }

    public void setRetrait(boolean bool) {
        retrait = bool;
    }
}
