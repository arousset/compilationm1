/* Generated By:JJTree: Do not edit this line. ASTident.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */

public
class ASTident extends SimpleNode {
	//Ajout d'une variable
	private String ident;
	
  public ASTident(int id) {
    super(id);
  }

  public ASTident(MiniJaja p, int id) {
    super(p, id);
  }

  //Ajout de méthodes pour récuperer une valeur
  public void setValeur(String str){
	this.ident=str;
  }
	  
  public String getValeur(){
	return this.ident;
  }

  /** Accept the visitor. **/
  public Object jjtAccept(MiniJajaVisitor visitor, Object data) throws MiniJajaVisitorException {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=a60b774d4230ac9f405179d55ea8c3d0 (do not edit this line) */
