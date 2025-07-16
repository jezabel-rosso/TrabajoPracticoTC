package ar.edu.ubp.compilador.ast;

import java.util.ArrayList;
import java.util.List;

public class CompoundNode implements ASTNode {
    private final List<ASTNode> children = new ArrayList<>();

    public void addChild(ASTNode node) {
        children.add(node);
    }

    public List<ASTNode> getChildren() {
        return children;
    }

    @Override
    public String toCode() {
        StringBuilder builder = new StringBuilder();
        for (ASTNode child : children) {
            builder.append(child.toCode()).append("\n");
        }
        return builder.toString();
    }
}
