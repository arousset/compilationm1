
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


/**
 *
 * @author JeanJean
 */
public class Inter {
         Pile pile;
         Tas_Tas tas;
         JajaCode parser;
         

	public static void main(String args[]) throws ParseException, FileNotFoundException, JajaCodeVisitorException {
	    // on declare la memoire
            Pile pile = new Pile();
            Tas_Tas tas = new Tas_Tas();        
            
            
            // On instancie le parser MiniJaja
	    JajaCode parser = new JajaCode(new FileReader(new File("C:/Users/rhork/Documents/aa.txt")));         
            parser.Classe();
	    // Recuperation de la racine de l'AST (Abstract Syntax Tree).
	    Node racine = parser.legetJJTree().rootNode();

         
                        // Affichage de l'ASA.
	    System.out.println("Debut arbre");
            ((SimpleNode) racine).dump(" > ");
	    System.out.println("Fin arbre");
            
            
            // on instancie tt le bordel et donc les visiteurs
            ((SimpleNode) racine).jjtAccept(new InterpreteurVisitorJajacode(pile, tas, parser), null);


	}
    public Inter() {
        // on declare la memoire
            pile = new Pile();
            tas = new Tas_Tas();
    }
/*
    public void parse(String file_parse) throws FileNotFoundException, ParseException, MiniJajaVisitorException {
        MiniJaja parser = new MiniJaja(new FileReader(new File("C:/Users/rhork/Desktop/compilationm1/Interpreteur/inter_minijaja/exemple.txt")));
        parser.classe();
	    // Recuperation de la racine de l'AST (Abstract Syntax Tree).
	    Node racine = parser.getJJTree().rootNode();

            // on instancie tt le bordel et donc les visiteurs
            ((SimpleNode) racine).jjtAccept(new InterpreteurVisitorMinijaja(pile, tas, parser), null);

            // Affichage de l'ASA.
	    System.out.println("Debut arbre");
            ((SimpleNode) racine).dump(" > ");
	    System.out.println("Fin arbre");

    }
     */
    
}

