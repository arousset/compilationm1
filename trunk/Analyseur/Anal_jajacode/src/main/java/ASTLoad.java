/* Generated By:JJTree: Do not edit this line. ASTLoad.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTLoad extends SimpleNode {
  public ASTLoad(int id) {
    super(id);
  }

  public ASTLoad(JajaCode p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JajaCodeVisitor visitor, Object data) throws JajaCodeVisitorException {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=c70abfc96911425b91dd61a6f84b49ed (do not edit this line) */
