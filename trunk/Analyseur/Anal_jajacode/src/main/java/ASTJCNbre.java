/* Generated By:JJTree: Do not edit this line. ASTJCNbre.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTJCNbre extends SimpleNode {
  private int nombre;
	
  public ASTJCNbre(int id) {
    super(id);
  }

  public ASTJCNbre(JajaCode p, int id) {
    super(p, id);
  }

  public void setValeur(int nb){
	this.nombre=nb;
  }
  
  public int getValeur(){
	return this.nombre;
  }

  /** Accept the visitor. **/
  public Object jjtAccept(JajaCodeVisitor visitor, Object data) throws JajaCodeVisitorException {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=6b6cf67ac1c9d0cd7562b06f313e35fa (do not edit this line) */