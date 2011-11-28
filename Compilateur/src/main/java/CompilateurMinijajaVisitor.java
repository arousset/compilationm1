/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ROUSSET Alban & Clement KAWCZAK
 */
public class CompilateurMinijajaVisitor implements MiniJajaVisitor {
    // Sa visite Sec !
  public Object visit(SimpleNode node, Object data) throws MiniJajaVisitorException {
      return null;
  }

  public Object visit(ASTclasse node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";
      // init o+ G (pdss o+ pmma o+ prdss) o+ D pop o+ D jcstop

      //init
      retour += cmpadd.getAddress() + " init;\n";
      cmpadd.addAdress(1);
      //dss
      retour += node.jjtGetChild(1).jjtAccept(this,data);
      //mma
      retour += node.jjtGetChild(2).jjtAccept(this,data);
      //rdss
      cmpadd.setRetrait(true);
      retour += node.jjtGetChild(1).jjtAccept(this, data);
      cmpadd.setRetrait(false);
      //pop
      retour += cmpadd.getAddress() + " pop;\n";
      cmpadd.addAdress(1);
      //jcstop
      retour += cmpadd.getAddress() + " jcstop;\n";
      cmpadd.addAdress(1);

      return retour;
  }

  public Object visit(ASTident node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      //cident load(i)
      retour += cmpadd.getAddress();
      retour += " load(" + node.getValeur() + ");\n";
      cmpadd.addAdress(1);
      
      return retour;
  }

  public Object visit(ASTdecls node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      if(!cmpadd.getRetrait()) {
        //cdecls
          // pds o+ pdss

          //ds
          retour += node.jjtGetChild(0).jjtAccept(this, data);
          //dss
          retour += node.jjtGetChild(1).jjtAccept(this, data);
      } else {
        //crdecls
          // prdss o+ prds

          //rdss
          retour += node.jjtGetChild(1).jjtAccept(this, data);
          //rds
          retour += node.jjtGetChild(0).jjtAccept(this, data);
      }
      

      
      return retour;
  }

  public Object visit(ASTvnil node, Object data) throws MiniJajaVisitorException {
      return "";
  }

  public Object visit(ASTcst node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      if(!cmpadd.getRetrait()){
        // ccst
            // e
            retour += node.jjtGetChild(2).jjtAccept(this, data);
            // new
            retour += cmpadd.getAddress();
            retour += " new(";
            retour += ((ASTident)node.jjtGetChild(1)).getValeur() + ",";
            retour += node.jjtGetChild(0).jjtAccept(this, data) + ",";
            retour += "cst,0";
            retour += ");\n";
            cmpadd.addAdress(1);
       }else{
            // crcst
                // swap
                retour += cmpadd.getAddress() + " swap;\n";
		cmpadd.addAdress(1);
		// pop
		retour += cmpadd.getAddress() + " pop;\n";
		cmpadd.addAdress(1);
       }

      return retour;
  }

  public Object visit(ASTtableau node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      if(!cmpadd.getRetrait()){
        // e
	retour += node.jjtGetChild(2).jjtAccept(this, data);
	// new
	retour += cmpadd.getAddress();
	retour += " newarray(";
	retour += ((ASTident)node.jjtGetChild(1)).getValeur() + ",";
	retour += node.jjtGetChild(0).jjtAccept(this, data);
	retour += ");\n";
	cmpadd.addAdress(1);
       }else{
            // swap
            retour += cmpadd.getAddress() + " swap;\n";
            cmpadd.addAdress(1);
            // pop
            retour += cmpadd.getAddress() + " pop;\n";
            cmpadd.addAdress(1);
       }

      return retour;
  }

