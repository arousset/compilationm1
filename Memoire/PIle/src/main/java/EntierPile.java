public class EntierPile extends ElementMemoire{

	
	public EntierPile(String nom, EntierValue b,Genre tp, TypeEntier ttp){	
		
		quadruplet = new Quadruplet(nom,b,tp,ttp, null); 
	}

	public String toString(){
		return quadruplet.toString();		
	}

}
