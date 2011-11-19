public class EntierPile extends ElementMemoire{

	
	public EntierPile(String nom, EntierValue b,Genre tp, TypeEntier ttp, String conteneur){		
		quadruplet = new Quadruplet(nom,b,tp,ttp, conteneur); 
	}

	public String toString(){
		return quadruplet.toString();		
	}

}