  public Object visit(ASTmethode node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      if(!cmpadd.getRetrait()){
	// cmethode

	// push
	retour += cmpadd.getAddress() + " push(" + (cmpadd.getAddress() + 3) + ");\n";
	cmpadd.addAdress(1);
	// new
	retour += cmpadd.getAddress();
	retour += " new(";
	retour += ((ASTident)node.jjtGetChild(1)).getValeur() + ",";
	retour += node.jjtGetChild(0).jjtAccept(this, data) + ",";
	retour += "meth,0);\n";
	cmpadd.addAdress(1);
	// goto
	int addrgoto = cmpadd.getAddress();
	cmpadd.addAdress(1);
	// ens
	cmpadd.setEntete(0);
	String ens = node.jjtGetChild(2).jjtAccept(this, data) + "";
	// dvs
	String dvs = node.jjtGetChild(3).jjtAccept(this, data) + "";
	// iss
	String iss = node.jjtGetChild(4).jjtAccept(this, data) + "";
	// rdvs
	cmpadd.setRetrait(true);
	String rdvs = node.jjtGetChild(3).jjtAccept(this, data) + "";
	cmpadd.setRetrait(false);
            // cmethodeR
            if(node.jjtGetChild(0).getClass().getSimpleName().equals("ASTrien")){
		  // goto
		  retour += addrgoto + " goto(" + (cmpadd.getAddress() + 3) + ");\n";
	    }else{
		  // goto
		  retour += addrgoto + " goto(" + (cmpadd.getAddress() + 2) + ");\n";
	    }
	// ens
	retour += ens;
	// dvs
	retour += dvs;
	// iss
	retour += iss;
	// rdvs
	retour += rdvs;
	// cmethodeR
	if(node.jjtGetChild(0).getClass().getSimpleName().equals("ASTrien")){
            // push(0)
            retour += cmpadd.getAddress() + " push(0);\n";
            cmpadd.addAdress(1);
	}
	// swap
	retour += cmpadd.getAddress() + " swap;\n";
	cmpadd.addAdress(1);
	// return
	retour += cmpadd.getAddress() + " return;\n";
	cmpadd.addAdress(1);
      }else{
	// crmethode

	// swap
	retour += cmpadd.getAddress() + " swap;\n";
	cmpadd.addAdress(1);
	// pop
	retour += cmpadd.getAddress() + " pop;\n";
        cmpadd.addAdress(1);
      }

      return retour;
  }

  public Object visit(ASTvar node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      if(!cmpadd.getRetrait()){
        //cvar

            // e
            retour += node.jjtGetChild(2).jjtAccept(this, data);
            // new
            retour += cmpadd.getAddress();
            retour += " new(";
            retour += ((ASTident)node.jjtGetChild(1)).getValeur() + ",";
            retour += node.jjtGetChild(0).jjtAccept(this, data) + ",";
            retour += "var,0";
            retour += ");\n";
            cmpadd.addAdress(1);
      }else{
	// crvar

            // swap
            retour += cmpadd.getAddress() + " swap;\n";
            cmpadd.addAdress(1);
            // pop
            retour += cmpadd.getAddress() + " pop;\n";
            cmpadd.addAdress(1);
      }

      return retour;
  }

  public Object visit(ASTvars node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      if(!cmpadd.getRetrait()){
        // cvars

            // dv
            retour += node.jjtGetChild(0).jjtAccept(this, data);
            // dvs
            retour += node.jjtGetChild(1).jjtAccept(this, data);
      }else{
	// crvars
            // rdvs
            retour += node.jjtGetChild(1).jjtAccept(this, data);
            // rdv
            retour += node.jjtGetChild(0).jjtAccept(this, data);
      }

      return retour;
  }

  public Object visit(ASTomega node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // push(w)
      retour += cmpadd.getAddress() + " push();\n";
      cmpadd.addAdress(1);
      return retour;
  }

  public Object visit(ASTmain node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      //pdvs o+ piss o+ D push(0)) o+ prdvs

      //dvs
      retour += node.jjtGetChild(0).jjtAccept(this, data);
      //iss
      retour += node.jjtGetChild(1).jjtAccept(this, data);
      //push
      retour += cmpadd.getAddress() + " push(0);\n";
      cmpadd.addAdress(1);
      //rdvs
      cmpadd.setRetrait(true);
      retour += node.jjtGetChild(0).jjtAccept(this, data) + "";
      cmpadd.setRetrait(false);

      return retour;
  }

  public Object visit(ASTentetes node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      cmpadd.addEntete(1);
      //en
      retour += node.jjtGetChild(0).jjtAccept(this, data);
      //ens
      retour += node.jjtGetChild(1).jjtAccept(this, data);

      return retour;
  }

  public Object visit(ASTenil node, Object data) throws MiniJajaVisitorException {
      String retour = "";
      return retour;
  }

