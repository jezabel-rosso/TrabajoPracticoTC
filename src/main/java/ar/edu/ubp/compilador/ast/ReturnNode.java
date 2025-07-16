// ReturnNode.java
package ar.edu.ubp.compilador.ast;

public class ReturnNode implements ASTNode {
    private final ASTNode expression;

    public ReturnNode(ASTNode expression) {
        this.expression = expression;
    }

    // alias para IntermediateCodeGenerator
    public ASTNode getValue() {
        return expression;
    }

    // mantiene el getter original
    public ASTNode getExpression() {
        return expression;
    }

    @Override
    public String toCode() {
        return "return " + (expression != null ? expression.toCode() : "") + ";";
    }
}
