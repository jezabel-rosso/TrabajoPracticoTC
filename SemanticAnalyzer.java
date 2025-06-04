import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class SemanticAnalyzer extends CPPSubsetParserBaseListener {

    private Map<String, String> symbolTable = new HashMap<>();
    private boolean hasSemanticErrors = false;

    @Override
    public void enterVariableDecl(CPPSubsetParser.VariableDeclContext ctx) {
        String varName = ctx.ID().getText();
        String varType = ctx.type().getText();

        if (symbolTable.containsKey(varName)) {
            System.err.println("Error semántico: variable '" + varName + "' ya declarada.");
            hasSemanticErrors = true;
        } else {
            symbolTable.put(varName, varType);
        }
    }

    @Override
    public void enterAssignment(CPPSubsetParser.AssignmentContext ctx) {
        String varName = ctx.ID().getText();

        if (!symbolTable.containsKey(varName)) {
            System.err.println("Error semántico: variable '" + varName + "' no declarada.");
            hasSemanticErrors = true;
        }
    }

    public boolean hasErrors() {
        return hasSemanticErrors;
    }
}
