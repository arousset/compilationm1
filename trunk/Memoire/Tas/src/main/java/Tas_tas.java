
import java.util.Vector;


public class Tas_Tas {
	int tailleTas ;
	int espaceFragmente;
	int espaceDefragmente;
	int espaceTotal;
	boolean plein; 
	int nbTab;	
	Tas_Espaces[] tas;
	

	//TODO rendre les trucs privÃ©s :) // faire avec un switch les diffï¿½rents types de tableaux, faire les espaces vides d'un type particulier
	

	
	public Tas_Tas(){ // rendre unique :s
		this.tailleTas = 32;
		this.espaceTotal = this.tailleTas;
		this.espaceDefragmente = this.espaceTotal;
		this.espaceFragmente = 0;
		this.tas = new Tas_Espaces[this.tailleTas];
		this.nbTab=0;
		this.Tas_subdivisionEspacesTotal();
	}
	
	public boolean Tas_verifIndexTasDispo(int index){	// VÃ©rifie si l'indexe dans le tas n'est pas utilisÃ©
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
				if(this.tas[i].nom.equals(nom))
					return i;
			}
		}
	return -1;
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
					this.espaceFragmente += this.tas[i].tableau.length;			// Une fonction aurait pu ï¿½tre crï¿½ï¿½e pour visiter case par case et vï¿½rifier combien sont libres
				
			}
			
		}
		this.espaceTotal = this.espaceFragmente + this.espaceDefragmente;
	}
		
	public int Tas_verifGarbage(int taille){							// VÃ©rifie si l'espace ciblÃ© est libre et de la taille nÃ©cessaire et renvoie l'indexe 
		if(this.espaceTotal>= taille){
			if(Math.pow(2,Tas_pouissance(this.espaceTotal,true)) >=taille){   // verifier chaque tableau et retourner le plus grand pour ï¿½viter 4*4*4*4 et dire qu'il y a 16 de place alors qu'en fait pas
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
	
	public void Tas_affecterValeur(String nom, int index, Object val){		// TODO : ajouter des exceptions lors de modification de genre int bool sur un même tableau		
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
	}*/
	
	public void Tas_generationEspaces(){						// Fonction tÃ©moin
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
	
	public void Tas_subdivisionEspacesTotal(){					// Fonction de subdivision de l'espace disponible TODO : virer la diminution d'espaceDisop
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
	
																			//System.out.println("L'adresse du tableau ï¿½ remplir : " +addrTab);
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
	
    public String toString(){
        if(this.tas!=null){
            String retour= "Taille du tas : " + this.tailleTas + " \n" + "Espace mï¿½moire utilisï¿½ : " + (this.tailleTas-this.espaceTotal) + " \n" + "Espace mï¿½moire disponible total : " + this.espaceTotal + " \n" + "Espace mï¿½moire fragmentï¿½ : " + this.espaceFragmente + " \n"+ "Espace mï¿½moire non fragmentï¿½ : " + this.espaceDefragmente + " \n" + "Nombre d'espace mï¿½moires existants : " + this.nbTab + " \n" ;
            for(int i=0; i<this.tailleTas; i++){
                if(!Tas_verifIndexTasDispo(i)){
                    retour +=this.tas[i].toString()+"   // \n";
                }
            }
            return retour;
        }
        else return "Pas de tas a afficher";
    }
       
    
    public Vector<String> get_Tas(){
        Vector<String> vs = new Vector<String>();
        if(this.tas!=null){
            //String retour= "Taille du tas : " + this.tailleTas + " \n" + "Espace mémoire utilisé : " + (this.tailleTas-this.espaceTotal) + " \n" + "Espace mémoire disponible total : " + this.espaceTotal + " \n" + "Espace mémoire fragmenté : " + this.espaceFragmente + " \n"+ "Espace mémoire non fragmenté : " + this.espaceDefragmente + " \n" + "Nombre d'espaces mémoires existants : " + this.nbTab + " \n" ;
            for(int i=0; i<this.tailleTas; i++){
                if(!Tas_verifIndexTasDispo(i)){
                    if (this.tas[i].genre != "-"){
                    vs.add (this.tas[i].toString());
                    }
                }
            }        
        }
        else{
            vs.add("Tas vide");
        }
        return vs;
    }
    
    
    
        public Object Tas_recupValeur(String nom, int index){
            Object x;
            return this.tas[Tas_selectionnerTableau(nom)].Espace_recupValeur(index);
        }
        
        
        
        public void Tas_dump(){
            for(int i=0; i<this.tailleTas; i++){
                if(!Tas_verifIndexTasDispo(i)){
                    Tas_supprimerTableau(i);
                }
            }
            this.tas=null;
        }
}
