/* Generated By:JJTree: Do not edit this line. ASTNeg.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTNeg extends SimpleNode {
  public ASTNeg(int id) {
    super(id);
  }

  public ASTNeg(JajaCode p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JajaCodeVisitor visitor, Object data) throws JajaCodeVisitorException {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=4d66fe7e456e57c68f8bccfa5fb81fbc (do not edit this line) */