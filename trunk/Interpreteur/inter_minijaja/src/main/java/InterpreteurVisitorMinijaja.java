
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author ROUSSET Alban & Lucas PERRONNE
 */




public class InterpreteurVisitorMinijaja implements MiniJajaVisitor {
	MiniJaja Asa_Minijaja;
	Pile pile;
        Tas_Tas tas;
        boolean pause = true;
        Inter interpretor;
    // Permet de gerer le pas a pas.
  //  Boolean thread_sleep = false;
           
        
     


  public InterpreteurVisitorMinijaja(Pile pile, Tas_Tas tas, MiniJaja Asa_Minijaja, Inter interpretor) {

		this. Asa_Minijaja = Asa_Minijaja;
		this.tas = tas;
		this.pile = pile; 
                this.interpretor = interpretor;
        System.out.println("on instancie l'InterperteurVisitorMiniJaja");
  }
  
  public void next(){
    pause = false;
  }    
  
  public Object visit(SimpleNode node, Object data) throws MiniJajaVisitorException {
			/*if (thread_sleep == true) {
				this.wait();
			}*/
      System.out.println("On passe par SimpleNode");
	  return null;
  }

  public Object visit(ASTclasse node, Object data) throws MiniJajaVisitorException {

      System.out.println("on est content");
      if (pause == true){
            try {
                interpretor.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(InterpreteurVisitorMinijaja.class.getName()).log(Level.SEVERE, null, ex);
            }
      } 
        System.out.println("on est joyeux");  
          
      System.out.println("On passe par ASTCLASSE");

      
     String name;

     name = (String) node.jjtGetChild(0).jjtAccept(this, data);

     pile.getPile().push(new ClassPile(name, new ClassValue("w"), new GenreClass(), new TypeClass(), name));

      System.out.println(pile.AfficherPile());
     
      node.jjtGetChild(1).jjtAccept(this, data);
      node.jjtGetChild(2).jjtAccept(this, data);
      
      
     // RETIRER QUADRUPLET CLASSE ET TAS
      
      
      
      
   // ENLEVER LES 2 COMMENTAIRES SUIVANTS   
      
    // pile.supprimer(name,tas);
    // tas.Tas_dump();
      
      
      
     System.out.println("hahaha" + tas.get_Tas());
     System.out.println("hahaha" + pile.AfficherPile());
      
      return "";
      
      
      
      
  }

  public Object visit(ASTident node, Object data) throws MiniJajaVisitorException {

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
      return "";
  }

  public Object visit(ASTvnil node, Object data) throws MiniJajaVisitorException {
      return "";
  }

