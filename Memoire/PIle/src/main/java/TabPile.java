public class TabPile extends ElementMemoire{

	
	public TabPile(String nom, TabValue b,Genre tp, Type ttp, String conteneur){	
		quadruplet = new Quadruplet(nom,b,tp,ttp, conteneur); 
	}

	public String toString(){
		return quadruplet.toString();		
	}

	
}
