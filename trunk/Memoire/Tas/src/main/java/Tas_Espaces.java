
public class Tas_Espaces {
		int addrTab;		//	entier contenant l'adresse de la premiere case du tableau dans le tas
		int genre;
		int[] tableau;//  entier indiquant le type de variable stockées
		String nom; 		//  chaîne contenant le nom du tableau
		
		
				
		
		//Constructeur par defaut

		
		
		public Tas_Espaces(int taille){
			tableau = new int[taille];
			for(int i =0; i<taille; i++){

				this.tableau[i]=0;
			}
		}

		
		
		
		public int[] recupTableau(int addrTab, int taille,int[] tas ){
			int[] tab={0};
			if(tas[addrTab]!=0){
				for(int i = addrTab; i<taille; i++){
					tab[i]=tas[i];
				}
			}
			return tab;
		}
		
		public String recupNom(){		
			return nom;
		}
		
		public boolean verifTabOccupe(int addrTab, int taille, int[] tas){
			int i=addrTab;
			boolean vide=true;
			while (vide && ( i < taille)){
				if (tas[i]!=0){
					vide=false;
				}
			i++;
			}
			return vide;
		}
		
		public void viderTableau (int addrTab, int taille, int[] tas){
			for(int i = addrTab; i<taille; i++){
				tas[i]=0;
			}
		}
		
		//Affiche les caractéristiques du tableau dans le tas
		public String toString(){
			String retour=" ";
			for(int i =0; i<this.tableau.length; i++){
				retour += this.tableau[i] + " | ";
			}
			return retour;
		}

	}

