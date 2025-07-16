// src/main/java/ar/edu/ubp/compilador/ast/ForNode.java
package ar.edu.ubp.compilador.ast;

public class ForNode implements ASTNode {
    private final ASTNode init;
    private final ASTNode condition;
    private final ASTNode update;
    private final CompoundNode body;

    public ForNode(
        ASTNode init,
        ASTNode condition,
        ASTNode update,
        CompoundNode body
    ) {
        this.init      = init;
        this.condition = condition;
        this.update    = update;
        this.body      = body;
    }

    public ASTNode getInit()      { return init; }
    public ASTNode getCondition(){ return condition; }
    public ASTNode getUpdate()   { return update; }
    public CompoundNode getBody(){ return body; }

    @Override
    public String toCode() {
        return "for(" + init.toCode() + "; " +
                      condition.toCode() + "; " +
                      update.toCode() + ") " +
               body.toCode();
    }
}
