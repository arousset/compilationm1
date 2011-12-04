
/**
 * Classe Tas_Espaces: Represente un tableau dans le tas.
 * 				   Permet de recuperer les informations sur le tableau 
 * 			       pour le retrouver dans le tas et la pile.
 */

public class Tas_Espaces {
		/** Entier contenant l'adresse de la premiere case du tableau dans le tas. */
		private int addrTas;	
		/** Entier contenant le nombre de references faites au tableau. */
		private int nbRef;
		/** Entier contenant la taille du tableau. */
		private int taille;			
		/** Chaine indiquant le type de variable stockee. */
		private String genre;	
		/** Chaine contenant le nom du tableau. */
		private String nom; 	
		

		/**
		 * Constructeur par initialisation : initialise tous les attributs d'un Tas_Espaces occupe
		 * @param aT : L'adresse dans le tas.
		 * @param nbRef : Le nombre de references au tableau. 
		 * @param t  : La taille du tableau.
		 * @param genre : Le genre des variables du tableau.
		 * @param nom : Le nom du tableau.
		 */
		public Tas_Espaces(int aT, int nbRef, int taille, String genre, String nom){
			this.addrTas = aT;
			this.nbRef = nbRef;
			this.taille = taille;
			this.genre = genre;
			this.nom = nom;
		}
		
		/**
		 * Constructeur par initialisation : initialise tous les attributs d'un Tas_Espaces libre
		 * @param aT : L'adresse dans le tas.
		 * @param t  : La taille du tableau.
		 */
		public Tas_Espaces(int aT, int taille){
			this.addrTas = aT;
			this.taille = taille;

		}
		
		/**
		 * Méthode getAddrTas : Recupere la valeur de l'adresse dans le tas.
		 * @return : L'adresse du tas de l'instance courante.
		 */
		public int getAddrTas() {
			return addrTas;
		}

		/**
		 * Methode setAddrTas : Change l'attribut <addrTas>.
		 * @param addrTas : Change la valeur de l'adresse du tableau dans le tas de l'instance courante.
		 */
		public void setAddrTas(int addrTas) {
			this.addrTas = addrTas;
		}
		
		/**
		 * Méthode getnbRef : Recupere le nombre de references du tableau.
		 * @return : Le nombre de references du tableau de l'instance courante.
		 */
		public int getnbRef() {
			return nbRef;
		}

		/**
		 * Methode setnbRef : Change l'attribut <nbRef>.
		 * @param nbRef : Change le nombre de references du tableau dans le tas de l'instance courante.
		 */
		public void setnbRef(int nbRef) {
			this.nbRef = nbRef;
		}
		
		/**
		 * Methode getTaille : Recupere la valeur de la taille du tableau.
		 * @return : La taille du tableau de l'instance courante.
		 */
		public int getTaille() {
			return taille;
		}

		/**
		 * Methode setTaille: Change l'attribut <taille>.
		 * @param taille : Change la valeur de la taille d'un tableau de l'instance courante.
		 */
		public void setTaille(int taille) {
			this.taille = taille;
		}

		/**
		 * Methode getGenre : Recupere le genre du tableau.
		 * @return : Le genre du tableau de l'instance courante.
		 */
		public String getGenre() {
			return genre;
		}

		/**
		 * Methode setGenre: Change l'attribut <genre>.
		 * @param genre : Change le genre d'un tableau de l'instance courante.
		 */
		public void setGenre(String genre) {
			this.genre = genre;
		}

		/**
		 * Methode getNom : Recupere le nom du tableau.
		 * @return : Le nom du tableau de l'instance courante.
		 */
		public String getNom() {
			return nom;
		}

		/**
		 * Methode setNom: Change l'attribut <nom>.
		 * @param nom : Change le nom d'un tableau de l'instance courante.
		 */
		public void setNom(String nom) {
			this.nom = nom;
		}

		/**
		 * Methode toString: Affiche les informations d'un Tas_Espaces
		 */
		public String toString(){
			String retour="("+taille+")" + "["+genre+"] "+"@"+nom+ " : ";

			return retour;
		}
}