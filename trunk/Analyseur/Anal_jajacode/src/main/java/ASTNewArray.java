/* Generated By:JJTree: Do not edit this line. ASTNewArray.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTNewArray extends SimpleNode {
  public ASTNewArray(int id) {
    super(id);
  }

  public ASTNewArray(JajaCode p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JajaCodeVisitor visitor, Object data) throws JajaCodeVisitorException {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=01968af9fb824553f91c07302e67f401 (do not edit this line) */