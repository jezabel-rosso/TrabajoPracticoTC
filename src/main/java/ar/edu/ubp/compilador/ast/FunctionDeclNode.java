// src/main/java/ar/edu/ubp/compilador/ast/FunctionDeclNode.java
package ar.edu.ubp.compilador.ast;

import java.util.List;

public class FunctionDeclNode implements ASTNode {
    private final String returnType;
    private final String name;
    private final List<String> paramTypes;
    private final List<String> paramNames;
    private final CompoundNode body;

    public FunctionDeclNode(
        String returnType,
        String name,
        List<String> paramTypes,
        List<String> paramNames,
        CompoundNode body
    ) {
        this.returnType = returnType;
        this.name       = name;
        this.paramTypes = paramTypes;
        this.paramNames = paramNames;
        this.body       = body;
    }

    public String getReturnType() { return returnType; }
    public String getName()       { return name; }
    public List<String> getParamTypes() { return paramTypes; }
    public List<String> getParamNames() { return paramNames; }
    public CompoundNode getBody()       { return body; }

    @Override
    public String toCode() {
        return returnType + " " + name + "(" +
               String.join(", ", paramTypes) + ") " +
               body.toCode();
    }
}