  public Object visit(ASTentete node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      //new
      retour += cmpadd.getAddress();
      retour += " new(";
      retour += ((ASTident)node.jjtGetChild(1)).getValeur() + ",";
      retour += node.jjtGetChild(0).jjtAccept(this, data) + ",";
      retour += "var," + cmpadd.getEntete();
      retour += ");\n";
      cmpadd.addAdress(1);

      return retour;
  }

  public Object visit(ASTinstrs node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      //is
      retour += node.jjtGetChild(0).jjtAccept(this, data);
      //iss
      retour += node.jjtGetChild(1).jjtAccept(this, data);

      return retour;
  }

  public Object visit(ASTinil node, Object data) throws MiniJajaVisitorException {
      String retour = "";
      return retour;
  }

  public Object visit(ASTretour node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // e
      retour += node.jjtGetChild(0).jjtAccept(this, data);

      return retour;
  }

  public Object visit(ASTtantque node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // goto permet de stocker la valeur temp pour la mettre apres
      int addgoto = cmpadd.getAddress();
      // e
      retour += node.jjtGetChild(0).jjtAccept(this, data);
      // not
      retour += cmpadd.getAddress() + " not;\n";
      cmpadd.addAdress(1);
      // if
      int addrif = cmpadd.getAddress();
      cmpadd.addAdress(1);
      // iss
      String iss = node.jjtGetChild(1).jjtAccept(this, data) + "";
      // if
      retour += addrif + " if(" + (cmpadd.getAddress() + 1) + ");\n";
      // iss
      retour += iss;
      // goto bis ^^
      retour += cmpadd.getAddress() + " goto(" + addgoto + ");\n";
      cmpadd.addAdress(1);

      return retour;
  }

  public Object visit(ASTsi node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // e
      retour += node.jjtGetChild(0).jjtAccept(this, data);
      // if
      int addrif = cmpadd.getAddress();
      cmpadd.addAdress(1);
      // s1
      String s1 = node.jjtGetChild(2).jjtAccept(this, data) + "";
      // if
      retour += addrif + " if(" + (cmpadd.getAddress() + 1) + ");\n";
      // s1
      retour += s1;
      // goto
      int addrgoto = cmpadd.getAddress();
      cmpadd.addAdress(1);
      // s
      String s = node.jjtGetChild(1).jjtAccept(this, data) + "";
      // goto
      retour += addrgoto + " goto(" + (cmpadd.getAddress()) + ");\n";
      // s
      retour += s;

      return retour;
  }

  public Object visit(ASTappelI node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // lexp
      retour += node.jjtGetChild(1).jjtAccept(this, data);
      // invoke
      retour += cmpadd.getAddress();
      retour += " invoke(" + ((ASTident)node.jjtGetChild(0)).getValeur() + ");\n";
      cmpadd.addAdress(1);
      // rlexp
      cmpadd.setRetrait(true);
      retour += node.jjtGetChild(1).jjtAccept(this, data);
      cmpadd.setRetrait(false);
      // pop
      retour += cmpadd.getAddress();
      retour += " pop;\n";
      cmpadd.addAdress(1);

      return retour;
  }

  public Object visit(ASTaffectation node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // caffecte
      if(node.jjtGetChild(0).getClass().getSimpleName().equals("ASTident")){
    	  // e
    	  retour += node.jjtGetChild(1).jjtAccept(this, data);
    	  // inc
    	  retour += cmpadd.getAddress();
    	  retour += " store(" + ((ASTident)node.jjtGetChild(0)).getValeur() + ");\n";
    	  cmpadd.addAdress(1);
      }
      // caffecteT
      if(node.jjtGetChild(0).getClass().getSimpleName().equals("ASTtab")){
    	  // e1
    	  retour += node.jjtGetChild(0).jjtGetChild(1).jjtAccept(this, data);
    	  // e
    	  retour += node.jjtGetChild(1).jjtAccept(this, data);
    	  // ainc
    	  retour += cmpadd.getAddress();
    	  retour += " astore(" + ((ASTident)((ASTtab)node.jjtGetChild(0)).jjtGetChild(0)).getValeur() + ");\n";
    	  cmpadd.addAdress(1);
      }

      return retour;
  }

