public class ClassPile extends ElementMemoire{

	
	public ClassPile(String nom, ClassValue b,GenreClass tp, TypeClass ttp, String conteneur){	
		quadruplet = new Quadruplet(nom,b,tp,ttp, conteneur); 
	}

	public String toString(){
		return quadruplet.toString();		
	}

	
}
