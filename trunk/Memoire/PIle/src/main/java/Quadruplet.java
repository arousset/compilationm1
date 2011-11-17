
public class Quadruplet {

	private String nom;
	private Value_pile valeur;
	private Genre genre;
	private Type type;
	private Quadruplet conteneur;
	
	public Quadruplet(String nom, Value_pile valeur, Genre genre, Type type, Quadruplet conteneur){
		this.nom=nom;
		this.valeur=valeur;
		this.genre=genre;
		this.type=type;
		this.conteneur=conteneur;
		
	}
	
	public String toString(){
		return ("<" + nom + "," + valeur + "," + genre + "," + type  + ">");
	}
	
        public String getIdent(){
            return nom;
        }
        

        public void setValue(Value_pile valeur){
            this.valeur = valeur;
        }

         public int getValeur() {
            return Integer.parseInt(valeur.toString());
        }   
	
}