  public Object visit(ASTcst node, Object data) throws MiniJajaVisitorException {

      String name;
      String value;
      
     name = (String) node.jjtGetChild(1).jjtAccept(this, data);
     value = (String) node.jjtGetChild(2).jjtAccept(this, data);

     
     
      Node n = node.jjtGetParent();
      while (n.toString() != "classe" && n.toString() != "methode" && n.toString() != "main" ){
          n=n.jjtGetParent();
      }
      
      String ident_conteneur;
      if (n.toString() == "classe" ){
          ident_conteneur = ""+n.jjtGetChild(0).jjtAccept(this, data);
      }else{
          if (n.toString() == "methode" ){
              ident_conteneur = ""+n.jjtGetChild(1).jjtAccept(this, data);
          }
          else{
              ident_conteneur = "main";
          }
      }
     
      if(node.jjtGetChild(0).toString() == "booleen") {
          pile.getPile().push(new BooleenPile(name, new BooleanValue(value), new GenreCst(), new TypeBoolean(), ident_conteneur));
      } else if (node.jjtGetChild(0).toString() == "entier") {
          pile.getPile().push(new EntierPile(name, new EntierValue(value), new GenreCst(), new TypeEntier(), ident_conteneur));
      }

      System.out.println(pile.AfficherPile());
      return "";
  }

  
  public Object visit(ASTtableau node, Object data) throws MiniJajaVisitorException {
      System.out.println("On passe par ASTtableau");
      
      
      String type_tab;
      
      
      if (node.jjtGetChild(0).toString().equals("entier")){
          type_tab = "entier";
      }
      else{
         type_tab = "booleen"; 
      }
      
      String iden_tab = ""+node.jjtGetChild(1).jjtAccept(this, data);
      String nombre_de_case_tab = ""+ node.jjtGetChild(2).jjtAccept(this, data);
      
      
      
      int adressetas = tas.Tas_allouerTableau(iden_tab, type_tab, Integer.parseInt(nombre_de_case_tab));

        
      
      Node n = node.jjtGetParent();
      while (n.toString() != "classe" && n.toString() != "methode" && n.toString() != "main" ){
          n=n.jjtGetParent();
      }
      
      String ident_conteneur;
      if (n.toString() == "classe" ){
          ident_conteneur = ""+n.jjtGetChild(0).jjtAccept(this, data);
      }else{
          if (n.toString() == "methode" ){
              ident_conteneur = ""+n.jjtGetChild(1).jjtAccept(this, data);
          }
          else{
              ident_conteneur = "main";
          }
      }
        
        
      if (type_tab == "entier"){
          System.out.println( " ONESTBON " );
        pile.getPile().push(new TabPile(iden_tab,new TabValue(adressetas),new GenreTab(),new TypeEntier(), ident_conteneur ));                          
      }   
      if (type_tab == "booleen"){
          System.out.println( " 2159198199819581 " );
        pile.getPile().push(new TabPile(iden_tab,new TabValue(adressetas),new GenreTab(),new TypeBoolean(), ident_conteneur ));                          
      }       
      
      
      System.out.println("type tab : " + type_tab + " ident tab " + iden_tab + " nombre de case " + nombre_de_case_tab);
      
      System.out.println(pile.AfficherPile());
      System.out.println(tas.get_Tas());
      return "";
  }

  public Object visit(ASTmethode node, Object data) throws MiniJajaVisitorException{
      
      String name;
      String value;

     name = (String) node.jjtGetChild(1).jjtAccept(this, data);
    // value = (String) node.jjtGetChild(2).jjtAccept(this, data);

     
     
     
           Node n = node.jjtGetParent();
      while (n.toString() != "classe" && n.toString() != "methode" && n.toString() != "main" ){
          n=n.jjtGetParent();
      }
      
      String ident_conteneur;
      if (n.toString() == "classe" ){
          ident_conteneur = ""+n.jjtGetChild(0).jjtAccept(this, data);
      }else{
          if (n.toString() == "methode" ){
              ident_conteneur = ""+n.jjtGetChild(1).jjtAccept(this, data);
          }
          else{
              ident_conteneur = "main";
          }
      }
     
     
 
     
     
      if(node.jjtGetChild(0).toString() == "booleen") {
          pile.getPile().push(new MethPile(name, new MethValue(node), new GenreMeth(), new TypeBoolean(), ident_conteneur));
      } else if (node.jjtGetChild(0).toString() == "entier") {
          System.out.println("ENCULAGE");
          pile.getPile().push(new MethPile(name, new MethValue(node), new GenreMeth(), new TypeEntier(), ident_conteneur));
      }
      else if (node.jjtGetChild(0).toString() == "rien") {
          pile.getPile().push(new MethPile(name, new MethValue(node), new GenreMeth(), new TypeVoid(), ident_conteneur));
      }

     System.out.println(pile.AfficherPile());
      return "";
  }

