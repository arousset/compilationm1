
public class Quadruplet {

	private String nom;
	private Object valeur;
	private Genre genre;
	private Type type;
	private Quadruplet conteneur;
	
	public Quadruplet(String nom, Object valeur, Genre genre, Type type, Quadruplet conteneur){
		this.nom=nom;
		this.valeur=valeur;
		this.genre=genre;
		this.type=type;
		this.conteneur=conteneur;
		
	}
	
	public String toString(){
		return ("<" + nom + "," + valeur + "," + genre + "," + type  + ">");
	}
	

	
}