package ar.edu.ubp.compilador.ast;

import java.util.ArrayList;
import java.util.List;

public class ASTCodeGenerator {

    public List<String> generate(ASTNode node) {
        List<String> code = new ArrayList<>();
        generateRecursive(node, code);
        return code;
    }

    private void generateRecursive(ASTNode node, List<String> code) {
        if (node == null) return;

        if (node instanceof CompoundNode) {
            for (ASTNode child : ((CompoundNode) node).getChildren()) {
                generateRecursive(child, code);
            }
        } else {
            String line = node.toCode();
            if (line != null && !line.isEmpty()) {
                code.add(line);
            }
        }
    }
}