  public Object visit(ASTvar node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTvar");

      String name;
      String value;

     name = (String) node.jjtGetChild(1).jjtAccept(this, data);
     value = ""+ node.jjtGetChild(2).jjtAccept(this, data);

     
         
           Node n = node.jjtGetParent();
      while (n.toString() != "classe" && n.toString() != "methode" && n.toString() != "main" ){
          System.out.println(n);
          n=n.jjtGetParent();
      }
      
      String ident_conteneur;
      if (n.toString() == "classe" ){
          ident_conteneur = ""+n.jjtGetChild(0).jjtAccept(this, data);
      }else{
          if (n.toString() == "methode" ){
              ident_conteneur = ""+n.jjtGetChild(1).jjtAccept(this, data);
          }
          else{
              ident_conteneur = "main";
          }
      } 
     
    
     
     
      if(node.jjtGetChild(0).toString() == "booleen") {
          pile.getPile().push(new BooleenPile(name, new BooleanValue(value), new GenreVar(), new TypeBoolean(),ident_conteneur));
      } else if (node.jjtGetChild(0).toString() == "entier") {
          pile.getPile().push(new EntierPile(name, new EntierValue(value), new GenreVar(), new TypeEntier(), ident_conteneur));
      }

      System.out.println(pile.AfficherPile());
      return "";
  }

  public Object visit(ASTvars node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTvars");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }

      return "";
  }

  public Object visit(ASTomega node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTomega");
      return "w";
  }

  public Object visit(ASTmain node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTmain");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      
      
      // RETIRER TOUTES LES DECLARATION DU MAIN
      
         // ENLEVER LE COMMENTAIRE SUIVANT      
        //   pile.supprimer("main", tas);
      
      
     System.out.println(pile.AfficherPile());
      
      return "";
  }

  public Object visit(ASTentetes node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTententes");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return "";
  }

  public Object visit(ASTenil node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTenil");
      return "";
  }

  public Object visit(ASTentete node, Object data) throws MiniJajaVisitorException{
      
      System.out.println("On passe par ASTentete");
      String name;
      String value;

     name = (String) node.jjtGetChild(1).jjtAccept(this, data);
     value = "w";

              
      Node n = node.jjtGetParent();
      while (n.toString() != "classe" && n.toString() != "methode" && n.toString() != "main" ){
          n=n.jjtGetParent();
      }
      
      String ident_conteneur;
      if (n.toString() == "classe" ){
          ident_conteneur = ""+n.jjtGetChild(0).jjtAccept(this, data);
      }else{
          if (n.toString() == "methode" ){
              ident_conteneur = ""+n.jjtGetChild(1).jjtAccept(this, data);
          }
          else{
              ident_conteneur = "main";
          }
      } 
     
     
     
     
     
      if(node.jjtGetChild(0).toString() == "booleen") {
          pile.getPile().push(new BooleenPile(name, new BooleanValue(value), new GenreVar(), new TypeBoolean(), ident_conteneur));
      } else if (node.jjtGetChild(0).toString() == "entier") {
          pile.getPile().push(new EntierPile(name, new EntierValue(value), new GenreVar(), new TypeEntier(), ident_conteneur));
      }

     System.out.println(pile.AfficherPile());
      return "";
      
  }

  public Object visit(ASTinstrs node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTinstrs");
  
          String retour1 = ""+node.jjtGetChild(0).jjtAccept(this, data);         
          String retour2 = ""+node.jjtGetChild(1).jjtAccept(this, data);
          

           
          if (node.jjtGetChild(0).toString() == "retour" ){   
              if(pile.estpresent(retour1)){                 
                      return "ilsagitdelafeinteduretourancestrale"+ pile.searchident(""+retour1).getQuad().getValeurMethode();    
              }
              else{
                return "ilsagitdelafeinteduretourancestrale"+ retour1;  
              }
          }
                   
          if (retour1.length() > 35 ){
          
               System.out.println("passage dans le noeud INSTRS " + retour1);
              if (retour1.substring(0,36) == "ilsagitdelafeinteduretourancestrale"){
                  return retour1;
              }
          
          }        

          if (node.jjtGetChild(1).toString() == "retour" ){   
              if(pile.estpresent(retour2)){                 
                      return "ilsagitdelafeinteduretourancestrale"+ pile.searchident(""+retour2).getQuad().getValeurMethode();    
              }
              else{
                return "ilsagitdelafeinteduretourancestrale"+ retour2;  
              }
          }
          
                   
          if (retour1.length() > 35 ){
          
               System.out.println("passage dans le noeud INSTRS " + retour2);
              if (retour1.substring(0,36) == "ilsagitdelafeinteduretourancestrale"){
                  return retour1;
              }
          
          }    
          

                System.out.println("RETOUR : " + retour1 + "" + retour2 ); 
      
      return "" + retour1 + retour2;


  }
  
