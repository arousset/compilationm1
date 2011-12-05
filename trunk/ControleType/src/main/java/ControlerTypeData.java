/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anais
 */
public class ControlerTypeData {
    public TableSymbole ts;
	private String portee;
	private boolean evalType;
	private String evalGenre;

	public ControlerTypeData() {
		ts = new TableSymbole();
		portee = "";
	}

	public void setPortee(String portee){
		this.portee = portee;
	}

	public String getPortee(){
		return this.portee;
	}

	public void setEvalType(){
		this.evalType = true;
	}

	public void unsetEvalType(){
		this.evalType = false;
	}

	public boolean isInEvalType(){
		return evalType;
	}

	public void setEvalGenre(String genre){
		this.evalGenre = genre;
	}

	public String getEvalGenre(){
		return this.evalGenre;
	}

}
