/* Generated By:JavaCC: Do not edit this line. JajaCodeVisitor.java Version 5.0 */  

  public class InterpreteurVisitorJajacode implements JajaCodeVisitor {
	JajaCode Asa_Jajacode;
	Pile pile;
        Tas_Tas tas;

    // Permet de gerer le pas a pas.
  //  Boolean thread_sleep = false;

  public InterpreteurVisitorJajacode(Pile pile, Tas_Tas tas, JajaCode Asa_Jajacode) {

		this. Asa_Jajacode = Asa_Jajacode;
		this.tas = tas;
		this.pile = pile;
        System.out.println("on instancie l'InterperteurJajaCode");
  }  
    
    
  public Object visit(SimpleNode node, Object data) throws JajaCodeVisitorException{    
            System.out.println("On passe par SimpleNode");
            
            for (int i=0;i<node.jjtGetNumChildren();i++){
                node.jjtGetChild(i).jjtAccept(this, data);
            }
            
            
      return true;
  };
  public Object visit(ASTJajaCode node, Object data) throws JajaCodeVisitorException{
            System.out.println("On passe par ASTJajaCode");
                         for (int i=0;i<node.jjtGetNumChildren();i++){
                node.jjtGetChild(i).jjtAccept(this, data);
            }
      return true;
  };
  
  
 
  public Object visit(ASTJCNil node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTJCNil");
      return true;
  };
  
  
  public Object visit(ASTInit node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTInit");
                   for (int i=0;i<node.jjtGetNumChildren();i++){
                node.jjtGetChild(i).jjtAccept(this, data);
                   }                   
      return true;
  };
  
  
  public Object visit(ASTSwap node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTSwap");
      
      pile.swap();
      
                   System.out.println("----------nouvelle-----------");
             System.out.println(pile.AfficherPile());       
      
      
      return true;
  };
  
  
  public Object visit(ASTNew node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTNew");

                   
             String ident_new = ""+node.jjtGetChild(0).jjtAccept(this, data);
             String type_new = ""+node.jjtGetChild(1).jjtAccept(this, data);
             String sorte_new = ""+node.jjtGetChild(2).jjtAccept(this, data);
             String adresse_new = ""+node.jjtGetChild(3).jjtAccept(this, data);
             
          //   String valeur_new =""+pile.getPile().get(pile.getPile().size()-Integer.parseInt(adresse_new)-1).getQuad().getValeurMethode();
       //      pile.getPile().remove(pile.getPile().size()-Integer.parseInt(adresse_new)-1);
             
             
             System.out.println(type_new + sorte_new);
             
             if (type_new.equalsIgnoreCase("entier")){
                 if (sorte_new.equalsIgnoreCase("var")){
                     pile.getPile().get(pile.getPile().size()-Integer.parseInt(adresse_new)-1).getQuad().setIdent(ident_new);
                     pile.getPile().get(pile.getPile().size()-Integer.parseInt(adresse_new)-1).getQuad().setGenre(new GenreVar());                 
                 }
                 if (sorte_new.equalsIgnoreCase("cst")){
                     pile.getPile().get(pile.getPile().size()-Integer.parseInt(adresse_new)-1).getQuad().setIdent(ident_new);
                 }
                 if (sorte_new.equalsIgnoreCase("meth")){
                     pile.getPile().get(pile.getPile().size()-Integer.parseInt(adresse_new)-1).getQuad().setIdent(ident_new);
                     pile.getPile().get(pile.getPile().size()-Integer.parseInt(adresse_new)-1).getQuad().setGenre(new GenreMeth());   
                 }
             }
             
             if (type_new.equalsIgnoreCase("booleen")){
                 if (sorte_new.equalsIgnoreCase("var")){
                     pile.getPile().get(pile.getPile().size()-Integer.parseInt(adresse_new)-1).getQuad().setIdent(ident_new);
                     pile.getPile().get(pile.getPile().size()-Integer.parseInt(adresse_new)-1).getQuad().setGenre(new GenreVar());   
                 }
                 if (sorte_new.equalsIgnoreCase("cst")){
                     pile.getPile().get(pile.getPile().size()-Integer.parseInt(adresse_new)-1).getQuad().setIdent(ident_new);
                 }
                 if (sorte_new.equalsIgnoreCase("meth")){
                     pile.getPile().get(pile.getPile().size()-Integer.parseInt(adresse_new)-1).getQuad().setIdent(ident_new);
                     pile.getPile().get(pile.getPile().size()-Integer.parseInt(adresse_new)-1).getQuad().setGenre(new GenreMeth());   
                 }
             }
             
             if (type_new.equalsIgnoreCase("void")){
                 if (sorte_new.equalsIgnoreCase("meth")){                   
                     pile.getPile().get(pile.getPile().size()-Integer.parseInt(adresse_new)-1).getQuad().setIdent(ident_new);
                     pile.getPile().get(pile.getPile().size()-Integer.parseInt(adresse_new)-1).getQuad().setType(new TypeVoid());
                     pile.getPile().get(pile.getPile().size()-Integer.parseInt(adresse_new)-1).getQuad().setGenre(new GenreMeth());   
                 }
             }
             
             
           //  pile.getPile().push(null)
             
       //      System.out.println(ident_new + " : " + type_new + " : " + sorte_new + " : " + valeur_new);
             System.out.println("----------nouvelle-----------");
             System.out.println(pile.AfficherPile());             
             
             
             
      return true;
  };
  

  public Object visit(ASTNewArray node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTNewArray");

      
      
             String ident_new = ""+node.jjtGetChild(0).jjtAccept(this, data);
             String type_new = ""+node.jjtGetChild(1).jjtAccept(this, data);
             
          //   String valeur_new =""+pile.getPile().get(pile.getPile().size()-Integer.parseInt(adresse_new)-1).getQuad().getValeurMethode();
       //      pile.getPile().remove(pile.getPile().size()-Integer.parseInt(adresse_new)-1);
             
             
             System.out.println(type_new + ident_new);
             ElementMemoire em = pile.getPile().pop();
             
             if (type_new.equalsIgnoreCase("entier")){               
                  pile.getPile().push(new TabPile(ident_new,new TabValue(em.getQuad().getValeur()),new GenreTab(),new TypeEntier(),""));
             }
             
             if (type_new.equalsIgnoreCase("booleen")){
                  pile.getPile().push(new TabPile(ident_new,new TabValue(em.getQuad().getValeur()),new GenreTab(),new TypeBoolean(),""));  
             }
             
             tas.Tas_allouerTableau(ident_new, type_new,em.getQuad().getValeur());
      
             
           //  pile.getPile().push(null)
             
       //      System.out.println(ident_new + " : " + type_new + " : " + sorte_new + " : " + valeur_new);
             System.out.println("----------nouvelle-----------");
             System.out.println(pile.AfficherPile());             
             System.out.println(tas.toString());
             
             
      return true;
      
      
      
      
  };
  
  public Object visit(ASTInvoke node, Object data) throws JajaCodeVisitorException{

      
      System.out.println("On passe par ASTInvoke");
      
      String methode_a_appeler = ""+node.jjtGetChild(0).jjtAccept(this, data);
      int num_methode_a_appeler = pile.searchident(methode_a_appeler).getQuad().getValeur();
      String prochain_num = ""+node.jjtGetParent().jjtGetChild(2).jjtGetChild(0).jjtAccept(this, data);                               
         
      pile.getPile().push(new EntierPile("w",new EntierValue(prochain_num),new GenreCst(),new TypeEntier(),""));
      
      Node sn = node.jjtGetParent();
      
                   System.out.println("----------nouvelle-----------");
                   
             System.out.println(pile.AfficherPile());     
      
             
              System.out.println("NUMERO DU NOEUD"+ sn.jjtGetChild(0).jjtAccept(this, data));
             
             
      while (Integer.parseInt(""+sn.jjtGetChild(0).jjtAccept(this, data)) != num_methode_a_appeler){
          
          sn=sn.jjtGetParent();
          System.out.println("NUMERO DU NOEUD"+ sn.jjtGetChild(0).jjtAccept(this, data));
      }
       
      System.out.println("ON A TROUVER LA FONCTION" + num_methode_a_appeler);
      sn.jjtAccept(this, data);
      
      return true;
      
      
  };
  
  
  public Object visit(ASTReturn node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTReturn");
      
      ElementMemoire em = pile.getPile().pop();
      int aller_a = em.getQuad().getValeur();
      
      Node sn = node.jjtGetParent();
      
      while (Integer.parseInt(""+sn.jjtGetChild(0).jjtAccept(this, data)) != aller_a ){
          sn=sn.jjtGetChild(2);
      }
      System.out.println("ON EST ARRIVE RETURN");
      
                         System.out.println("----------nouvelle-----------");
             System.out.println(pile.AfficherPile());     
      
      sn.jjtAccept(this, data);
           
      return true;
  };
  
  
  public Object visit(ASTPush node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTPush");
                   for (int i=0;i<node.jjtGetNumChildren();i++){
                node.jjtGetChild(i).jjtAccept(this, data);
            }
                   
       if ( node.jjtGetChild(0).toString().equalsIgnoreCase("JCVrai")){
           pile.getPile().push(new BooleenPile("w",new BooleanValue("true"),new GenreCst(),new TypeBoolean(),""));
       }
       
       if (node.jjtGetChild(0).toString().equalsIgnoreCase("JCFaux")){
            pile.getPile().push(new BooleenPile("w",new BooleanValue("false"),new GenreCst(),new TypeBoolean(),""));
       }
       
        if (node.jjtGetChild(0).toString().equalsIgnoreCase("JCNbre")){
            pile.getPile().push(new EntierPile("w",new EntierValue(""+node.jjtGetChild(0).jjtAccept(this, data)),new GenreCst(),new TypeEntier(),""));
       }
       
       System.out.println("caca"+node.jjtGetChild(0).toString());
       
   /*    if (node.jjtGetChild(0).toString().equalsIgnoreCase("JCFaux")){
            pile.getPile().push(new BooleenPile("w",new BooleanValue("false"),new GenreCst(),new TypeBoolean(),""));
       } 
    */   
    //  pile.getPile().push()
       
       System.out.println(pile.AfficherPile());
      return true;
  };
  
  
  public Object visit(ASTPop node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTPop");
      
      pile.getPile().pop();
      
      
                   System.out.println("----------nouvelle-----------");
             System.out.println(pile.AfficherPile());       
      
      return true;
  };
  
  
  public Object visit(ASTLoad node, Object data) throws JajaCodeVisitorException{
    
                   
      String a_load = ""+pile.searchident(""+node.jjtGetChild(0).jjtAccept(this, data)).getQuad().getValeurMethode();             
      if (pile.searchident(""+node.jjtGetChild(0).jjtAccept(this, data)).getQuad().getType().equalsIgnoreCase("Entier")){
          pile.getPile().push(new EntierPile("w",new EntierValue(a_load),new GenreCst(),new TypeEntier(),""));
      }
      if (pile.searchident(""+node.jjtGetChild(0).jjtAccept(this, data)).getQuad().getType().equalsIgnoreCase("Boolean")){
          pile.getPile().push(new BooleenPile("w",new BooleanValue(a_load),new GenreCst(),new TypeBoolean(),""));          
      }               
                   
                   
      System.out.println("----------LOAD-----------");
      System.out.println(pile.AfficherPile());               
                   
      return true;
  };
  
  
  public Object visit(ASTALoad node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTALoad");
        
      
      String a_load = ""+node.jjtGetChild(0).jjtAccept(this, data);
      int index = pile.getPile().pop().getQuad().getValeur();
      if (pile.searchident(a_load).getQuad().getType().equalsIgnoreCase("Entier")){          
          pile.getPile().push(new EntierPile("w",new EntierValue(""+tas.Tas_recupValeur(a_load,index)),new GenreCst(),new TypeEntier(),""));
      }
      if (pile.searchident(a_load).getQuad().getType().equalsIgnoreCase("Boolean")){
          pile.getPile().push(new BooleenPile("w",new BooleanValue(""+tas.Tas_recupValeur(a_load,index)),new GenreCst(),new TypeBoolean(),""));          
      }               
                   
                   
      System.out.println("----------LOAD-----------");
      System.out.println(pile.AfficherPile());    
         System.out.println(tas.toString());
      
      
      return true;
  };
  
  
  
  public Object visit(ASTStore node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTStore");
                 
                   
      String valeur_store =""+pile.getPile().lastElement().getQuad().getValeurMethode();
      pile.getPile().remove(pile.getPile().size()-1);
                   
      
      System.out.println("VALEURSTORE : " + valeur_store);
      
      String ident_store = ""+node.jjtGetChild(0).jjtAccept(this, data);
      
      if (pile.searchident(ident_store).getQuad().getType().equalsIgnoreCase("Boolean")){
         pile.searchident(ident_store).getQuad().setValue(new BooleanValue(valeur_store));    
      }  
      
      if (pile.searchident(ident_store).getQuad().getType().equalsIgnoreCase("Entier")){
         pile.searchident(ident_store).getQuad().setValue(new EntierValue(valeur_store));    
      }   
          
             System.out.println("----------nouvelle-----------");
             System.out.println(pile.AfficherPile());    
                   
      return true;
  };
  
  
  public Object visit(ASTAStore node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTAStore");
      
      
      
      
      
      String valeur_tab = ""+pile.getPile().pop().getQuad().getValeurMethode();
      int indice_tab = pile.getPile().pop().getQuad().getValeur();            
      
      System.out.println("VALEURtab : " + valeur_tab + " indicetab : " + indice_tab);
      
      String ident_store = ""+node.jjtGetChild(0).jjtAccept(this, data);
      
      tas.Tas_affecterValeur(ident_store, indice_tab, valeur_tab);    
      
         
             System.out.println("----------nouvelle-----------");
             System.out.println(pile.AfficherPile());     
                System.out.println(tas.toString());
   
      
      return true;
  };
  
 
  public Object visit(ASTIf node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTIf");
      
      String valeur_if = ""+pile.getPile().pop().getQuad().getValeurMethode();
      if (valeur_if.equals("true")){
          

      int aller_a  = Integer.parseInt(""+node.jjtGetChild(0).jjtAccept(this, data));
      Node sn = node.jjtGetParent();
      
      while (Integer.parseInt(""+sn.jjtGetChild(0).jjtAccept(this, data)) != aller_a ){
          sn=sn.jjtGetChild(2);
      }
      System.out.println("ON EST ARRIVE");
      sn.jjtAccept(this, data);
     
      }
      
      
      return true;
  };
  
  
  public Object visit(ASTGoto node, Object data) throws JajaCodeVisitorException{
        System.out.println("On passe par ASTGoto");
      int aller_a  = Integer.parseInt(""+node.jjtGetChild(0).jjtAccept(this, data));
      Node sn = node.jjtGetParent();
      
      while (Integer.parseInt(""+sn.jjtGetChild(0).jjtAccept(this, data)) != aller_a ){
          sn=sn.jjtGetChild(2);
      }
      System.out.println("ON EST ARRIVE");
      sn.jjtAccept(this, data);
      
      return true;
  };
  
  
  public Object visit(ASTInc node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTInc");

      int ancienne_valeur = pile.searchident(""+node.jjtGetChild(0).jjtAccept(this, data)).getQuad().getValeur();
      ElementMemoire em = pile.getPile().pop();
      int nouvelle_valeur = ancienne_valeur + em.getQuad().getValeur();
      pile.searchident(""+node.jjtGetChild(0).jjtAccept(this, data)).getQuad().setValue(new EntierValue(""+nouvelle_valeur));
      
      
     System.out.println("----------nouvelle-----------");
     System.out.println(pile.AfficherPile());  
      
      return true;
  };

  
  public Object visit(ASTAInc node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTAInc");

      int  inc1 = pile.getPile().pop().getQuad().getValeur();
      int indice = pile.getPile().pop().getQuad().getValeur();
      
      int ancienne_valeur = Integer.parseInt(""+tas.Tas_recupValeur((""+node.jjtGetChild(0).jjtAccept(this, data)),indice));
      int nouvelle_valeur = ancienne_valeur + inc1;
      
     tas.Tas_affecterValeur(""+node.jjtGetChild(0).jjtAccept(this, data), indice, ""+nouvelle_valeur);   
     System.out.println("----------nouvelle-----------");
     System.out.println(pile.AfficherPile());  
     System.out.println(tas.toString());
     
     
      return true;
  };
  
  
  public Object visit(ASTNop node, Object data) throws JajaCodeVisitorException{
      System.out.println("VA TE FAIRE ENCULE");

      return true;
  };
  
  
  public Object visit(ASTJCStop node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTJCStop");
      
                         System.out.println("----------nouvelle-----------");
             System.out.println(pile.AfficherPile());   
             
      System.exit(0);
      return true;
  };
  
  public Object visit(ASTJCIdent node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTJCIdent");            
       System.out.println(node.getValeur());            
      return node.getValeur();
  };
  
  public Object visit(ASTJCType node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTJCType");
      return node.getValeur();
  };
  
  public Object visit(ASTJCSorte node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTJCSorte");
      return node.getValeur();
  };
  
  public Object visit(ASTJCVrai node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTJCVrai");
      return true;
  };
  
  public Object visit(ASTJCFaux node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTJCFaux");
      return false;
  };
  
  public Object visit(ASTNeg node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTNeg");
      
      String valeur = ""+pile.getPile().peek().getQuad().getValeur();
     
      if (valeur.substring(0,1).equalsIgnoreCase("-")){
          pile.getPile().peek().getQuad().setValue(new EntierValue (valeur.substring(1))); 
      }else{
          pile.getPile().peek().getQuad().setValue(new EntierValue ("-"+valeur));   
      }
      
   
       System.out.println("----------nouvelle-----------");
             System.out.println(pile.AfficherPile());   
      
      return true;
  };
  
  public Object visit(ASTNot node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTNot");

      if (pile.getPile().pop().getQuad().getValeurMethode().equals("true")){
          pile.getPile().push(new BooleenPile("w",new BooleanValue("true"),new GenreCst(),new TypeBoolean(),""));   
      }
      else{
          pile.getPile().push(new BooleenPile("w",new BooleanValue("false"),new GenreCst(),new TypeBoolean(),""));   
      }
      
                         System.out.println("----------nouvelle-----------");
             System.out.println(pile.AfficherPile());   
      
      return true;
  };
  
  public Object visit(ASTAdd node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTAdd");

      ElementMemoire em = pile.getPile().pop();
      ElementMemoire em1 = pile.getPile().pop();
      int total = em1.getQuad().getValeur()+em.getQuad().getValeur();
      
      pile.getPile().push(new EntierPile("w",new EntierValue(""+total),new GenreCst(),new TypeEntier(),""));
      
                   System.out.println("----------nouvelle-----------");
             System.out.println(pile.AfficherPile());   
      
      return true;
  };
  
  public Object visit(ASTSub node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTSub");

      ElementMemoire em = pile.getPile().pop();
      ElementMemoire em1 = pile.getPile().pop();
      int total = em1.getQuad().getValeur()-em.getQuad().getValeur();
      
      pile.getPile().push(new EntierPile("w",new EntierValue(""+total),new GenreCst(),new TypeEntier(),""));
      
      System.out.println("----------nouvelle-----------");
      System.out.println(pile.AfficherPile());   
      
      
      return true;
  };

  
  public Object visit(ASTMul node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTMul");

      
      ElementMemoire em = pile.getPile().pop();
      ElementMemoire em1 = pile.getPile().pop();
      int total = em1.getQuad().getValeur()*em.getQuad().getValeur();
      
      pile.getPile().push(new EntierPile("w",new EntierValue(""+total),new GenreCst(),new TypeEntier(),""));
      
                   System.out.println("----------nouvelle-----------");
             System.out.println(pile.AfficherPile());   
      
      
      return true;
  };
  

  public Object visit(ASTDiv node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTDiv");

      
      ElementMemoire em = pile.getPile().pop();
      ElementMemoire em1 = pile.getPile().pop();
      int total = em1.getQuad().getValeur()/em.getQuad().getValeur();

      
      pile.getPile().push(new EntierPile("w",new EntierValue(""+total),new GenreCst(),new TypeEntier(),""));
      
                   System.out.println("----------nouvelle-----------");
             System.out.println(pile.AfficherPile());   
      
      return true;
  };
  
 
  public Object visit(ASTCmp node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTCmp");
      
      ElementMemoire e1 = pile.getPile().pop();
      ElementMemoire e2 = pile.getPile().pop();
      
      if (e1.getQuad().getValeurMethode().equals(e2.getQuad().getValeurMethode())){
           pile.getPile().push(new BooleenPile("w",new BooleanValue("true"),new GenreCst(),new TypeBoolean(),""));
      }else{
          pile.getPile().push(new BooleenPile("w",new BooleanValue("false"),new GenreCst(),new TypeBoolean(),""));
      }
      
            System.out.println("----------nouvelle-----------");
      System.out.println(pile.AfficherPile());   
      
      return true;
  };
  

  public Object visit(ASTSup node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTSup");

      
      ElementMemoire e1 = pile.getPile().pop();
      ElementMemoire e2 = pile.getPile().pop();
      
      if (e1.getQuad().getValeur() < e2.getQuad().getValeur()){
           pile.getPile().push(new BooleenPile("w",new BooleanValue("true"),new GenreCst(),new TypeBoolean(),""));
      }else{
          pile.getPile().push(new BooleenPile("w",new BooleanValue("false"),new GenreCst(),new TypeBoolean(),""));
      }
      
            System.out.println("----------nouvelle-----------");
      System.out.println(pile.AfficherPile());   
      
      
      
      return true;
  };
  
  public Object visit(ASTInf node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTInf");

      
            
      ElementMemoire e1 = pile.getPile().pop();
      ElementMemoire e2 = pile.getPile().pop();
      
      if (e1.getQuad().getValeur() > e2.getQuad().getValeur()){
           pile.getPile().push(new BooleenPile("w",new BooleanValue("true"),new GenreCst(),new TypeBoolean(),""));
      }else{
          pile.getPile().push(new BooleenPile("w",new BooleanValue("false"),new GenreCst(),new TypeBoolean(),""));
      }
      
            System.out.println("----------nouvelle-----------");
      System.out.println(pile.AfficherPile());   
      
      
      return true;
  };
  
  
  public Object visit(ASTOr node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTOr");

      
      ElementMemoire e1 = pile.getPile().pop();
      ElementMemoire e2 = pile.getPile().pop();
      
      if (e1.getQuad().getValeurMethode().equals("true") || e2.getQuad().getValeurMethode().equals("true")){
           pile.getPile().push(new BooleenPile("w",new BooleanValue("true"),new GenreCst(),new TypeBoolean(),""));
      }else{
          pile.getPile().push(new BooleenPile("w",new BooleanValue("false"),new GenreCst(),new TypeBoolean(),""));
      }
      
            System.out.println("----------nouvelle-----------");
      System.out.println(pile.AfficherPile());   
      
      
      
      return true;
  };
  

  public Object visit(ASTAnd node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTAnd");

      
      ElementMemoire e1 = pile.getPile().pop();
      ElementMemoire e2 = pile.getPile().pop();
      
      if (e1.getQuad().getValeurMethode().equals("true") && e2.getQuad().getValeurMethode().equals("true")){
           pile.getPile().push(new BooleenPile("w",new BooleanValue("true"),new GenreCst(),new TypeBoolean(),""));
      }else{
          pile.getPile().push(new BooleenPile("w",new BooleanValue("false"),new GenreCst(),new TypeBoolean(),""));
      }
      
            System.out.println("----------nouvelle-----------");
      System.out.println(pile.AfficherPile());   
      
      
      return true;
  };
  
  
  public Object visit(ASTJCNbre node, Object data) throws JajaCodeVisitorException{
      System.out.println("On passe par ASTJCNbre");
       System.out.println(node.getValeur());            
      return node.getValeur();
  };
}
/* JavaCC - OriginalChecksum=8ab0f442622e479ee245257214939ad8 (do not edit this line) */
