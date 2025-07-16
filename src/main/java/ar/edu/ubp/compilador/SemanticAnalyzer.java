// src/main/java/ar/edu/ubp/compilador/SemanticAnalyzer.java
package ar.edu.ubp.compilador;

import java.util.*;
import org.antlr.v4.runtime.tree.*;
import ar.edu.ubp.compilador.ast.*;

public class SemanticAnalyzer extends CPPSubsetParserBaseListener {
    private final Map<String,List<String>> functions = new HashMap<>();
    private boolean hasErrors = false;
    private final Map<String,String> symbolTable = new HashMap<>();

    @Override
    public void enterFunctionDecl(CPPSubsetParser.FunctionDeclContext ctx) {
        String name = ctx.ID().getText();
        List<String> types = new ArrayList<>();
        if (ctx.paramList() != null) {
            for (var p : ctx.paramList().param()) {
                types.add(p.type().getText());
            }
        }
        functions.put(name, types);
    }

    @Override
    public void enterFunctionCallExpr(CPPSubsetParser.FunctionCallExprContext ctx) {
        String name = ctx.ID().getText();
        if (!functions.containsKey(name)) {
            Logger.error("Función '" + name + "' no declarada");
            hasErrors = true;
        } else {
            int expected = functions.get(name).size();
            int given    = ctx.expr().size();
            if (expected != given) {
                Logger.error("Llamada a '" + name + "' con " + given +
                             " args; se esperaban " + expected);
                hasErrors = true;
            }
        }
    }

    @Override
    public void enterVariableDecl(CPPSubsetParser.VariableDeclContext ctx) {
        String var  = ctx.ID().getText();
        String type = ctx.type().getText();
        if (symbolTable.containsKey(var)) {
            Logger.error("Variable '" + var + "' ya declarada");
            hasErrors = true;
        } else {
            symbolTable.put(var, type);
        }
    }

    @Override
    public void enterAssignment(CPPSubsetParser.AssignmentContext ctx) {
        String var = ctx.ID().getText();
        if (!symbolTable.containsKey(var)) {
            Logger.error("Variable '" + var + "' no declarada");
            hasErrors = true;
        }
    }

    public boolean hasErrors() {
        return hasErrors;
    }

    /** Devuelve una vista inmutable de la tabla de símbolos. */
    public Map<String,String> getSymbolTable() {
        return Collections.unmodifiableMap(symbolTable);
    }
}