  public Object visit(ASTsomme node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // csomme
      if(node.jjtGetChild(0).getClass().getSimpleName().equals("ASTident")){
    	  // e
    	  retour += node.jjtGetChild(1).jjtAccept(this, data);
    	  // inc
    	  retour += cmpadd.getAddress();
    	  retour += " inc(" + ((ASTident)node.jjtGetChild(0)).getValeur() + ");\n";
    	  cmpadd.addAdress(1);
      }
      // csommeT
      if(node.jjtGetChild(0).getClass().getSimpleName().equals("ASTtab")){
    	  // e1
    	  retour += node.jjtGetChild(0).jjtGetChild(1).jjtAccept(this, data);
    	  // e
    	  retour += node.jjtGetChild(1).jjtAccept(this, data);
    	  // ainc
    	  retour += cmpadd.getAddress();
    	  retour += " ainc(" + ((ASTident)node.jjtGetChild(0)).getValeur() + ");\n";
    	  cmpadd.addAdress(1);
      }

      return retour;
  }

  public Object visit(ASTincrement node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // cinc
      if(node.jjtGetChild(0).getClass().getSimpleName().equals("ASTident")){
    	  // push(1)
    	  retour += cmpadd.getAddress() + " push(1);\n";
    	  cmpadd.addAdress(1);
    	  // inc
    	  retour += cmpadd.getAddress();
    	  retour += " inc(" + ((ASTident)node.jjtGetChild(0)).getValeur() + ");\n";
    	  cmpadd.addAdress(1);
      }
      // cincT
      if(node.jjtGetChild(0).getClass().getSimpleName().equals("ASTtab")){
    	  // e
    	  retour += node.jjtGetChild(0).jjtGetChild(1).jjtAccept(this, data);
    	  // push(1)
    	  retour += cmpadd.getAddress() + " push(1);\n";
    	  cmpadd.addAdress(1);
    	  // ainc
    	  retour += cmpadd.getAddress();
    	  retour += " ainc(" + ((ASTident)node.jjtGetChild(0)).getValeur() + ");\n";
    	  cmpadd.addAdress(1);
      }

      return retour;
  }

  public Object visit(ASTtab node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // e
      retour += node.jjtGetChild(1).jjtAccept(this, data);
      // aload
      retour += cmpadd.getAddress();
      retour += " aload(" + ((ASTident)node.jjtGetChild(0)).getValeur() + ");\n";
      cmpadd.addAdress(1);

      return retour;
  }

  public Object visit(ASTlistexp node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      if(!cmpadd.getRetrait()){
    	  // clexp
            // exp
            retour += node.jjtGetChild(0).jjtAccept(this, data);
            // lexp
            retour += node.jjtGetChild(1).jjtAccept(this, data);
      }else{
	  // crlexp
            // swap
            retour += cmpadd.getAddress() + " swap;\n";
            cmpadd.addAdress(1);
            // pop
            retour += cmpadd.getAddress() + " pop;\n";
            cmpadd.addAdress(1);

            // lexp
            retour += node.jjtGetChild(1).jjtAccept(this, data);
      }

      return retour;
  }

  public Object visit(ASTexnil node, Object data) throws MiniJajaVisitorException {
      String retour = "";
      return retour;
  }

  public Object visit(ASTnon node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // e
      retour += node.jjtGetChild(0).jjtAccept(this, data);
      //not
      retour += cmpadd.getAddress();
      retour += " not;\n";
      cmpadd.addAdress(1);

      return retour;
  }

  public Object visit(ASTneg node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // e
      retour += node.jjtGetChild(0).jjtAccept(this, data);
      //neg
      retour += cmpadd.getAddress();
      retour += " neg;\n";
      cmpadd.addAdress(1);

      return retour;
  }

  public Object visit(ASTet node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // e1
      retour += node.jjtGetChild(0).jjtAccept(this, data);
      // e
      retour += node.jjtGetChild(1).jjtAccept(this, data);
      //and
      retour += cmpadd.getAddress();
      retour += " and;\n";
      cmpadd.addAdress(1);

      return retour;
  }

