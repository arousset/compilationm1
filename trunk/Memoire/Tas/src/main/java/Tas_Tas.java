import java.util.ArrayList;
import java.util.Vector;



public class Tas_Tas {
	public final static int TT = 256;
	
	int tailleTas ;
	int espaceLibre;
	Object[] tas;
	private ArrayList<Tas_Espaces>espacesOccupes;
	private ArrayList<ArrayList<Tas_Espaces>>espacesVides;
	private Tas_Vide vide;
	
	public Tas_Tas(){ // rendre unique :s
		this.tailleTas = 31; // = TT
		this.espaceLibre = this.tailleTas;
		vide = new Tas_Vide();
		tas = new Object[tailleTas];
		espacesOccupes = new ArrayList<Tas_Espaces>();
		espacesVides = new ArrayList<ArrayList<Tas_Espaces>>();
		espacesVides = Tas_arrayList();
		Tas_subdivisionEspacesLibres(tailleTas,espaceLibre,false);
		for(int i =0; i<tailleTas; i++){
			this.tas[i]= vide;
		}
	}
	
	
	public boolean Tas_verifCaseLibre(int index){
		return (this.tas[index] instanceof Tas_Vide);
	}

	public int Tas_verifEspaceLibre(){
		int k = 0;
		for(int i=0; i<tailleTas; i++){
			//System.out.println("("+i+") ->" +tas[i]);
			if(Tas_verifCaseLibre(i)){
				k++;
			}
		}
		for (int j=0; j<espacesVides.size(); j++){
			System.out.println("Espaces vides de la taille de 2 puissance "+j);
			for (int f=0; f<espacesVides.get(j).size(); f++){
				System.out.println("Espace numero :" +f + " contient " + espacesVides.get(j).size() +" tableaux");
				System.out.println("Addresse dans le tas : " +espacesVides.get(j).get(f).getAddrTas());
				System.out.println("Taille dans le tas : " +espacesVides.get(j).get(f).getTaille());
			}

		}
		System.out.println("\n");
		for (int j=0; j<espacesOccupes.size(); j++){
		System.out.println("Nombre d'espaces occupes : "+espacesOccupes.size());
			System.out.println("Espace numero :" +j + " contient " + espacesOccupes.get(j).getTaille() +" donnees");
			System.out.println("Addresse dans le tas : " +espacesOccupes.get(j).getAddrTas());
		}
		System.out.println("\n" +k);
		return k;
	}
	
	public int Tas_selectionnerIndex(int taille){
		int index = Tas_pouissance(taille,false);
		if(taille<this.espaceLibre){
			if((index<espacesVides.size())&&(!espacesVides.get(index).isEmpty())){
				return index;
			}
			else {
				return -1;
			}
		}
		return -2;
	}
	
        public Object Tas_recupValeur(int addr, int index){
            return this.tas[this.espacesOccupes.get(addr).getAddrTas() + index];
        }
        
	public int Tas_allouerTableau(String nom, String genre, int taille){
		int aT = Tas_selectionnerIndex(taille);
		if(aT>=0){
			int indexv = (this.espacesVides.get(aT).size() - 1) ; 		// selectionne le dernier element d'une arraylist d'une puissance donnee
			//System.out.println("LAAAAAAAAA"+ this.espacesVides.get(aT).size());
			//System.out.println("ICIIIIIIII" +this.espacesVides.get(4).get(0).getTaille());
			this.espacesOccupes.add(this.espacesVides.get(aT).get(indexv));
			//System.out.println("diff de taille" +(this.espacesVides.get(aT).get(indexv).getTaille() - taille));
			//System.out.println("addrTas>>>"+ this.espacesVides.get(aT).get(indexv).getAddrTas());
			//System.out.println("addr de debut de subdiv" +( this.espacesVides.get(aT).get(indexv).getAddrTas() + taille));
			Tas_subdivisionEspacesLibres ( this.espacesVides.get(aT).get(indexv).getAddrTas() + taille,(this.espacesVides.get(aT).get(indexv).getTaille()) - taille, true ); 	// soustrait de la taille de lespace vide lespace utilise et subdivise en sous tableaux
			this.espaceLibre -= taille;
			this.espacesVides.get(aT).remove(indexv);
			int indexo = this.espacesOccupes.size() - 1;
			this.espacesOccupes.get(indexo).setGenre(genre);
			this.espacesOccupes.get(indexo).setNom("-"+nom+"-");
			this.espacesOccupes.get(indexo).setnbRef(1);
			this.espacesOccupes.get(indexo).setTaille(taille); 
			//this.espacesOccupes.add(aT, new Tas_Espaces(aT, 1, taille, genre, nom));
			//Tas_verifEspaceLibre();
		}
		
		else if(aT==-1){
			Tas_garbageCollector();
			aT = this.espaceLibre - taille;
			this.espaceLibre -= taille;
			this.espacesOccupes.add(new Tas_Espaces(aT, 1, taille, genre, nom));
			Tas_subdivisionEspacesLibres ( aT,this.espaceLibre, false ); 	// soustrait de la taille de lespace vide lespace utilise et subdivise en sous tableaux
			int indexo = this.espacesOccupes.size() - 1;
			this.espacesOccupes.get(indexo).setGenre(genre);
                        this.espacesOccupes.get(indexo).setNom("-"+nom+"-");
			this.espacesOccupes.get(indexo).setnbRef(1);
			this.espacesOccupes.get(indexo).setTaille(taille); 

		}

		else if(aT==-2){
			System.out.println(">>>>>>>> RED ALERT <<<<<<<<");
		}
		return this.espacesOccupes.size()-1;

	}
	