  public Object visit(ASTinil node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTinil");

      return "";
  }

  public Object visit(ASTretour node, Object data) throws MiniJajaVisitorException{
     System.out.println("On passe par ASTretour");    
      return node.jjtGetChild(0).jjtAccept(this, data);
  }

  
  public Object visit(ASTtantque node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTwhile");

      if (node.jjtGetChild(0).jjtAccept(this, data).equals(true)){
          node.jjtGetChild(1).jjtAccept(this, data);
          node.jjtAccept(this, data);
      }
      
      
      return "";
  }

  public Object visit(ASTsi node, Object data) throws MiniJajaVisitorException{
      
      System.out.println("On passe par ASTSI");

      
     if  (node.jjtGetChild(0).jjtAccept(this, data).equals(true) || node.jjtGetChild(0).jjtAccept(this, data).equals("true")){         
         return node.jjtGetChild(1).jjtAccept(this, data);
     }
     else 
         {
         return node.jjtGetChild(2).jjtAccept(this, data);
     }
      
  }
  
  
  
  public Object visit(ASTappelI node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTappI");

   
      String ident_fonction = (String) node.jjtGetChild(0).jjtAccept(this, data);
      ElementMemoire e = pile.searchident(ident_fonction);
      ASTmethode n  = (ASTmethode) e.getQuad().getValeurMethode();
      

      
      int taille_avant_decl = pile.getPile().size();
      n.jjtGetChild(2).jjtAccept(this, data);
      int taille_apres_decl = pile.getPile().size();
      
      System.out.println("nombre_de_nouvelles_valeurs = "+(taille_apres_decl - taille_avant_decl));
      
      ASTlistexp o = (ASTlistexp) node.jjtGetChild(1);
      
      
      System.out.println("--------------------------");
     System.out.println(pile.AfficherPile());
      
      
      for (int i=taille_avant_decl;i<taille_apres_decl;i++){     
                  
          
          if ( o.jjtGetChild(0).toString() == "ident"){
              ElementMemoire em = pile.searchident(""+o.jjtGetChild(0).jjtAccept(this, data));
              String em_type = em.getQuad().getType(); 
              String value_param = (String) em.getQuad().getValeurMethode();
              if (em_type == "Entier"){
                  pile.getPile().get(i).getQuad().setValue(new EntierValue(value_param));
              }
              if (em_type == "Boolean"){
                  pile.getPile().get(i).getQuad().setValue(new BooleanValue(value_param));                    
              }
          }
          else{           
              if (pile.getPile().get(i).getQuad().getType() == "Entier"){
                  pile.getPile().get(i).getQuad().setValue(new EntierValue(""+o.jjtGetChild(0).jjtAccept(this, data)));
              }
              if (pile.getPile().get(i).getQuad().getType() == "Boolean"){
                  pile.getPile().get(i).getQuad().setValue(new BooleanValue(""+o.jjtGetChild(0).jjtAccept(this, data)));                    
              }
                       
          }

        if (o.jjtGetChild(1).toString() != "exnil"){
           o=  (ASTlistexp) o.jjtGetChild(1); 
        }        
      }
      
      
      System.out.println("avant " + taille_avant_decl + " apres " + taille_apres_decl);
      
      
        //(t, ident(i), ent, dvs, iss) 
           
      
     n.jjtGetChild(3).jjtAccept(this, data);
     
     
      System.out.println("--------------------------");
     System.out.println(pile.AfficherPile());
              
     // Node n = e.getQuad().getValeur();
      System.out.println("LE JOLI IDENT " + ident_fonction);
 

       n.jjtGetChild(4).jjtAccept(this, data);
       
       // ENLEVER LE COMMENTAIRE SUIVANT   
       
       // pile.supprimer(ident_fonction,tas);
       return "";
     
     
     
  }

