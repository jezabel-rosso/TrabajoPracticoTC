// BinaryExprNode.java
package ar.edu.ubp.compilador.ast;

public class BinaryExprNode implements ASTNode {
    private final String operator;
    private final ASTNode left;
    private final ASTNode right;

    public BinaryExprNode(String operator, ASTNode left, ASTNode right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    // alias para IntermediateCodeGenerator
    public String getOp() {
        return operator;
    }

    // mantiene el getter original
    public String getOperator() {
        return operator;
    }

    public ASTNode getLeft() {
        return left;
    }

    public ASTNode getRight() {
        return right;
    }

    @Override
    public String toCode() {
        return "(" + left.toCode() + " " + operator + " " + right.toCode() + ")";
    }
}
