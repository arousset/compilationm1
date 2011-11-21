/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ROUSSET Alban & Clement KAWCZAK
 */

import java.io.*;
import javax.xml.soap.Node;


public class Compilateur_minijaja {
    public Node ASA;
    public Reader minijaja;
    public String output;

    public Compilateur_minijaja(Reader minijaja){
		this.minijaja = minijaja;
		ASA = null;
		output = "fichier_out.jjc";
    }

    public Compilateur_minijaja(Reader minijaja, String output){
	this.minijaja = minijaja;
	ASA = null;
	this.output = output;
    }

    
    
}
