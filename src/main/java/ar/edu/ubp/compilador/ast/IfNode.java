// src/main/java/ar/edu/ubp/compilador/ast/IfNode.java
package ar.edu.ubp.compilador.ast;

public class IfNode implements ASTNode {
    private final ASTNode condition;
    private final CompoundNode thenBranch;
    private final CompoundNode elseBranch; // puede ser null

    public IfNode(
        ASTNode condition,
        CompoundNode thenBranch,
        CompoundNode elseBranch
    ) {
        this.condition  = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    public ASTNode getCondition()       { return condition; }
    public CompoundNode getThenBranch() { return thenBranch; }
    public CompoundNode getElseBranch() { return elseBranch; }

    @Override
    public String toCode() {
        String s = "if (" + condition.toCode() + ") " + thenBranch.toCode();
        if (elseBranch != null) {
            s += " else " + elseBranch.toCode();
        }
        return s;
    }
}
