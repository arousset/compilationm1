

public class Tas_Espaces {
		int addrTab;		//	entier contenant l'adresse de la premiere case du tableau dans le tas
		String genre;			//  entier indiquant le type de variable stockées
		Object[] tableau;		
		String nom; 		//  chaîne contenant le nom du tableau
		int espace, occupe; // choix de ne pas avoir a se balader dans les tableaux a verifier tout d'abord si le type de la premiere case est "Tas_Vide" et ensuite dans le cas contraire verifier s'il contient des espaces vide
		
				
		
		//Constructeur par defaut

		
		
		public Tas_Espaces(int espace){
			tableau = new Object[espace];
			for(int i =0; i<espace; i++){
				this.tableau[i]= new Tas_Vide();
			}
			this.espace = espace;														// Définit la taille totale de l'espace ( multiple de 2 )
			this.occupe = 0;
                        this.nom = "non alloue";
                        this.genre = "-";
		}

	/*	public Tas_Espaces(int espace, Object[] tab){
			tableau = new Object[tab.length];
			Espace_remplissageTableau(tab);
			for(int i =tab.length; i<espace; i++){
				this.tableau[i]= new Tas_Vide();
			}
		}*/
		
		public void Espace_affecterValeur(int index, Object val){
			this.tableau[index]=val;								
		}
		
		public void Espace_allouerTableau(String nom, String genre, int taille){
			this.nom = nom;
			this.genre= genre;
			this.occupe= taille;
		}
				
		public Object[] Espace_getTableau(int addrTab, int taille ){
			return this.tableau;
		}
		
		public boolean Espace_verifTabLibre(){
			return (this.tableau[0] instanceof Tas_Vide);
		}
		
		public void Espace_viderTableau (int addrTab, int taille, int[] tas){
			for(int i = addrTab; i<taille; i++){
				tas[i]=0;
			}
		}
		
		public int Espace_verifEspaceFragmente(){ 
			return (this.espace-this.occupe);
		}
		
		public void Espace_ajusterTableau(){
			Object[] tabjuste = new Object[this.occupe];
				for(int i = 0; i< this.occupe; i++){
					tabjuste[i] = this.tableau[i];
					}
				this.espace=this.occupe;
				this.tableau=null;						// Free l'espace anciennement alloué pour les données
				this.tableau=tabjuste;					// Ajuste le tableau à la taille des données contenues
				
		}
		
                public Object Espace_recupValeur(int index){
                        return this.tableau[index];
            
                }
                
                
                
        public String toString(){
            String retour="["+genre+"] "+"@"+nom+ " : [";
            for(int i =0; i<this.tableau.length; i++){
                if (i==this.tableau.length -1){
                    retour += this.tableau[i]+"] \n";
                }
                else retour += this.tableau[i] +", ";
            }
            return retour;
        }

	}