  public Object visit(ASTaffectation node, Object data) throws MiniJajaVisitorException{
      
      System.out.println("On passe par ASTaffec");

      String ident_affecation;
      String eval_affectation;

      
      
      if (node.jjtGetChild(0).toString().equals("tab")){
          ident_affecation = ""+node.jjtGetChild(0).jjtGetChild(0).jjtAccept(this, data);

          String case_tab = ""+node.jjtGetChild(0).jjtGetChild(1).jjtAccept(this, data);
          
          if (pile.estpresent(case_tab)){
              case_tab = ""+pile.searchident(case_tab).getQuad().getValeur();
          }
          
          int case_tab_int = Integer.parseInt(case_tab);
          
          
          eval_affectation = ""+node.jjtGetChild(1).jjtAccept(this, data); 
          
          if (node.jjtGetChild(1).toString() == "ident"){                    
              eval_affectation = (""+pile.searchident(eval_affectation).getQuad().getValeurMethode());
          }
              
          //(String) node.jjtGetChild(1).jjtAccept(this, data);
   

    System.out.println("EVALUATION DE LAFFECTATION :      -----   "+ eval_affectation);
        
     System.out.println("ADRESSE DU TABLEAU : " + ident_affecation +" CASETAB " + case_tab_int + " EVAL AFFECTATION "+ eval_affectation);   
     
         int value_tab = pile.searchident(ident_affecation).getQuad().getValeur();
         tas.Tas_affecterValeur(value_tab, case_tab_int, eval_affectation);    

       
   
       System.out.println(pile.AfficherPile());
       System.out.println(tas.get_Tas());
          
          
          
      }
      else{
      
      
      
    ident_affecation = (String) node.jjtGetChild(0).jjtAccept(this, data);
    System.out.println("IDENT AFFECTATION "+ident_affecation);
    
  //  System.out.println("VALEUR AFFECTATION "+node.jjtGetChild(1).jjtAccept(this, data));
    eval_affectation = ""+node.jjtGetChild(1).jjtAccept(this, data); //(String) node.jjtGetChild(1).jjtAccept(this, data);
   

    System.out.println("EVALUATION DE LAFFECTATION :      -----   "+ eval_affectation);

   
    if(node.jjtGetChild(1).toString() == "ident") {
       pile.searchident(ident_affecation).getQuad().setValue(new EntierValue(""+eval(eval_affectation)));
    }
    
    
    if(node.jjtGetChild(1).toString() == "nbre") {
       pile.searchident(ident_affecation).getQuad().setValue(new EntierValue(eval_affectation));
    }
    
    
    if(node.jjtGetChild(1).toString() == "boolean") {
       pile.searchident(ident_affecation).getQuad().setValue(new BooleanValue(eval_affectation));
    }

    if(node.jjtGetChild(1).toString() == "plus" || node.jjtGetChild(1).toString() == "moins" || node.jjtGetChild(1).toString() == "mult" || node.jjtGetChild(1).toString() == "div" || node.jjtGetChild(1).toString() == "neg" ) {
       System.out.println("BORDELDEMERDE " + eval_affectation );
       ElementMemoire e = pile.searchident(ident_affecation);
       e.getQuad().setValue(new EntierValue(eval_affectation));
    }
   
    if(node.jjtGetChild(1).toString() == "Meth") {
       pile.searchident(ident_affecation).getQuad().setValue(new MethValue(eval_affectation));
    }
    
    
    
    
    if (pile.searchident(ident_affecation).getQuad().getType().toString() == "Entier" && node.jjtGetChild(1).toString() == "appelE" ){
        if(eval_affectation == "true" || eval_affectation == "false"){
       
            
            // NEW EXCEPTION
            
            
        }else{
           pile.searchident(ident_affecation).getQuad().setValue(new EntierValue(eval_affectation)); 
        }
    }
    
    if (pile.searchident(ident_affecation).getQuad().getType().toString() == "Boolean" && node.jjtGetChild(1).toString() == "appelE" ){
        
        if(eval_affectation == "true" || eval_affectation == "false"){      
            pile.searchident(ident_affecation).getQuad().setValue(new BooleanValue(eval_affectation));                      
        }else{
         
            
            // NEW EXCEPTION
            
            
        }
    }
    
      }
    
    
    
    
    
    
    
    
    
     System.out.println(pile.AfficherPile());
     
    return "";
    
  }