	public void Tas_affecterValeur(int addr, int index, Object val){							// TODO : ajouter des exceptions lors de modification de genre int bool sur un meme tableau		
		//if()																		tester les types
		this.tas[(this.espacesOccupes.get(addr).getAddrTas() + index)] = val;
	//	System.out.println(this.tas[]);
	}
	
        
        
        public void Tas_supprimerTableau(int addr){
            for(int i =this.espacesOccupes.get(addr).getAddrTas(); i<this.espacesOccupes.get(addr).getAddrTas()+this.espacesOccupes.get(addr).getTaille(); i++){
            this.tas[i]= this.vide;
            }
            this.espaceLibre += this.espacesOccupes.get(addr).getTaille();
            this.espacesOccupes.remove(addr);
        }
        
        
	public void Tas_subdivisionEspacesLibres(int aT, int t, boolean incr){								// Fonction de subdivision de l'espace disponible
		int taille, pouissance ;
		if (incr){
			 taille =1;
			while ( t > 0 ){	
				pouissance = Tas_pouissance(t,true);
				taille = (int)(Math.pow(2, pouissance));
				this.espacesVides.get(pouissance).add(new Tas_Espaces( aT ,taille));
				aT+=taille;
				t -= taille;
			}
		}
		else{
				while ( t > 0 ){

						pouissance = Tas_pouissance(t,true);
						taille = (int)(Math.pow(2, pouissance));
						aT-=taille;
						this.espacesVides.get(pouissance).add(new Tas_Espaces( aT ,taille));
						t -= taille;
			}
		}
		
	}
	
        public void Tas_garbageCollector(){
            if(!this.espacesOccupes.isEmpty()){
                for (int i = 0; i<this.espacesOccupes.size(); i++){
                    if(this.espacesOccupes.get(i).getnbRef()==0){
                       Tas_supprimerTableau(i);
                    }
                }
                Tas_reorganiserTas();

            }
            for (int i = 0; i<this.espacesVides.size(); i++){
                this.espacesVides.get(i).clear();
            }
        }
	
public int Tas_incrementerNbref(int addrt, String t, int addrt1, String t1){
if (this.espacesOccupes.get(addrt1)!=null){
this.espacesOccupes.get(addrt1).setnbRef(this.espacesOccupes.get(addrt1).getnbRef()+1);
this.espacesOccupes.get(addrt1).setNom((String)this.espacesOccupes.get(addrt1).getNom() + t +"-");
this.Tas_decrementerNbref(addrt, t);
return 0;
}
return -2;
}

public int Tas_decrementerNbref(int addrt, String t){
if (this.espacesOccupes.get(addrt)!=null){
this.espacesOccupes.get(addrt).setnbRef(this.espacesOccupes.get(addrt).getnbRef()-1);
this.espacesOccupes.get(addrt).setNom((String)this.espacesOccupes.get(addrt).getNom().replace("-"+t+"-", "-"));
if (this.espacesOccupes.get(addrt).getnbRef()==0){
this.espacesOccupes.get(addrt).setNom("<Unreferenced>");
}
return 0;
}
return -2;
}

