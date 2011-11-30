
/**
 *
 * @author anais
 */
public class TableSymbole {

    public final static int ncase=37;
    public final static int cod=31;

    public ElementListeChaine[] table;

    public TableSymbole(){
        this.table= new ElementListeChaine[ncase];
    }


    public static int hashVal(String k){
        if(k==null){
            return -1;
        }

        int h=0;
        int n=k.length();

        if (n==1){
            h=k.charAt(0)*cod;
        }
         else{
            n--;
            for(int i=0;i<n;i++){
                h+=k.charAt(i)*(cod^n);
                n--;
            }
         }
        int h2=h%ncase;
        return h2;
    }

    public void add(Symbole s){
        int key=hashVal(s.nom);

        if (table[key] ==null){
            table[key] = new ElementListeChaine(s);
        }
         else{
            table[key].add(s);
         }
    }

    public void del(Symbole s)
    {
        int k=hashVal(s.nom);
        if(table[k]!= null){
            table[k].del(s);
        }
    }

    public boolean modif(Symbole s){
        int key=hashVal(s.nom);


        ElementListeChaine sy=table[key].recherche(s);
        if(sy==null){
            return false;
        }
        sy.setVal(s);
        return true;
    }

    public boolean exist(Symbole s){
        int  key=hashVal(s.nom);
        if(table[key].recherche(s)==null){
            return false;

        }
        return true;
    }

    public String cherchePos(Symbole s){
        int key=hashVal(s.nom);

        ElementListeChaine e= table[key].recherche(s);
        if(e==null)
            return null;

        int cpt=0;
        while(e.getPrec() !=null){
            cpt++;
            e=e.getPrec();
        }
        String pos= new String("(" +key+"."+cpt+")");
        return pos;
    }


    public String rechercheType(String nom, String genre, String portee){

        int key=hashVal(nom);

        if(table[key]==null){
            return null;//virer la ligne quand exception done
          //  throw new NomNotInTableException("objet <"+nom+", "+genre+" ,"+portee+"> non trouve dans la table des symboles ");
        }

		String sorte = table[key].rechercheType(nom,genre,portee);

		if(sorte == null){
			String sup_portee = this.recherchePortee(portee, "meth");
			if(sup_portee==null){
                            return null;//degage
				//throw new IdNonDansTableSymboleException("Objet <" + id + "," + obj + "," + portee + "> non trouve dans la table des symboles !");
			}else{
				return this.rechercheType(nom, genre, sup_portee);
			}
		}else{
			return sorte;
		}

	}

	public String recherchePortee(String nom, String genre){// throws IdNonDansTableSymboleException {

		int key = hashVal(nom);

		if(table[key]==null){
                    return null;//pareil
			//throw new IdNonDansTableSymboleException("Objet <" + nom + "," + genre + "> non trouve dans la table des symboles !");
		}

		String e = table[key].recherchePortee(nom,genre);

		if(e == null){
                        return null;
		//	throw new NomNonDansTableSymboleException("Objet <" + nom + "," + genre + "> non trouve dans la table des symboles !");
		}else{
			return e;
		}
			
	}



	public String toString(){
		String ret="Table Symbole : \n";
		for(int i=0;i<table.length;i++){
			if(table[i]!=null){
				ret+="   ["+i+"] : "+table[i].toString()+"\n";
			}
		}
		return ret;
	}


}