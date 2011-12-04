
public class Tas_Vide {
	char val;
	public Tas_Vide(){ 
		this.val = '#';
	}
	public String toString(){
		return ""+this.val;
	}
}




/**
public Tas_Tas(){ // rendre unique :s
	this.tailleTas = 32;
	this.espaceTotal = this.tailleTas;
	this.espaceDefragmente = this.espaceTotal;
	this.espaceFragmente = 0;
	this.tas = new Tas_Espaces[this.tailleTas];
	this.nbTab=0;
	Tas_subdivisionEspacesTotal();
	
}//TODO rendre les trucs privés :)

public boolean Tas_verifIndexTasDispo(int index){	// Vérifie si l'indexe dans le tas n'est pas utilisé
	if(this.tas[index]!=null){
		return false;
	}
	else return true;
}

public int Tas_selectionnerIndex(int taille){
	for (int i =(this.nbTab)-1; i>=0; i--){
		if(!Tas_verifIndexTasDispo(i) && this.tas[i].Espace_verifTabLibre()){
			if ((this.tas[i].tableau.length>=taille) && (this.tas[i].Espace_verifTabLibre())){
				return i;
			}
		}
	}
	return -1;
}

public int Tas_selectionnerTableau(String nom){
	for(int i =0; i<this.tailleTas; i++){
		if(!Tas_verifIndexTasDispo(i)){
			if(this.tas[i].nom == nom)
				return i;
		}
	}
return -1;
}

public Object Tas_recupValeur(String nom, int index){
	return this.tas[Tas_selectionnerTableau(nom)].Espace_recupValeur(index);
}

public void Tas_verifEspaceOccupe(){
	this.espaceFragmente = 0;
	for (int i=0; i<this.tailleTas; i++){
		if(!Tas_verifIndexTasDispo(i) && !this.tas[i].Espace_verifTabLibre()){
			this.espaceTotal -= this.tas[i].tableau.length;
		}
	}
	this.espaceFragmente = this.espaceTotal;
	
}

public void Tas_verifEspaceLibre(){
	this.espaceFragmente = 0;
	for(int i=0; i<this.tailleTas; i++){
		if(!Tas_verifIndexTasDispo(i) && this.tas[i].Espace_verifTabLibre()){
				this.espaceFragmente += this.tas[i].tableau.length;			// Une fonction aurait pu �tre cr��e pour visiter case par case et v�rifier combien sont libres
			
		}
		
	}
	this.espaceTotal = this.espaceFragmente + this.espaceDefragmente;
}
	
public int Tas_verifGarbage(int taille){							// Vérifie si l'espace ciblé est libre et de la taille nécessaire et renvoie l'indexe 
	if(this.espaceTotal>= taille){
		if(Math.pow(2,Tas_pouissance(this.espaceTotal,true)) >=taille){   // verifier chaque tableau et retourner le plus grand pour �viter 4*4*4*4 et dire qu'il y a 16 de place alors qu'en fait pas
			return Tas_selectionnerIndex(taille);
		}
	else{
		 return -2;
	}
}
return-1; 
}

public int Tas_allouerTableau(String nom, String genre, int taille){
	int addrTab = Tas_verifGarbage(taille);
	if(addrTab>=0){
		this.tas[addrTab].Espace_allouerTableau(nom, genre, taille);
		Tas_subdivisionEspaces(addrTab);

	}
	else if(addrTab==-2){
		Tas_refonteEspace();
		addrTab= nbTab-1;												//hash func
		tas[addrTab] = new Tas_Espaces(taille);
		this.tas[addrTab].Espace_allouerTableau(nom, genre, taille);
		this.espaceDefragmente-=taille;
		this.nbTab++;
		Tas_verifEspaceLibre();
		System.out.println(this.espaceDefragmente);
		Tas_subdivisionEspacesTotal();
		System.out.println("CIAO BAMBINO");
	}
	Tas_verifEspaceLibre();
	return addrTab;

}

public void Tas_affecterValeur(String nom, int index, Object val){		// TODO : ajouter des exceptions lors de modification de genre int bool sur un m�me tableau		
	this.tas[Tas_selectionnerTableau(nom)].Espace_affecterValeur(index, val);
}

public void Tas_supprimerTableau(int index){
	this.tas[index]=null;
}

/*	public int Tas_verifTailleEspace(int taille){			// trololol
	int i = 0;									// Scan les tableaux en fonction de leur taille
	if(Tas_verifEspaceOK(taille)!= -1){

		i=Tas_pouissance(taille,false);
	return i;
	}
	return -1;
}

public void Tas_generationEspaces(){						// Fonction témoin
	int taille=1;
	for(int i = 0; i<nbTab; i++){
		this.tas[i]= new Tas_Espaces(taille);
		taille*=2;
		this.nbTab++;
	}

}

public void Tas_refonteEspace(){
	for(int i = 0; i<this.tailleTas; i++){
		if(!Tas_verifIndexTasDispo(i) && this.tas[i].Espace_verifTabLibre()){
			this.espaceDefragmente+=tas[i].tableau.length;
			Tas_supprimerTableau(i);
			this.nbTab--;

		}
	}
	
	
}

public void Tas_subdivisionEspaces(int index){
	int i=0,x =this.tas[index].Espace_verifEspaceFragmente();
	int pouissance= Tas_pouissance(x, true);
	if(	x!=0){
		this.tas[index].Espace_ajusterTableau();
		while(x>0){
			if(Tas_verifIndexTasDispo(i)){

				int taille = (int)(Math.pow(2, pouissance));
				this.tas[i]= new Tas_Espaces(taille);
				x-=taille;
				pouissance = Tas_pouissance(x,true);
				this.espaceFragmente+=taille;
				this.nbTab++;
				
			}
			i++;
		}
	}

}

public void Tas_subdivisionEspacesTotal(){					// Fonction de subdivision de l'espace disponible
	int i=0,pouissance= Tas_pouissance(this.espaceDefragmente, true);
	while ( this.espaceDefragmente>0){
		if(Tas_verifIndexTasDispo(i)){
			int taille = (int)(Math.pow(2, pouissance));
			this.tas[i]= new Tas_Espaces(taille);
			this.espaceDefragmente -= taille;
			pouissance = Tas_pouissance(this.espaceDefragmente, true);
			this.espaceFragmente +=taille;
			this.nbTab++; 
		}
		i++;
		
	}	

}

public void Tas_dump(){
	for(int i=0; i<this.tailleTas; i++){
		if(!Tas_verifIndexTasDispo(i)){
			Tas_supprimerTableau(i);
		}
	}
	this.tas=null;
}
																	
public int Tas_pouissance(int val, boolean inf){   		// tester que i est bien <10 pour la puissance voulue
	int i;
	if(!inf){
		i=0;
		while (Math.pow(2,i)<val && i<10){
			i++;
		}
	}
	else{
		i=10;
		while (Math.pow(2,i)>val && i>0){
			i--;
		}
	}
	

	return i;
}

public Vector<String> get_Tas(){
	Vector<String> vs = new Vector<String>();
	if(this.tas!=null){
		//String retour= "Taille du tas : " + this.tailleTas + " \n" + "Espace m�moire utilis� : " + (this.tailleTas-this.espaceTotal) + " \n" + "Espace m�moire disponible total : " + this.espaceTotal + " \n" + "Espace m�moire fragment� : " + this.espaceFragmente + " \n"+ "Espace m�moire non fragment� : " + this.espaceDefragmente + " \n" + "Nombre d'espaces m�moires existants : " + this.nbTab + " \n" ;
		for(int i=0; i<this.tailleTas; i++){
			if(!Tas_verifIndexTasDispo(i)){
				vs.add (this.tas[i].toString());
			}
		}		
	}
	else{
		vs.add("Tas vide");
	}
	return vs;
}
*/
/*****************************************/
/**
 * 	/*	public Tas_Espaces(int espace, Object[] tab){
			tableau = new Object[tab.length];
			Espace_remplissageTableau(tab);
			for(int i =tab.length; i<espace; i++){
				this.tableau[i]= new Tas_Vide();
			}
		}
		
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
		
		public Object Espace_recupValeur(int index){
			return this.tableau[index];
			
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
		
		
		
		
		
		
		
		int espace, occupe; // choix de ne pas avoir � se balader dans les tableaux � v�rifier tout d'abord si le type de la premi�re case est "Tas_Vide" et ensuite dans le cas contraire v�rifier s'il contient des espaces vide
*/
