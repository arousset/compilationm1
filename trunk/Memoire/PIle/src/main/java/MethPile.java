public class MethPile extends ElementMemoire {

	
	public MethPile(String nom, MethValue b,Genre tp, Type ttp, String conteneur){	
		
		quadruplet = new Quadruplet(nom,b,tp,ttp, conteneur); 
	}

	public String toString(){
		return quadruplet.toString();		
	}

	
}
