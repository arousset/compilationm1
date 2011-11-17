//package Interpreteur;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


/**
 *
 * @author hermes
 */
public class Inter {
       /*  Pile pile;
         Tas tas;
         MiniJaja parser;*/
	public static void main(String args[]) throws ParseException, FileNotFoundException, MiniJajaVisitorException {
	    // on declare la memoire
            Pile pile = new Pile();
            Tas tas = new Tas();
            
            // On instancie le parser MiniJaja
            MiniJaja parser = new MiniJaja(new FileReader(new File("/home/ubuntu1/exemple.txt")));
	    parser.classe();
	    // Recuperation de la racine de l'AST (Abstract Syntax Tree).
	    Node racine = parser.getJJTree().rootNode();

            // on instancie tt le bordel et donc les visiteurs
            ((SimpleNode) racine).jjtAccept(new InterpreteurVisitorMinijaja(pile, tas, parser), null);

            // Affichage de l'ASA.
	   /* System.out.println("Debut arbre");
            ((SimpleNode) racine).dump(" > ");
	    System.out.println("Fin arbre");*/
	}
   /* public Inter() {
        // on declare la memoire
            pile = new Pile();
            tas = new Tas();
    }

    public void parse(String file_parse) throws FileNotFoundException, ParseException, MiniJajaVisitorException {
        MiniJaja parser = new MiniJaja(new FileReader(new File("/home/ubuntu1/exemple.txt")));
        parser.classe();
	    // Recuperation de la racine de l'AST (Abstract Syntax Tree).
	    Node racine = parser.getJJTree().rootNode();

            // on instancie tt le bordel et donc les visiteurs
            ((SimpleNode) racine).jjtAccept(new InterpreteurVisitorMinijaja(pile, tas, parser), null);

            // Affichage de l'ASA.
	    //System.out.println("Debut arbre");
            //((SimpleNode) racine).dump(" > ");
	    //System.out.println("Fin arbre");

    }*/
}

