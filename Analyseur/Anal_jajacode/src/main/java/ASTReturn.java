/* Generated By:JJTree: Do not edit this line. ASTReturn.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTReturn extends SimpleNode {
  public ASTReturn(int id) {
    super(id);
  }

  public ASTReturn(JajaCode p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JajaCodeVisitor visitor, Object data) throws JajaCodeVisitorException {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=bdda117c1a1fa3389fe9e7d53df6ea09 (do not edit this line) */