public class BooleenPile extends ElementMemoire{

	
	public BooleenPile(String nom, boolean b,Genre tp, Type ttp){	
		quadruplet = new Quadruplet(nom,b,tp,ttp, null); 
	}

	public String toString(){
		return quadruplet.toString();		
	}

	
}
