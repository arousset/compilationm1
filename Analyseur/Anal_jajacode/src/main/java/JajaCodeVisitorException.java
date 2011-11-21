public class JajaCodeVisitorException extends Exception {
	
	/**
	 * Constructeur par defaut
	 * @param msg message d'erreur de l'exception
	 */
	public JajaCodeVisitorException(String msg){
		super(msg);
	}
	
	public String toString(){
		return this.getMessage() + "\n\t-> " + this.getClass().getName();
	}
	
}
