import java.util.Stack;


public class Pile {

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
	
	public void swap(){
		ElementMemoire tmp = pile.peek();
		pile.pop();
		ElementMemoire tmp2 = pile.peek();
		pile.pop();
		pile.push(tmp);
		pile.push(tmp2);	
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