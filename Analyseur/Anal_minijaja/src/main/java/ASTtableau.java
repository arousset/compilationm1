/* Generated By:JJTree: Do not edit this line. ASTtableau.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTtableau extends SimpleNode {
  public ASTtableau(int id) {
    super(id);
  }

  public ASTtableau(MiniJaja p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(MiniJajaVisitor visitor, Object data) throws MiniJajaVisitorException {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=de7f0a4e86d1ce7d54ed0c8ed75a9114 (do not edit this line) */