  public Object visit(ASTsomme node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTsomme");


      String ident_affecation;
      String eval_affectation;

    ident_affecation = (String) node.jjtGetChild(0).jjtAccept(this, data);
    System.out.println("IDENT AFFECTATION "+ident_affecation);
    
  //  System.out.println("VALEUR AFFECTATION "+node.jjtGetChild(1).jjtAccept(this, data));
    eval_affectation = ""+node.jjtGetChild(1).jjtAccept(this, data); //(String) node.jjtGetChild(1).jjtAccept(this, data);
   

    System.out.println("EVALUATION DE LAFFECTATION :      -----   "+ eval_affectation);
    
    int ancienne_affectation=Integer.parseInt(""+pile.searchident(ident_affecation).getQuad().getValeurMethode());
    

   
    if(node.jjtGetChild(1).toString() == "ident") {
       int valeur_evaluee =  eval(eval_affectation);
       int resultat = valeur_evaluee + ancienne_affectation;
       pile.searchident(ident_affecation).getQuad().setValue(new EntierValue(""+resultat));
       
    }
    
    
    if(node.jjtGetChild(1).toString() == "nbre") {
        int resultat = Integer.parseInt(eval_affectation) + ancienne_affectation;
       pile.searchident(ident_affecation).getQuad().setValue(new EntierValue(eval_affectation));
    }
    
    
    if(node.jjtGetChild(1).toString() == "boolean") {
       pile.searchident(ident_affecation).getQuad().setValue(new BooleanValue(eval_affectation));
    }

    if(node.jjtGetChild(1).toString() == "plus" || node.jjtGetChild(1).toString() == "moins" || node.jjtGetChild(1).toString() == "mult" || node.jjtGetChild(1).toString() == "div" ) {
      int resultat = Integer.parseInt(eval_affectation) + ancienne_affectation;
        
        
        System.out.println("BORDELDEMERDE " + eval_affectation );
       ElementMemoire e = pile.searchident(ident_affecation);
       e.getQuad().setValue(new EntierValue(""+resultat));
    }
   
    
    if (pile.searchident(ident_affecation).getQuad().getType().toString() == "Entier" && node.jjtGetChild(1).toString() == "appelE" ){
        if(eval_affectation.substring(35) == "true" || eval_affectation.substring(35) == "false"){
       
            
            // NEW EXCEPTION
            
            
        }else{
                int resultat = Integer.parseInt(eval_affectation.substring(35)) + ancienne_affectation;  
           pile.searchident(ident_affecation).getQuad().setValue(new EntierValue(""+resultat)); 
        }
    }
    
    if (pile.searchident(ident_affecation).getQuad().getType().toString() == "Boolean" && node.jjtGetChild(1).toString() == "appelE" ){
        
        if(eval_affectation.substring(35) == "true" || eval_affectation.substring(35) == "false"){   
            pile.searchident(ident_affecation).getQuad().setValue(new BooleanValue(eval_affectation.substring(35)));                      
        }else{
         
            
            // NEW EXCEPTION
            
            
        }
    }
    
     System.out.println(pile.AfficherPile());
     
    return "";
      
      
      
      
      
  }

