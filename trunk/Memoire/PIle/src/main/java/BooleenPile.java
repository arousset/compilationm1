public class BooleenPile extends ElementMemoire{

	
	public BooleenPile(String nom, BooleanValue b,Genre tp, TypeBoolean ttp, String conteneur){	
		quadruplet = new Quadruplet(nom,b,tp,ttp, conteneur); 
	}

	public String toString(){
		return quadruplet.toString();		
	}

	
}
