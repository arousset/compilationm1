/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anais
 */
public class ControlerTypeVisitor implements MiniJajaVisitor {

   public Object visit(SimpleNode node, Object data) throws MiniJajaVisitorException {
		return null;
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTclasse node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;
		String id = (String)node.jjtGetChild(0).jjtAccept(this, data);
		ctdata.setPortee(id);
		boolean ret = (Boolean)node.jjtGetChild(1).jjtAccept(this, data);
		ret &= (Boolean)node.jjtGetChild(2).jjtAccept(this, data);
		return ret;
	}


	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTident node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;
		if(ctdata.isInEvalType()){
			//try {
				if(ctdata.isInEvalType()){
					return ctdata.ts.rechercheType(node.getValeur(),ctdata.getEvalGenre(),ctdata.getPortee());
				}else{
					return ctdata.ts.rechercheType(node.getValeur(),"var",ctdata.getPortee());
				}
			/*}catch(PileException e){
				throw new SymboleNotExistException(e.toString());
			}*/
		}else{
			return node.getValeur();
		}
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTdecls node, Object data) throws MiniJajaVisitorException {
		boolean ret = (Boolean)node.jjtGetChild(0).jjtAccept(this, data);
		ret &= (Boolean)node.jjtGetChild(1).jjtAccept(this, data);
		return ret;
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTvnil node, Object data) throws MiniJajaVisitorException {
		return true;
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTcst node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		String id = (String)node.jjtGetChild(1).jjtAccept(this, data);
		String sorte = (String)node.jjtGetChild(0).jjtAccept(this, data);
		ctdata.ts.add(new Symbole(id,"cst",sorte,ctdata.getPortee()));

		ctdata.setEvalType();
		String typeval=(String)node.jjtGetChild(2).jjtAccept(this, data);
		ctdata.unsetEvalType();

		if(!typeval.equals("void") && !typeval.equals(sorte)){
			throw new TypeIncompatibleException("declaration de <" + id + ",cst," + ctdata.getPortee() + "> de type <"+ sorte + "> ne peut etre affecter de <" + typeval + ">");
		}

		return true;
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTtableau node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		String id = (String)node.jjtGetChild(1).jjtAccept(this, data);
		String sorte = (String)node.jjtGetChild(0).jjtAccept(this, data);
		ctdata.ts.add(new Symbole(id,"tab",sorte,ctdata.getPortee()));

		ctdata.setEvalType();
		String typeval=(String)node.jjtGetChild(2).jjtAccept(this, data);
		ctdata.unsetEvalType();

		if(!typeval.equals("entier")){
			throw new TypeIncompatibleException("declaration de <" + id + ",tab," + ctdata.getPortee() + "> : doit etre <"+ sorte + ">[<entier>] au lieu de <"+ sorte + ">[<" + typeval + ">]");
		}

		return true;
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTmethode node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		String id = (String)node.jjtGetChild(1).jjtAccept(this, data);
		String sorte = (String)node.jjtGetChild(0).jjtAccept(this, data);

		String oldportee = ctdata.getPortee();
		ctdata.setPortee(id);
		String entetes = (String)node.jjtGetChild(2).jjtAccept(this, data);
		ctdata.setPortee(oldportee);

		ctdata.ts.add(new Symbole(id,"meth",sorte,entetes,oldportee));

		ctdata.setPortee(id);
		node.jjtGetChild(3).jjtAccept(this, data);
		node.jjtGetChild(4).jjtAccept(this, data);
		ctdata.setPortee(oldportee);

		return true;
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTvar node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		String id = (String)node.jjtGetChild(1).jjtAccept(this, data);
		String sorte = (String)node.jjtGetChild(0).jjtAccept(this, data);
		ctdata.ts.add(new Symbole(id,"var",sorte,ctdata.getPortee()));

		ctdata.setEvalType();
		String typeval=(String)node.jjtGetChild(2).jjtAccept(this, data);
		ctdata.unsetEvalType();

		if(!typeval.equals("void") && !typeval.equals(sorte)){
			throw new TypeIncompatibleException("declaration de <" + id + ",var," + ctdata.getPortee() + "> de type <"+ sorte + "> ne peut etre affecter de <" + typeval + ">");
		}

		return true;
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTvars node, Object data) throws MiniJajaVisitorException {
		boolean ret = (Boolean)node.jjtGetChild(0).jjtAccept(this, data);
		ret &= (Boolean)node.jjtGetChild(1).jjtAccept(this, data);
		return ret;
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTomega node, Object data) throws MiniJajaVisitorException {
		return "void";
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTmain node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		ctdata.ts.add(new Symbole("main","meth","","",ctdata.getPortee()));

		String oldportee = ctdata.getPortee();
		ctdata.setPortee("main");
		boolean ret = (Boolean)node.jjtGetChild(0).jjtAccept(this, data);
		ret &= (Boolean)node.jjtGetChild(1).jjtAccept(this, data);
		ctdata.setPortee(oldportee);
		return ret;
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTentetes node, Object data) throws MiniJajaVisitorException {
		String entete = (String)node.jjtGetChild(0).jjtAccept(this, data);
		String entetes = (String)node.jjtGetChild(1).jjtAccept(this, data);
		return entete+(entetes!=""?"*":"")+entetes;
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTenil node, Object data) throws MiniJajaVisitorException {
		return "";
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTentete node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		String id = (String)node.jjtGetChild(1).jjtAccept(this, data);
		String sorte = (String)node.jjtGetChild(0).jjtAccept(this, data);
		ctdata.ts.add(new Symbole(id,"var",sorte,ctdata.getPortee()));

		return sorte;
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTinstrs node, Object data) throws MiniJajaVisitorException {
		boolean ret = (Boolean)node.jjtGetChild(0).jjtAccept(this, data);
		ret &= (Boolean)node.jjtGetChild(1).jjtAccept(this, data);
		return ret;
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTinil node, Object data) throws MiniJajaVisitorException {
		return true;
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTretour node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;
		try {
			String porteeMeth = ctdata.ts.recherchePortee(ctdata.getPortee(), "meth");
			String sorte = ctdata.ts.rechercheType(ctdata.getPortee(), "meth",porteeMeth);

			ctdata.setEvalType();
			String typeval = (String)node.jjtGetChild(0).jjtAccept(this, data);
			ctdata.unsetEvalType();

			if(sorte.equals(typeval)){
				return true;
			}else{
				throw new TypeIncompatibleException("methode de <" + ctdata.getPortee() + ",meth," + porteeMeth + "> de type <"+ sorte + "> ne peut retourner le type <" + typeval + ">");
			}
		}catch(MiniJajaVisitorException e){
			throw new SymboleNotExistException(e.toString());
		}
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTtantque node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		ctdata.setEvalType();
		String typeval = (String)node.jjtGetChild(0).jjtAccept(this, data);
		ctdata.unsetEvalType();

		if(typeval.equals("booleen")){
			return (Boolean)node.jjtGetChild(1).jjtAccept(this, data);
		}else{
			throw new TypeIncompatibleException("ne peut effectuer l'instruction <tantque> dans <"+ ctdata.getPortee() + "> avec le type <" + typeval + ">");
		}
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTsi node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		ctdata.setEvalType();
		String typeval = (String)node.jjtGetChild(0).jjtAccept(this, data);
		ctdata.unsetEvalType();

		if(typeval.equals("booleen")){
			return (Boolean)node.jjtGetChild(1).jjtAccept(this, data) &&
				(Boolean)node.jjtGetChild(2).jjtAccept(this, data);
		}else{
			throw new TypeIncompatibleException("ne peut effectuer l'instruction <tantque> dans <"+ ctdata.getPortee() + "> avec le type <" + typeval + ">");
		}
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTappelI node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		try{
			ctdata.unsetEvalType();
			String id = (String)node.jjtGetChild(0).jjtAccept(this, data);
			ctdata.setEvalType();
			ctdata.setEvalGenre("meth");
			String typeval = (String)node.jjtGetChild(0).jjtAccept(this, data);
			ctdata.setEvalGenre(null);
			String profil = (String)node.jjtGetChild(1).jjtAccept(this, data);
			
				return true;
			
		}catch(MiniJajaVisitorException e){
			throw new SymboleNotExistException(e.toString());
		}
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTaffectation node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		ctdata.setEvalType();
		String sorte1 = (String)node.jjtGetChild(0).jjtAccept(this, data);
		String sorte2 = (String)node.jjtGetChild(1).jjtAccept(this, data);
		ctdata.unsetEvalType();

		if(!sorte1.equals(sorte2)){
			throw new TypeIncompatibleException("dans <" + ctdata.getPortee() + ">, operation <affectation> impossible entre <" + sorte1 + "> et <"+ sorte2 + "> ");
		}

		return true;
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTsomme node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		ctdata.setEvalType();
		String sorte1 = (String)node.jjtGetChild(0).jjtAccept(this, data);
		String sorte2 = (String)node.jjtGetChild(1).jjtAccept(this, data);
		ctdata.unsetEvalType();

		if(!sorte1.equals("entier") || !sorte2.equals("entier")){
			throw new TypeIncompatibleException("dans <" + ctdata.getPortee() + ">, operation <somme> impossible entre <" + sorte1 + "> et <"+ sorte2 + "> ");
		}

		return true;
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTincrement node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		ctdata.setEvalType();
		String sorte = (String)node.jjtGetChild(0).jjtAccept(this, data);
		ctdata.unsetEvalType();

		if(!sorte.equals("entier")){
			throw new TypeIncompatibleException("dans <" + ctdata.getPortee() + ">, operation <increment> avec le type <" + sorte + ">");
		}

		return true;
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTtab node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		ctdata.setEvalGenre("tab");
		String typeval = (String)node.jjtGetChild(0).jjtAccept(this, data);
		ctdata.setEvalGenre(null);

		String index = (String)node.jjtGetChild(1).jjtAccept(this, data);

		if(!index.equals("entier")){
			throw new TypeIncompatibleException("dans <" + ctdata.getPortee() + ">, index de tableau doit etre du type <entier> au lieu de <" + typeval + ">");
		}

		return typeval;
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTlistexp node, Object data) throws MiniJajaVisitorException {
		String entete = (String)node.jjtGetChild(0).jjtAccept(this, data);
		String entetes = (String)node.jjtGetChild(1).jjtAccept(this, data);
		return entete+(entetes!=""?"*":"")+entetes;
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTexnil node, Object data) throws MiniJajaVisitorException {
		return "";
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTnon node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		String sorte = (String)node.jjtGetChild(0).jjtAccept(this, data);

		if(!sorte.equals("booleen")){
			throw new TypeIncompatibleException("dans <" + ctdata.getPortee() + ">, operation <ou> avec <" + sorte + "> ");
		}

		return "booleen";
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTneg node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		String sorte = (String)node.jjtGetChild(0).jjtAccept(this, data);

		if(!sorte.equals("entier")){
			throw new TypeIncompatibleException("dans <" + ctdata.getPortee() + ">, operation <neg> avec <" + sorte + "> ");
		}

		return "entier";
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTet node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		String sorte1 = (String)node.jjtGetChild(0).jjtAccept(this, data);
		String sorte2 = (String)node.jjtGetChild(1).jjtAccept(this, data);

		if(!sorte1.equals("booleen") || !sorte2.equals("booleen")){
			throw new TypeIncompatibleException("dans <" + ctdata.getPortee() + ">, operation <et> impossible entre <" + sorte1 + "> et <"+ sorte2 + "> ");
		}

		return "booleen";
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTou node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		String sorte1 = (String)node.jjtGetChild(0).jjtAccept(this, data);
		String sorte2 = (String)node.jjtGetChild(1).jjtAccept(this, data);

		if(!sorte1.equals("booleen") || !sorte2.equals("booleen")){
			throw new TypeIncompatibleException("dans <" + ctdata.getPortee() + ">, operation <ou> impossible entre <" + sorte1 + "> et <"+ sorte2 + "> ");
		}

		return "booleen";
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTegal node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		String sorte1 = (String)node.jjtGetChild(0).jjtAccept(this, data);
		String sorte2 = (String)node.jjtGetChild(1).jjtAccept(this, data);

		if(!sorte1.equals("entier") || !sorte2.equals("entier")){
			throw new TypeIncompatibleException("dans <" + ctdata.getPortee() + ">, operation <egal> impossible entre <" + sorte1 + "> et <"+ sorte2 + "> ");
		}

		return "entier";
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTinf node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		String sorte1 = (String)node.jjtGetChild(0).jjtAccept(this, data);
		String sorte2 = (String)node.jjtGetChild(1).jjtAccept(this, data);

		if(!sorte1.equals("entier") || !sorte2.equals("entier")){
			throw new TypeIncompatibleException("dans <" + ctdata.getPortee() + ">, operation <inf> impossible entre <" + sorte1 + "> et <"+ sorte2 + "> ");
		}

		return "booleen";
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTsup node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		String sorte1 = (String)node.jjtGetChild(0).jjtAccept(this, data);
		String sorte2 = (String)node.jjtGetChild(1).jjtAccept(this, data);

		if(!sorte1.equals("entier") || !sorte2.equals("entier")){
			throw new TypeIncompatibleException("dans <" + ctdata.getPortee() + ">, operation <sup> impossible entre <" + sorte1 + "> et <"+ sorte2 + "> ");
		}

		return "booleen";
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTplus node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		String sorte1 = (String)node.jjtGetChild(0).jjtAccept(this, data);
		String sorte2 = (String)node.jjtGetChild(1).jjtAccept(this, data);

		if(!sorte1.equals("entier") || !sorte2.equals("entier")){
			throw new TypeIncompatibleException("dans <" + ctdata.getPortee() + ">, operation <plus> impossible entre <" + sorte1 + "> et <"+ sorte2 + "> ");
		}

		return "entier";
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTmoins node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		String sorte1 = (String)node.jjtGetChild(0).jjtAccept(this, data);
		String sorte2 = (String)node.jjtGetChild(1).jjtAccept(this, data);

		if(!sorte1.equals("entier") || !sorte2.equals("entier")){
			throw new TypeIncompatibleException("dans <" + ctdata.getPortee() + ">, operation <moins> impossible entre <" + sorte1 + "> et <"+ sorte2 + "> ");
		}

		return "entier";
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTmult node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		String sorte1 = (String)node.jjtGetChild(0).jjtAccept(this, data);
		String sorte2 = (String)node.jjtGetChild(1).jjtAccept(this, data);

		if(!sorte1.equals("entier") || !sorte2.equals("entier")){
			throw new TypeIncompatibleException("dans <" + ctdata.getPortee() + ">, operation <mult> impossible entre <" + sorte1 + "> et <"+ sorte2 + "> ");
		}

		return "entier";
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTdiv node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		String sorte1 = (String)node.jjtGetChild(0).jjtAccept(this, data);
		String sorte2 = (String)node.jjtGetChild(1).jjtAccept(this, data);

		if(!sorte1.equals("entier") || !sorte2.equals("entier")){
			throw new TypeIncompatibleException("dans <" + ctdata.getPortee() + ">, operation <div> impossible entre <" + sorte1 + "> et <"+ sorte2 + "> ");
		}

		return "entier";
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTvrai node, Object data) throws MiniJajaVisitorException {
		return "booleen";
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTfaux node, Object data) throws MiniJajaVisitorException {
		return "booleen";
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTappelE node, Object data) throws MiniJajaVisitorException {
		ControlerTypeData ctdata = (ControlerTypeData)data;

		try{
			ctdata.unsetEvalType();
			String id = (String)node.jjtGetChild(0).jjtAccept(this, data);
			ctdata.setEvalType();
			ctdata.setEvalGenre("meth");
			String typeval = (String)node.jjtGetChild(0).jjtAccept(this, data);
			ctdata.setEvalGenre(null);
			String profil = (String)node.jjtGetChild(1).jjtAccept(this, data);
			
				return typeval;
			
		}catch(MiniJajaVisitorException e){
			throw new SymboleNotExistException(e.toString());
		}

	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTnbre node, Object data) throws MiniJajaVisitorException {
		return "entier";
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTrien node, Object data) throws MiniJajaVisitorException {
		return "void";
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTentier node, Object data) throws MiniJajaVisitorException {
		return "entier";
	}

	/**
	 * Effectue le controle de type pour le noeud courant, en utilisant une table des symboles
	 * @param node Noeud a traiter
	 * @param data Table des symboles
	 */
	public Object visit(ASTbooleen node, Object data) throws MiniJajaVisitorException {
		return "booleen";
	}
}
