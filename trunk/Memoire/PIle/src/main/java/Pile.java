import java.util.Stack;
import java.util.Vector;


public class Pile {

        public boolean change = false;
    
	private Stack <ElementMemoire> pile;
	
	
	public Pile(){
		pile=new Stack<ElementMemoire>();
	}



	public Stack<ElementMemoire> getPile() {
		return pile;
	}


	public void setPile(Stack<ElementMemoire> pile) {
		this.pile = pile;
	}
	
	public void swap() {//throws PileVideException{
		
              /*  if(pile.empty()){
                     throw new PileVideException("pile vide, swap impossible");
                }*/
                ElementMemoire tmp = pile.peek();
		pile.pop();
               /* if(pile.empty()){
                     throw new PileVideException("pile vide, swap impossible");
                }*/
		ElementMemoire tmp2 = pile.peek();
		pile.pop();
		pile.push(tmp);
		pile.push(tmp2);	
	}

        public ElementMemoire searchident(String s){
        ElementMemoire retour=null;
            for (int i = 0;i<pile.size();i++){
                if (pile.get(i).getQuad().getIdent().equals(s)){                  
                    retour = pile.get(i);
                }
            }
        return retour;
        }
        
        
        public boolean estpresent(String s){
        
            for (int i = 0;i<pile.size();i++){
                if (pile.get(i).getQuad().getIdent().equals(s)){                  
                    return true;
                }
            }
            return false;
        } 
        
        
        public void supprimer(String s, Tas_Tas t){
                for (int i = 0;i<pile.size();i++){
                    
                  if (pile.get(i).getQuad().getConteneur().equals(s)){
                      if (pile.get(i).getQuad().getGenre().equals("tab")){                        
                          t.Tas_supprimerTableau(pile.get(i).getQuad().getValeur());                                                 
                      }
                      pile.remove(i);
                      i--;
                  }                 
                }         
        
        }
        
        
        public String AfficherPile(){
            String retour = "";
            for (int i = 0;i<pile.size();i++){
                retour = retour + pile.get(i).toString() + "\n";                 
                }         
        return retour;
        }

        public Vector<String> get_PileV() {
            Vector<String> vs = new Vector<String>();
            int i =0;
            System.out.println(pile.size());
            while (i< pile.size()) {
                vs.add(pile.get(i).toString());
                i++;
            }
            return vs;
        }
	
}


/*   1. un constructeur sans argument : public Stack();
2. pour empiler :
public Object push(Object item);
3. pour dépiler :
public synchronized Object pop();
4. pour accéder au sommet de la pile (sans le supprimer) :
public synchronized Object peek();
5. pour tester si la pile est vide :
public boolean empty();
6. pour rechercher un objet dans la pile :
public synchronized int search(Object o);
*/