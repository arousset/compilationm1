/* Generated By:JJTree: Do not edit this line. ASTlistexp.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTlistexp extends SimpleNode {
  public ASTlistexp(int id) {
    super(id);
  }

  public ASTlistexp(MiniJaja p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(MiniJajaVisitor visitor, Object data) throws MiniJajaVisitorException {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=d93449f7f964f826e7db393cc3aba9cf (do not edit this line) */
