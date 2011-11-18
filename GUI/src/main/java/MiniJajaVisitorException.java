public class MiniJajaVisitorException extends Exception {
	
	/**
	 * Constructeur par defaut
	 * @param msg message d'erreur de l'exception
	 */
	public MiniJajaVisitorException(String msg){
		super(msg);
	}
	
	public String toString(){
		return this.getMessage() + "\n\t-> " + this.getClass().getName();
	}
	
}