  public Object visit(ASTincrement node, Object data) throws MiniJajaVisitorException{

      System.out.println("On passe par ASTinc");
      
      String ident_affecation = (String) node.jjtGetChild(0).jjtAccept(this, data);
      int nouvelleval = pile.searchident(ident_affecation).getQuad().getValeur()+1;
      String eval_affectation = ""+nouvelleval;
      pile.searchident(ident_affecation).getQuad().setValue(new EntierValue(eval_affectation));
      System.out.println(pile.AfficherPile());
      
      return "";
  }

  public Object visit(ASTtab node, Object data) throws MiniJajaVisitorException{
      
      String ident_tableau = ""+node.jjtGetChild(0).jjtAccept(this, data);
      int index_tableau = Integer.parseInt(""+node.jjtGetChild(1).jjtAccept(this, data));
    
      int value_tab = pile.searchident(ident_tableau).getQuad().getValeur();
      String s = ""+tas.Tas_recupValeur(value_tab, index_tableau);
      return s;
  }

  public Object visit(ASTlistexp node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTexp");
      int i=0;
      while (i <node.jjtGetNumChildren()) {
          node.jjtGetChild(i).jjtAccept(this, data);
          i++;
      }
      return "";
  }

  public Object visit(ASTexnil node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTexil");
      return "";
  }


  public Object visit(ASTnon node, Object data) throws MiniJajaVisitorException{

      String s = ""+node.jjtGetChild(0).jjtAccept(this, data);
      if (node.jjtGetChild(0).toString() == "ident"){
          s= ""+pile.searchident(s).getQuad().getValeurMethode();
      }

      
      
      if (s.equals("false")){
        return true;
      }
      else{
          return false;
      }

       
  }

  
  public Object visit(ASTneg node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTng");
      String eval_fils = ""+node.jjtGetChild(0).jjtAccept(this, data);
      
      if (eval_fils.equalsIgnoreCase("0")){
          return eval_fils;
      }
      else{
        return "-"+eval_fils;
      }
  }
  
  public Object visit(ASTet node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTet");
             
       
      if ((node.jjtGetChild(0).jjtAccept(this, data).equals(true) || node.jjtGetChild(0).jjtAccept(this, data).equals("true")  ) && (node.jjtGetChild(1).jjtAccept(this, data).equals(true) || node.jjtGetChild(1).jjtAccept(this, data).equals("true")  )){
          return true;
      }
      else{
          return false;
      }
       
       
  }

 
  public Object visit(ASTou node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTou");
      
      if (node.jjtGetChild(0).jjtAccept(this, data).equals(true) || node.jjtGetChild(1).jjtAccept(this, data).equals(true)){
          return true;
      }
      else{
          return false;
      }
  }

  public Object visit(ASTegal node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTegal");

      
      
      String operateur1 = ""+node.jjtGetChild(0).jjtAccept(this, data);
      String operateur2 = ""+node.jjtGetChild(1).jjtAccept(this, data);
      
      if (pile.estpresent(operateur1)){
          operateur1 = ""+pile.searchident(operateur1).getQuad().getValeurMethode();
      }
      
      if (pile.estpresent(operateur2)){
          operateur1 = ""+pile.searchident(operateur2).getQuad().getValeurMethode();
      }
      
      System.out.println( operateur1 + " CACACACACACACACA "+operateur2);
      
      if (operateur1.equalsIgnoreCase(operateur2)){
          return true;
      }
      else{
          return false;
      }
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
	  return "true";
  }

