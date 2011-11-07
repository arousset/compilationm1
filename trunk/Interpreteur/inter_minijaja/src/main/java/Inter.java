//package Interpreteur;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


/**
 *
 * @author hermes
 */
public class Inter {
	public static void main(String args[]) throws ParseException, FileNotFoundException {
	    MiniJaja parser = new MiniJaja(new FileReader(new File("/home/ubuntu1/exemple.txt")));
	    parser.classe();
	    // Recuperation de la racine de l'AST (Abstract Syntax Tree).
	    Node racine = parser.getJJTree().rootNode();

            // Affichage de l'arbre dans la console systeme.
	    System.out.println("Debut arbre");
	    ((SimpleNode) racine).dump(" > "); 
	    System.out.println("Fin arbre");
	}
}

