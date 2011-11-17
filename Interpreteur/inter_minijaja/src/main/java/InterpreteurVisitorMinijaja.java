
/**
 *
 * @author hermes
 */
public class InterpreteurVisitorMinijaja implements MiniJajaVisitor {
	MiniJaja Asa_Minijaja;
	Pile pile;
    Tas tas;

    // Permet de gerer le pas a pas.
  //  Boolean thread_sleep = false;

  public InterpreteurVisitorMinijaja(Pile pile, Tas tas, MiniJaja Asa_Minijaja) {

		this. Asa_Minijaja = Asa_Minijaja;
		this.tas = tas;
		this.pile = pile;
        System.out.println("on instancie l'InterperteurVisitorMiniJaja");
  }

  public Object visit(SimpleNode node, Object data) throws MiniJajaVisitorException {
			/*if (thread_sleep == true) {
				this.wait();
			}*/
      System.out.println("On passe par SimpleNode");
	  return null;
  }

  public Object visit(ASTclasse node, Object data) throws MiniJajaVisitorException {
      System.out.println("On passe par ASTCLASSE");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTident node, Object data) throws MiniJajaVisitorException {
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      System.out.println("On passe par ASTIDENT");
      System.out.println(node.getValeur());
      return node.getValeur();
  }

  public Object visit(ASTdecls node, Object data) throws MiniJajaVisitorException {
      System.out.println("On passe par ASTDecls");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTvnil node, Object data) throws MiniJajaVisitorException {

      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTcst node, Object data) throws MiniJajaVisitorException {

      String name;
      String value;

      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      
     name = (String) node.jjtGetChild(1).jjtAccept(this, data);
     value = (String) node.jjtGetChild(2).jjtAccept(this, data);

      if(node.jjtGetChild(0).toString() == "booleen") {
          pile.getPile().push(new BooleenPile(name, new BooleanValue(value), new GenreCst(), new TypeBoolean()));
      } else if (node.jjtGetChild(0).toString() == "entier") {
          pile.getPile().push(new EntierPile(name, new EntierValue(value), new GenreCst(), new TypeEntier()));
      }

      System.out.println(pile.getPile().peek().toString());
      return true;
  }

  public Object visit(ASTtableau node, Object data) throws MiniJajaVisitorException {
      System.out.println("On passe par ASTtableau");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTmethode node, Object data) throws MiniJajaVisitorException{
      
      String name;
      String value;
/*
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
*/
     name = (String) node.jjtGetChild(1).jjtAccept(this, data);
    // value = (String) node.jjtGetChild(2).jjtAccept(this, data);

      if(node.jjtGetChild(0).toString() == "booleen") {
          pile.getPile().push(new MethPile(name, new MethValue("w"), new GenreMeth(), new TypeBoolean()));
      } else if (node.jjtGetChild(0).toString() == "entier") {
          pile.getPile().push(new MethPile(name, new MethValue("w"), new GenreMeth(), new TypeEntier()));
      }
      else if (node.jjtGetChild(0).toString() == "rien") {
          pile.getPile().push(new MethPile(name, new MethValue("w"), new GenreMeth(), new TypeVoid()));
      }

      System.out.println(pile.getPile().peek().toString());
      return true;
  }

  public Object visit(ASTvar node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTvar");

      String name;
      String value;

      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }

     name = (String) node.jjtGetChild(1).jjtAccept(this, data);
     value = (String) node.jjtGetChild(2).jjtAccept(this, data);

      if(node.jjtGetChild(0).toString() == "booleen") {
          pile.getPile().push(new BooleenPile(name, new BooleanValue(value), new GenreVar(), new TypeBoolean()));
      } else if (node.jjtGetChild(0).toString() == "entier") {
          pile.getPile().push(new EntierPile(name, new EntierValue(value), new GenreVar(), new TypeEntier()));
      }

