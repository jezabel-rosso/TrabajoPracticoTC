import java.util.HashMap;
import java.util.Map;

public class SemanticAnalyzer extends CPPSubsetParserBaseListener {

    private final Map<String, String> symbolTable = new HashMap<>();
    private boolean hasSemanticErrors = false;

    @Override
    public void enterProgram(CPPSubsetParser.ProgramContext ctx) {
        System.out.println("Comenzando análisis semántico...");
    }

    @Override
    public void enterVariableDecl(CPPSubsetParser.VariableDeclContext ctx) {
        String varName = ctx.ID().getText();
        String varType = ctx.type().getText();

        if (symbolTable.containsKey(varName)) {
            System.err.println("Error semántico: variable '" + varName + "' ya declarada.");
            hasSemanticErrors = true;
        } else {
            symbolTable.put(varName, varType);
            System.out.println("Variable declarada: " + varName + " de tipo " + varType);
        }
    }

    @Override
    public void enterAssignment(CPPSubsetParser.AssignmentContext ctx) {
        String varName = ctx.ID().getText();

        if (!symbolTable.containsKey(varName)) {
            System.err.println("Error semántico: variable '" + varName + "' no declarada.");
            hasSemanticErrors = true;
        } else {
            System.out.println("Asignación válida a la variable: " + varName);
        }
    }

    public boolean hasErrors() {
        return hasSemanticErrors;
    }
}