    public void setEspaceLibre(int espaceLibre) {
        this.espaceLibre = espaceLibre;
    }

        
        
        
        
	public int Tas_verifIndexOccupe(int index){
		for(int i=0; i<this.espacesOccupes.size(); i++){
	//		System.out.println("index de la case qu'on veut recup : "+index);
	//		System.out.println("index e la con := " + (this.espacesOccupes.get(i).getAddrTas() + this.espacesOccupes.get(i).getTaille()) + " a pour i : " +i);
			if( (this.espacesOccupes.get(i).getAddrTas() + this.espacesOccupes.get(i).getTaille() -1 ) == index){
				return i;
			}
		}
		return -2;
	}
	
        public int Tas_decrementerNbref(int addr){
            if (this.espacesOccupes.get(addr)!=null){
               this.espacesOccupes.get(addr).setnbRef(this.espacesOccupes.get(addr).getnbRef()-1);
               return 0;
            }
            return -2;
        }
        
        
	public void Tas_reorganiserTas(){
		int add, j=0, taille, debut, nbtab = this.espacesOccupes.size(), i=this.tailleTas-1;
		while(nbtab>0 && i>=0){
			//System.out.println("i ->" +i);
			//System.out.println("j ->" +j);
			
			if(Tas_verifCaseLibre(i)){
				j++;
				
			}
			else if(j>0){
				add = Tas_verifIndexOccupe(i);
		//		System.out.println(add);
				taille =  this.espacesOccupes.get(add).getTaille();
				debut = this.espacesOccupes.get(add).getAddrTas();
		//		System.out.println("debut : "+debut + " e " + (debut+j));
				Tas_transfertTableau(debut,debut + j,taille);
				this.espacesOccupes.get(add).setAddrTas(debut+j);
			
				nbtab--;
				j=0;
				}
			i--;
		}
	}
	
	public void Tas_transfertTableau(int aT1, int aT2, int taille){
		for(int i = taille -1; i>=0; i--){
	//	System.out.println("tas arrivee : " + tas[aT2+i] + "\n tas depart : " + tas[aT1+i] );
			tas[aT2+i]=tas[aT1+i];
			tas[aT1+i]=this.vide;
		}
	}
	
	public ArrayList<ArrayList <Tas_Espaces>> Tas_arrayList(){ 			// creation de l'arraylist espacesvides
		int pouissance= Tas_pouissance(this.espaceLibre, false);
		for (int i=0; i<pouissance; i++){
			espacesVides.add(i, new ArrayList<Tas_Espaces>());
		}
		return espacesVides;
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
	
	public void Tas_dump(){
		for(int i=0; i<this.espacesVides.size(); i++){
			espacesVides.get(i).clear();
			}
		this.espacesOccupes.clear();
		this.vide = null;
		this.tas=null;
	}
	
	public Vector<String> get_Tas(){
		Vector<String> vs = new Vector<String>();
		if(this.tas!=null){
			int debut,fin;
			//String retour= "\nTaille du tas : " + this.tailleTas + " \n" + "Espaces memoire utilises : " + (this.tailleTas-this.espaceLibre) + " \n" + "Espace memoire disponible total : " + this.espaceLibre + " \n" + "Nombre de tableaux existants : " + this.espacesOccupes.size()  + " \n" ;
			//vs.add(retour);
			
			for(int i=0; i<this.espacesOccupes.size(); i++){
				 vs.add (this.espacesOccupes.get(i).toString());
				 debut = this.espacesOccupes.get(i).getAddrTas();
				 fin = debut + this.espacesOccupes.get(i).getTaille();
				
				for(int j = debut; j < fin; j++){
					vs.add(tas[j].toString());
				}
				vs.add("\n");
			}
			/*for (int i=0; i<this.espacesVides.size(); i++){
				for (int j=0; j<this.espacesVides.get(i).size(); j++){
					 vs.add(this.espacesVides.get(i).get(j).toString());
					 debut = this.espacesVides.get(i).get(j).getAddrTas();
					 fin = debut + this.espacesVides.get(i).get(j).getTaille();

					for(int k = debut; k < fin; k++){
						vs.add(tas[k].toString());

					}
					vs.add("\n");
				}
			}*/
		}
		else{
			vs.add("Tas vide");
		}
		return vs;
	}

    public ArrayList<ArrayList<Tas_Espaces>> getEspacesVides() {
        return espacesVides;
    }


}
