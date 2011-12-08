/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ROUSSET Alban & Clement KAWCZAK
 */

import java.io.*;


public class Compilateur_minijaja {
    public Node ASA;
    public Reader minijaja;
    public String output;
    MiniJaja mjj;

    public Compilateur_minijaja(){
		ASA = null;
                // Voir pour passer le nom du fichier en parametre ou sinon on garde le meme fichier que l'on ecrase. c'est brain ^^
		output = "fichier_out.jjc";
                //mjj = new MiniJaja(this.minijaja);
    }
    
    

    public Compilateur_minijaja(Reader minijaja, String output){
	this.minijaja = minijaja;
	ASA = null;
	this.output = output;
    }

    private Object executeVisitor(MiniJajaVisitor visitor, Object data) throws MiniJajaVisitorException {
        return this.ASA.jjtAccept(visitor, data);
    }

    public void controleType() throws MiniJajaControleurTypeException {
        ControlerTypeData ctdata = new ControlerTypeData();
        System.out.println("ON EST ICI");
        try {
            boolean ret = (Boolean)this.executeVisitor(new ControlerTypeVisitor(), ctdata);
                    System.out.println(ret);
        }catch(MiniJajaVisitorException e) {
            throw new MiniJajaControleurTypeException(e.toString());
        }
    }

    public void parse() throws MiniJajaParseurException {
        try {
            mjj.classe();
            ASA = mjj.getJJTree().rootNode();
            
            
            System.out.println("Debut arbre");
            ((SimpleNode) ASA).dump(" > ");
	    System.out.println("Fin arbre");
            
            
            
        } catch (ParseException ex) {
            throw new MiniJajaParseurException(ex.getMessage());
        }
    }

    public String compile() throws MiniJajaCompilateurException {
        try {
            // On lance le compilation avec les visitor et et compilateur data qui gere les address.
            String lacompil = (String)this.executeVisitor((MiniJajaVisitor) new CompilateurMinijajaVisitor(),new Compilateur_address());
               return lacompil;
            // On gere les flux de sortie dans le fichier
            /*FileWriter out_flux = new FileWriter(this.output);
            BufferedWriter buff = new BufferedWriter(out_flux);
            buff.write(lacompil);
            // On oublie pas de vider !
            buff.flush();
            // On ferme
            buff.close();
            out_flux.close();*/
       /* }catch(IOException ex) {
            throw new MiniJajaCompilateurException(ex.getMessage());*/
        }catch(MiniJajaVisitorException e) {
            throw new MiniJajaCompilateurException(e.getMessage());
        }
    }

    public String compile_MiniJaja(Reader file, boolean firstParser) throws MiniJajaVisitorException {
        this.minijaja = file;
        if(firstParser)
            mjj = new MiniJaja(this.minijaja);
        else
             mjj.ReInit(minijaja);

        try {       
            this.parse();
            this.controleType();
            return this.compile();
        }catch(MiniJajaVisitorException ex) {
            throw new MiniJajaCompilationProcessusException(ex.toString());
        }
    }

    /* A virer par la suite ! phase test */
   /* public static void main(String args[]) throws Exception {
        Compilateur_minijaja compilo;          

        compilo = new Compilateur_minijaja(new FileReader(new File("C:/Users/Keiro/Documents/sample.txt")));
        try {
            compilo.compile_MiniJaja();
        } catch(MiniJajaVisitorException e) {
            System.err.println(e.toString());
        }
        System.out.println("On a fini le parsing");
    }*/
    
 
    
}