  public Object visit(ASTou node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // e1
      retour += node.jjtGetChild(0).jjtAccept(this, data);
      // e
      retour += node.jjtGetChild(1).jjtAccept(this, data);
      //or
      retour += cmpadd.getAddress();
      retour += " or;\n";
      cmpadd.addAdress(1);

      return retour;
  }

  public Object visit(ASTegal node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // e1
      retour += node.jjtGetChild(0).jjtAccept(this, data);
      // e
      retour += node.jjtGetChild(1).jjtAccept(this, data);
      //cmp
      retour += cmpadd.getAddress();
      retour += " cmp;\n";
      cmpadd.addAdress(1);

      return retour;
  }

  public Object visit(ASTinf node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

            // e1
      retour += node.jjtGetChild(0).jjtAccept(this, data);
      // e
      retour += node.jjtGetChild(1).jjtAccept(this, data);
      //inf
      retour += cmpadd.getAddress();
      retour += " inf;\n";
      cmpadd.addAdress(1);

      return retour;
  }

  public Object visit(ASTsup node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // e1
      retour += node.jjtGetChild(0).jjtAccept(this, data);
      // e
      retour += node.jjtGetChild(1).jjtAccept(this, data);
      //sup
      retour += cmpadd.getAddress();
      retour += " sup;\n";
      cmpadd.addAdress(1);

      return retour;
  }

  public Object visit(ASTplus node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // e1
      retour += node.jjtGetChild(0).jjtAccept(this, data);
      // e
      retour += node.jjtGetChild(1).jjtAccept(this, data);
      //add
      retour += cmpadd.getAddress();
      retour += " add;\n";
      cmpadd.addAdress(1);

      return retour;
  }

  public Object visit(ASTmoins node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // e1
      retour += node.jjtGetChild(0).jjtAccept(this, data);
      // e
      retour += node.jjtGetChild(1).jjtAccept(this, data);
      //sub
      retour += cmpadd.getAddress();
      retour += " sub;\n";
      cmpadd.addAdress(1);

      return retour;
  }

  public Object visit(ASTmult node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // e1
      retour += node.jjtGetChild(0).jjtAccept(this, data);
      // e
      retour += node.jjtGetChild(1).jjtAccept(this, data);
      //mul
      retour += cmpadd.getAddress();
      retour += " mul;\n";
      cmpadd.addAdress(1);

      return retour;
  }

  public Object visit(ASTdiv node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // e1
      retour += node.jjtGetChild(0).jjtAccept(this, data);
      // e
      retour += node.jjtGetChild(1).jjtAccept(this, data);
      //div
      retour += cmpadd.getAddress();
      retour += " div;\n";
      cmpadd.addAdress(1);
      
      return retour;
  }

  public Object visit(ASTvrai node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // cvrai push(jctrue);
      retour += cmpadd.getAddress() + " push(true);\n";
      cmpadd.addAdress(1);

      return retour;
  }

  public Object visit(ASTfaux node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // cfaux push(jcfalse);
      retour += cmpadd.getAddress() + " push(false);\n";
      cmpadd.addAdress(1);

      return retour;
  }

  public Object visit(ASTappelE node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // plexp o+ D invoke(i) o+ prlexp

      //lexp
      retour += node.jjtGetChild(1).jjtAccept(this, data);
      //invoke
      retour += cmpadd.getAddress();
      retour += " invoke(" + ((ASTident)node.jjtGetChild(0)).getValeur() + ");\n";
      cmpadd.addAdress(1);
      //rlexp
      cmpadd.setRetrait(true);
      retour += node.jjtGetChild(1).jjtAccept(this, data);
      cmpadd.setRetrait(false);
      
      return retour;
  }

  public Object visit(ASTnbre node, Object data) throws MiniJajaVisitorException {
      Compilateur_address cmpadd = (Compilateur_address) data;
      String retour = "";

      // cnbre push(i)
      retour += cmpadd.getAddress() + " push(" + node.getValeur() + ");\n";
      cmpadd.addAdress(1);
      
      return retour;
  }

  public Object visit(ASTrien node, Object data) throws MiniJajaVisitorException {
      return "void";
  }

  public Object visit(ASTentier node, Object data) throws MiniJajaVisitorException {
      return "entier";
  }

  public Object visit(ASTbooleen node, Object data) throws MiniJajaVisitorException {
      return "booleen";
  }
}
