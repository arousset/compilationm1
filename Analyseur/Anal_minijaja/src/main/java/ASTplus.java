/* Generated By:JJTree: Do not edit this line. ASTplus.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTplus extends SimpleNode {
  public ASTplus(int id) {
    super(id);
  }

  public ASTplus(MiniJaja p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(MiniJajaVisitor visitor, Object data) throws MiniJajaVisitorException {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=d05620ed34d5678eba28b920fbf95769 (do not edit this line) */