  public Object visit(ASTfaux node, Object data) throws MiniJajaVisitorException {
      System.out.println("On passe par ASTfaux");
	  return "false";
  }

  public Object visit(ASTappelE node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTappelE");

           String ident_fonction = (String) node.jjtGetChild(0).jjtAccept(this, data);
      ElementMemoire e = pile.searchident(ident_fonction);
      ASTmethode n  = (ASTmethode) e.getQuad().getValeurMethode();
      

      
      int taille_avant_decl = pile.getPile().size();
      n.jjtGetChild(2).jjtAccept(this, data);
      int taille_apres_decl = pile.getPile().size();
      
      int nombre_de_nouvelles_valeurs = taille_apres_decl - taille_avant_decl;
      
      ASTlistexp o = (ASTlistexp) node.jjtGetChild(1);
      
      
      System.out.println("--------------------------");
     System.out.println(pile.AfficherPile());
      
      
      for (int i=taille_avant_decl;i<taille_apres_decl;i++){     
                  
          
          if ( o.jjtGetChild(0).toString() == "ident"){
              ElementMemoire em = pile.searchident(""+o.jjtGetChild(0).jjtAccept(this, data));
              String em_type = em.getQuad().getType(); 
              String value_param = (String) em.getQuad().getValeurMethode();
              if (em_type == "Entier"){
                  pile.getPile().get(i).getQuad().setValue(new EntierValue(value_param));
              }
              if (em_type == "Boolean"){
                  pile.getPile().get(i).getQuad().setValue(new BooleanValue(value_param));                    
              }
          }
          else{           
              if (pile.getPile().get(i).getQuad().getType() == "Entier"){
                  pile.getPile().get(i).getQuad().setValue(new EntierValue(""+o.jjtGetChild(0).jjtAccept(this, data)));
              }
              if (pile.getPile().get(i).getQuad().getType() == "Boolean"){
                  pile.getPile().get(i).getQuad().setValue(new BooleanValue(""+o.jjtGetChild(0).jjtAccept(this, data)));                    
              }
                       
          }

        if (o.jjtGetChild(1).toString() != "exnil"){
           o=  (ASTlistexp) o.jjtGetChild(1); 
        }        
      }
      
      
      System.out.println("avant " + taille_avant_decl + " apres " + taille_apres_decl);
      
      
        //(t, ident(i), ent, dvs, iss)       
      
     n.jjtGetChild(3).jjtAccept(this, data);
     
     
      System.out.println("--------------------------");
     System.out.println(pile.AfficherPile());
              
     // Node n = e.getQuad().getValeur();
         System.out.println("------------DERNIER PASSAGE--------------");
     String retour = (""+n.jjtGetChild(4).jjtAccept(this, data));
      System.out.println("&&&&&&&&&&&&&&&&&&&&&&&& " + retour);
     
      
      // ENLEVER LE COMMENTAIRE SUIVANT   
      
      
    // pile.supprimer(ident_fonction,tas);
     System.out.println(pile.AfficherPile());
      return retour.substring(35); 
      
      
      
  }

  public Object visit(ASTnbre node, Object data) throws MiniJajaVisitorException {
      System.out.println("On passe par ASTnbre");

      System.out.println(node.getValeur());
      return String.valueOf(node.getValeur());
  }

  public Object visit(ASTrien node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTrien");
      return "";
  }

  public Object visit(ASTentier node, Object data) throws MiniJajaVisitorException{
      System.out.println("On passe par ASTEntier");
      return "";
  }

  public Object visit(ASTbooleen node, Object data) throws MiniJajaVisitorException {
    System.out.println("On passe par ASTBool");
    return "";
  }





  
  public int eval( String s){    
      System.out.println(pile.AfficherPile());
      
      return pile.searchident(s).getQuad().getValeur();    
  }


}