      System.out.println(pile.getPile().peek().toString());
      return true;
  }

  public Object visit(ASTvars node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTvars");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }

      return true;
  }

  public Object visit(ASTomega node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTomega");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return "w";
  }

  public Object visit(ASTmain node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTmain");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTentetes node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTententes");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTenil node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTenil");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTentete node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTentete");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTinstrs node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTinstrs");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTinil node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTinil");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTretour node, Object data) throws MiniJajaVisitorException{
     System.out.println("On passe par ASTretour");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTtantque node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTwhile");
     /* int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      */
      if (node.jjtGetChild(0).jjtAccept(this, data).equals(true)){
          node.jjtGetChild(1).jjtAccept(this, data);
          node.jjtAccept(this, data);
      }
      
      
      return true;
  }

  public Object visit(ASTsi node, Object data) throws MiniJajaVisitorException{
      
      System.out.println("On passe par ASTSI");
    /*  int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
     */ 
      
     if  (node.jjtGetChild(0).jjtAccept(this, data).equals(true)){
         node.jjtGetChild(1).jjtAccept(this, data);
     }
     else if (node.jjtGetChild(0).jjtAccept(this, data).equals(false)){
         node.jjtGetChild(2).jjtAccept(this, data);
     }
      
      
      return true;
  }
  
  
  
  public Object visit(ASTappelI node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTappI");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTaffectation node, Object data) throws MiniJajaVisitorException{
      
      System.out.println("On passe par ASTaffec");
      /*
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
*/
      String ident_affecation;
      String eval_affectation;

    ident_affecation = (String) node.jjtGetChild(0).jjtAccept(this, data);
    System.out.println("IDENT AFFECTATION "+ident_affecation);
    
  //  System.out.println("VALEUR AFFECTATION "+node.jjtGetChild(1).jjtAccept(this, data));
    eval_affectation = ""+node.jjtGetChild(1).jjtAccept(this, data); //(String) node.jjtGetChild(1).jjtAccept(this, data);
   

    System.out.println(node.jjtGetChild(1).toString());

    
    if(node.jjtGetChild(1).toString() == "nbre") {
       pile.searchident(ident_affecation).getQuad().setValue(new EntierValue(eval_affectation));
    }
    
    
    if(node.jjtGetChild(1).toString() == "boolean") {
       pile.searchident(ident_affecation).getQuad().setValue(new BooleanValue(eval_affectation));
    }

    if(node.jjtGetChild(1).toString() == "plus" || node.jjtGetChild(1).toString() == "moins" || node.jjtGetChild(1).toString() == "mult" || node.jjtGetChild(1).toString() == "div" ) {
       System.out.println("BORDELDEMERDE " + eval_affectation );
       ElementMemoire e = pile.searchident(ident_affecation);
       e.getQuad().setValue(new EntierValue(eval_affectation));
    }
   
    if(node.jjtGetChild(1).toString() == "Meth") {
       pile.searchident(ident_affecation).getQuad().setValue(new MethValue(eval_affectation));
    }
    
     System.out.println(pile.getPile().peek().toString());
     
    return true;
    
  }

  public Object visit(ASTsomme node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTsomme");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTincrement node, Object data) throws MiniJajaVisitorException{

      System.out.println("On passe par ASTinc");int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      
      String ident_affecation = (String) node.jjtGetChild(0).jjtAccept(this, data);
      int nouvelleval = pile.searchident(ident_affecation).getQuad().getValeur()+1;
      String eval_affectation = ""+nouvelleval;
      pile.searchident(ident_affecation).getQuad().setValue(new EntierValue(eval_affectation));
      System.out.println(pile.getPile().peek().toString());
      
      return true;
  }

  public Object visit(ASTtab node, Object data) throws MiniJajaVisitorException{
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTlistexp node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTexp");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTexnil node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTexil");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTnon node, Object data) throws MiniJajaVisitorException{
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTneg node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTng");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTet node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTet");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTou node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTou");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTegal node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTegal");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTinf node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTinf");

      
      
            int operateur1;
      int operateur2;
         
      
      if (node.jjtGetChild(0).toString().equals("ident")){
        operateur1 = eval((String)node.jjtGetChild(0).jjtAccept(this, data));
      }
      else{
        if (node.jjtGetChild(0).toString().equals("nbre")){
            operateur1 = Integer.parseInt((String)node.jjtGetChild(0).jjtAccept(this, data)); 
        }
        else{
            operateur1 = Integer.parseInt(""+node.jjtGetChild(0).jjtAccept(this, data));
        }
      }
      
      
      if (node.jjtGetChild(1).toString().equals("ident")){
        operateur2 = eval((String)node.jjtGetChild(1).jjtAccept(this, data));
      }
      else{   
        if (node.jjtGetChild(1).toString().equals("nbre")){
            operateur2 = Integer.parseInt((String)node.jjtGetChild(1).jjtAccept(this, data)); 
        }
        else{
            operateur2 = Integer.parseInt(""+node.jjtGetChild(1).jjtAccept(this, data));
        }
      }
      
      if (operateur1 < operateur2 ){
          return true;
      }
      else{
          return false;
      }
      
      
  }

  public Object visit(ASTsup node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTsup");

      
      int operateur1;
      int operateur2;
         
      
      if (node.jjtGetChild(0).toString().equals("ident")){
        operateur1 = eval((String)node.jjtGetChild(0).jjtAccept(this, data));
      }
      else{
        if (node.jjtGetChild(0).toString().equals("nbre")){
            operateur1 = Integer.parseInt((String)node.jjtGetChild(0).jjtAccept(this, data)); 
        }
        else{
            operateur1 = Integer.parseInt(""+node.jjtGetChild(0).jjtAccept(this, data));
        }
      }
      
      
      if (node.jjtGetChild(1).toString().equals("ident")){
        operateur2 = eval((String)node.jjtGetChild(1).jjtAccept(this, data));
      }
      else{   
        if (node.jjtGetChild(1).toString().equals("nbre")){
            operateur2 = Integer.parseInt((String)node.jjtGetChild(1).jjtAccept(this, data)); 
        }
        else{
            operateur2 = Integer.parseInt(""+node.jjtGetChild(1).jjtAccept(this, data));
        }
      }
      
      if (operateur1 > operateur2 ){
          return true;
      }
      else{
          return false;
      }
      
      
      
  }

  public Object visit(ASTplus node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTplus");
/*      
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
*/
      int operateur1;
      int operateur2;
         
      
      if (node.jjtGetChild(0).toString().equals("ident")){
        operateur1 = eval((String)node.jjtGetChild(0).jjtAccept(this, data));
      }
      else{
        if (node.jjtGetChild(0).toString().equals("nbre")){
            operateur1 = Integer.parseInt((String)node.jjtGetChild(0).jjtAccept(this, data)); 
        }
        else{
            operateur1 = Integer.parseInt(""+node.jjtGetChild(0).jjtAccept(this, data));
        }
      }
      
      
      if (node.jjtGetChild(1).toString().equals("ident")){
        operateur2 = eval((String)node.jjtGetChild(1).jjtAccept(this, data));
      }
      else{   
        if (node.jjtGetChild(1).toString().equals("nbre")){
            operateur2 = Integer.parseInt((String)node.jjtGetChild(1).jjtAccept(this, data)); 
        }
        else{
            operateur2 = Integer.parseInt(""+node.jjtGetChild(1).jjtAccept(this, data));
        }
      }
      

      return (int)operateur1+operateur2;
      
      
  }

  public Object visit(ASTmoins node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTmoins");

      
      
      int operateur1;
      int operateur2;
         
      
      if (node.jjtGetChild(0).toString().equals("ident")){
        operateur1 = eval((String)node.jjtGetChild(0).jjtAccept(this, data));
      }
      else{
        if (node.jjtGetChild(0).toString().equals("nbre")){
            operateur1 = Integer.parseInt((String)node.jjtGetChild(0).jjtAccept(this, data)); 
        }
        else{
            operateur1 = Integer.parseInt(""+node.jjtGetChild(0).jjtAccept(this, data));
        }
      }
      
      
      if (node.jjtGetChild(1).toString().equals("ident")){
        operateur2 = eval((String)node.jjtGetChild(1).jjtAccept(this, data));
      }
      else{   
        if (node.jjtGetChild(1).toString().equals("nbre")){
            operateur2 = Integer.parseInt((String)node.jjtGetChild(1).jjtAccept(this, data)); 
        }
        else{
            operateur2 = Integer.parseInt(""+node.jjtGetChild(1).jjtAccept(this, data));
        }
      }
      
      

      return  (int)operateur1-operateur2;
  }

  public Object visit(ASTmult node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTmult");

      
      int operateur1;
      int operateur2;
         
      
      if (node.jjtGetChild(0).toString().equals("ident")){
        operateur1 = eval((String)node.jjtGetChild(0).jjtAccept(this, data));
      }
      else{
        if (node.jjtGetChild(0).toString().equals("nbre")){
            operateur1 = Integer.parseInt((String)node.jjtGetChild(0).jjtAccept(this, data)); 
        }
        else{
            operateur1 = Integer.parseInt(""+node.jjtGetChild(0).jjtAccept(this, data));
        }
      }
      
      
      if (node.jjtGetChild(1).toString().equals("ident")){
        operateur2 = eval((String)node.jjtGetChild(1).jjtAccept(this, data));
      }
      else{   
        if (node.jjtGetChild(1).toString().equals("nbre")){
            operateur2 = Integer.parseInt((String)node.jjtGetChild(1).jjtAccept(this, data)); 
        }
        else{
            operateur2 = Integer.parseInt(""+node.jjtGetChild(1).jjtAccept(this, data));
        }
      }
      
      

      return  (int)operateur1*operateur2;
      
  }

  public Object visit(ASTdiv node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTdiv");

      
      
      int operateur1;
      int operateur2;
         
      
      if (node.jjtGetChild(0).toString().equals("ident")){
        operateur1 = eval((String)node.jjtGetChild(0).jjtAccept(this, data));
      }
      else{
        if (node.jjtGetChild(0).toString().equals("nbre")){
            operateur1 = Integer.parseInt((String)node.jjtGetChild(0).jjtAccept(this, data)); 
        }
        else{
            operateur1 = Integer.parseInt(""+node.jjtGetChild(0).jjtAccept(this, data));
        }
      }
      
      
      if (node.jjtGetChild(1).toString().equals("ident")){
        operateur2 = eval((String)node.jjtGetChild(1).jjtAccept(this, data));
      }
      else{   
        if (node.jjtGetChild(1).toString().equals("nbre")){
            operateur2 = Integer.parseInt((String)node.jjtGetChild(1).jjtAccept(this, data)); 
        }
        else{
            operateur2 = Integer.parseInt(""+node.jjtGetChild(1).jjtAccept(this, data));
        }
      }
      
      

      return  (int)operateur1/operateur2;
  }

  public Object visit(ASTvrai node, Object data) throws MiniJajaVisitorException {
      System.out.println("On passe par ASTvrai");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
	  return "true";
  }

  public Object visit(ASTfaux node, Object data) throws MiniJajaVisitorException {
      System.out.println("On passe par ASTfaux");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
	  return "false";
  }

  public Object visit(ASTappelE node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTappelE");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTnbre node, Object data) throws MiniJajaVisitorException {
      System.out.println("On passe par ASTnbre");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      System.out.println(node.getValeur());
      return String.valueOf(node.getValeur());
  }

  public Object visit(ASTrien node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTrien");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTentier node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTEntier");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return true;
  }

  public Object visit(ASTbooleen node, Object data) throws MiniJajaVisitorException {
    System.out.println("On passe par ASTBool");
    return true;
  }





  
  public int eval( String s){    
      System.out.println(pile.AfficherPile());
      
      return pile.searchident(s).getQuad().getValeur();    
  }


}
