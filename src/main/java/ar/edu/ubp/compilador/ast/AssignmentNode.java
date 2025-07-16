// AssignmentNode.java
package ar.edu.ubp.compilador.ast;

public class AssignmentNode implements ASTNode {
    private final String variableName;
    private final ASTNode expression;

    public AssignmentNode(String variableName, ASTNode expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    // alias para IntermediateCodeGenerator
    public String getName() {
        return variableName;
    }

    // mantiene el getter original
    public String getVariableName() {
        return variableName;
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
        return variableName + " = " + expression.toCode() + ";";
    }
}
