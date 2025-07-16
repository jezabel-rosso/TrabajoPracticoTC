package ar.edu.ubp.compilador.ast;

import java.util.List;
import java.util.stream.Collectors;  // <<-- Asegurate de importar esto

public class FunctionCallNode implements ASTNode {
    private final String functionName;
    private final List<ASTNode> arguments;

    public FunctionCallNode(String functionName, List<ASTNode> arguments) {
        this.functionName = functionName;
        this.arguments = arguments;
    }

    // getter para IntermediateCodeGenerator
    public List<ASTNode> getArgs() {
        return arguments;
    }

    // mantiene el getter original
    public List<ASTNode> getArguments() {
        return arguments;
    }

    public String getFunctionName() {
        return functionName;
    }

    @Override
    public String toCode() {
        String argsCode = arguments.stream()
                                   .map(ASTNode::toCode)
                                   .collect(Collectors.joining(", "));
        return functionName + "(" + argsCode + ")";
    }
}
