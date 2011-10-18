
public class Tas_tas {
	int tailleTas ;
	int espaceDispo;
	boolean plein; 
	int nbTab = 10;	
	Tas_Espaces[] tas;
	

	
	
/*	public int verifEspaceLibre(Tas_Espaces[] tas, int tailleTas){
		for(int i=0; i<tailleTas; i++){
			if (tas[i]==0){
				espaceDispo++;
			}
		}
		return espaceDispo;
	}*/
	public Tas_tas(){
		this.tas = new Tas_Espaces[nbTab];
		tailleTas = 1023;
	}
	
	public void selectionEspaces(int taille, int[]tas){
	
	}
	
	public void generationEspaces(){
		int taille=1;
		for(int i = 0; i<nbTab; i++){
			this.tas[i]= new Tas_Espaces(taille);
			taille*=2;
		}
	}

	public void allocationTableau(int addrTab, int taille, int[] tas, int[] tab){
		for(int i = addrTab; i<taille; i++){
			tas[i]=tab[i];
		}		
	}
	public String toString(){
		String retour= "";
		for(int i=0; i<nbTab; i++){
			retour +=this.tas[i].toString()+"   // \n";
		}
		return retour;
	}
	public static void main (String[] args ){
		Tas_tas tas = new Tas_tas();
		tas.generationEspaces();

		System.out.print(tas.toString());
	}
}
