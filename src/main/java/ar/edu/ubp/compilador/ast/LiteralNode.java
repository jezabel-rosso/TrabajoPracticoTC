package ar.edu.ubp.compilador.ast;

public class LiteralNode implements ASTNode {
    private final String value;

    public LiteralNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean isNumeric() {
        return value.matches("-?\\d+(\\.\\d+)?");  // admite int y double
    }

    @Override
    public String toCode() {
        return value;
    }
}
