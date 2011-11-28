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

    public Compilateur_minijaja(Reader minijaja){
		this.minijaja = minijaja;
		ASA = null;
                // Voir pour passer le nom du fichier en parametre ou sinon on garde le meme fichier que l'on ecrase. c'est brain ^^
		output = "fichier_out.jjc";
    }

    public Compilateur_minijaja(Reader minijaja, String output){
	this.minijaja = minijaja;
	ASA = null;
	this.output = output;
    }

    private Object executeVisitor(MiniJajaVisitor visitor, Object data) throws MiniJajaVisitorException {
        return this.ASA.jjtAccept(visitor, data);
    }

    public void parse() throws MiniJajaParseurException {
        try {
            MiniJaja mjj = new MiniJaja(this.minijaja);
            mjj.classe();
            ASA = mjj.getJJTree().rootNode();
        } catch (ParseException ex) {
            throw new MiniJajaParseurException(ex.getMessage());
        }
    }

    public void compile() throws MiniJajaCompilateurException {
        try {
            // On lance le compilation avec les visitor et et compilateur data qui gere les address.
            String lacompil = (String)this.executeVisitor((MiniJajaVisitor) new CompilateurMinijajaVisitor(),new Compilateur_address());

            // On gere les flux de sortie dans le fichier
            FileWriter out_flux = new FileWriter(this.output);
            BufferedWriter buff = new BufferedWriter(out_flux);
            buff.write(lacompil);
            // On oublie pas de vider !
            buff.flush();
            // On ferme
            buff.close();
            out_flux.close();
        }catch(IOException ex) {
            throw new MiniJajaCompilateurException(ex.getMessage());
        }catch(MiniJajaVisitorException e) {
            throw new MiniJajaCompilateurException(e.getMessage());
        }
    }

    public void compile_MiniJaja() throws MiniJajaVisitorException {
        try {
            this.parse();
            this.compile();
        }catch(MiniJajaVisitorException ex) {
            throw new MiniJajaCompilationProcessusException(ex.toString());
        }
    }
}
