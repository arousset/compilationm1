/* Generated By:JJTree: Do not edit this line. ASTaffectation.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTaffectation extends SimpleNode {
  public ASTaffectation(int id) {
    super(id);
  }

  public ASTaffectation(MiniJaja p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(MiniJajaVisitor visitor, Object data) throws MiniJajaVisitorException {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=1ee52bc508dc16a17d02b7cc20027233 (do not edit this line) */