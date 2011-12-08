
public class Quadruplet {

	private String nom;
	private Value_pile valeur;
	private Genre genre;
	private Type type;
	private String conteneur;
	
	public Quadruplet(String nom, Value_pile valeur, Genre genre, Type type, String conteneur){
		this.nom=nom;
		this.valeur=valeur;
		this.genre=genre;
		this.type=type;
		this.conteneur=conteneur;
		
	}
	
	public String toString(){
		return ("<" + nom + "," + valeur + "," + genre + "," + type  + ">" + "a pour conteneur : " + conteneur);
	}
	
        public String getIdent(){
            return nom;
        }
        
        public void setGenre(Genre genre){
            this.genre=genre;
        }
        
        public void setIdent(String nom){
            this.nom=nom;
        }
        
        public void setType(Type type){
            this.type=type;
        }
        
        public void setValue(Value_pile valeur){
            this.valeur = valeur;
        }

         public int getValeur() {
            return Integer.parseInt(valeur.toString());
        }   
                  
         public Object getValeurMethode() {
            return valeur.getValeur();
        } 
         
        public String getType() {
            return type.getType();
        } 
	
        public String getConteneur(){
            return conteneur;
        }
        
        public String getGenre(){
            return genre.toString();
        }
        
}


