
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.JTextArea;


/**
 *
 * @author ROUSSET Alban & Lucas PERRONNE
 */
class Inter extends Thread {
         Pile pile;
         Tas_Tas tas;
         MiniJaja parser;
         String file_parsec;
         JList listpilemjj;
         JList listtasmjj;

	public static void main(String args[]) throws ParseException, FileNotFoundException, MiniJajaVisitorException {
	    // on declare la memoire
            Pile pile = new Pile();
            Tas_Tas tas = new Tas_Tas();        
            
            
            // On instancie le parser MiniJaja
            MiniJaja parser = new MiniJaja(new FileReader(new File("C:/Users/rhork/Desktop/compilationm1/Interpreteur/inter_minijaja/exemple.txt")));
	    parser.classe();
	    // Recuperation de la racine de l'AST (Abstract Syntax Tree).
	    Node racine = parser.getJJTree().rootNode();

            
                        // Affichage de l'ASA.
	    System.out.println("Debut arbre");
            ((SimpleNode) racine).dump(" > ");
	    System.out.println("Fin arbre");
            
            JList pilelist=  new JList();
            JList taslist =  new JList();
            JTextArea s = new JTextArea();
            JTextArea e =  new JTextArea();
                    
            // on instancie tt le bordel et donc les visiteurs
            ((SimpleNode) racine).jjtAccept(new Interpreteur(pilelist,taslist,s,e), null);


	}
    public Inter(String file_parse, JList pilelist, JList taslist) {
        // on declare la memoire
            pile = new Pile();
            tas = new Tas_Tas();
            file_parsec = file_parse;
            listpilemjj = pilelist;
            listtasmjj = taslist;
    }

    public void parse(String file_parse) throws FileNotFoundException, ParseException, MiniJajaVisitorException {
         MiniJaja parser = new MiniJaja(new FileReader(new File(file_parse)));
        parser.classe();
	    // Recuperation de la racine de l'AST (Abstract Syntax Tree).
	    Node racine = parser.getJJTree().rootNode();

            // on instancie tt le bordel et donc les visiteurs
            ((SimpleNode) racine).jjtAccept(new InterpreteurVisitorMinijaja(pile, tas, parser, this), null);

            // Affichage de l'ASA.
	    System.out.println("Debut arbre");
            ((SimpleNode) racine).dump(" > ");
	    System.out.println("Fin arbre");

    }

    @Override
    public void run(){
        System.out.println("Debut du fred");
            MiniJaja parser = null;
            try {
                parser = new MiniJaja(new FileReader(new File(file_parsec)));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Inter.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                parser.classe();
            } catch (ParseException ex) {
                Logger.getLogger(Inter.class.getName()).log(Level.SEVERE, null, ex);
            }
            
	    // Recuperation de la racine de l'AST (Abstract Syntax Tree).
	    Node racine = parser.getJJTree().rootNode();
            try {
                // on instancie tt le bordel et donc les visiteurs
                ((SimpleNode) racine).jjtAccept(new InterpreteurVisitorMinijaja(pile, tas, parser,this), null);
            } catch (MiniJajaVisitorException ex) {
                Logger.getLogger(Inter.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Affichage de l'ASA.
	    System.out.println("Debut arbre");
            ((SimpleNode) racine).dump(" > ");
	    System.out.println("Fin arbre");
            System.out.println("Fin du fred");
            
             Vector<String> pilev = new Vector<String>();
                Vector<String> tasv = new Vector<String>();
                tasv = tas.get_Tas();
                pilev = pile.get_PileV();                                 
                    listpilemjj.setListData(pilev);
                    listpilemjj.updateUI();
                    listtasmjj.setListData(tasv);
                    listtasmjj.updateUI();
                    
       // throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

