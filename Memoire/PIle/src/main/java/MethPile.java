public class MethPile extends ElementMemoire {

	
	public MethPile(String nom, MethValue b,Genre tp, Type ttp){	
		
		quadruplet = new Quadruplet(nom,b,tp,ttp, null); 
	}

	public String toString(){
		return quadruplet.toString();		
	}

	
}
