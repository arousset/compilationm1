public class BooleenPile extends ElementMemoire{

	
	public BooleenPile(String nom, BooleanValue b,Genre tp, TypeBoolean ttp){	
		quadruplet = new Quadruplet(nom,b,tp,ttp, null); 
	}

	public String toString(){
		return quadruplet.toString();		
	}

	
}
