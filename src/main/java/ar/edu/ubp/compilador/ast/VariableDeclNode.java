// VariableDeclNode.java
package ar.edu.ubp.compilador.ast;

public class VariableDeclNode implements ASTNode {
    private final String type;
    private final String variableName;
    private final ASTNode value;

    public VariableDeclNode(String type, String variableName, ASTNode value) {
        this.type = type;
        this.variableName = variableName;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    // alias para IntermediateCodeGenerator
    public String getName() {
        return variableName;
    }

    // mantiene el getter original
    public String getVariableName() {
        return variableName;
    }

    // mantiene el getter original
    public ASTNode getValue() {
        return value;
    }

    @Override
    public String toCode() {
        if (value != null) {
            return type + " " + variableName + " = " + value.toCode() + ";";
        } else {
            return type + " " + variableName + ";";
        }
    }
}
