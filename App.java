import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class App {
    
    public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromFileName("programaEjemplo.cpp");

        CPPSubsetLexer lexer = new CPPSubsetLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CPPSubsetParser parser = new CPPSubsetParser(tokens);

        parser.removeErrorListeners();
        parser.addErrorListener(new DiagnosticErrorListener());

        // Generar árbol sintáctico desde la regla inicial 'program'
        ParseTree tree = parser.program();

        System.out.println("Arbol sintáctico:");
        System.out.println(tree.toStringTree(parser));

        // Análisis semántico
        ParseTreeWalker walker = new ParseTreeWalker();
        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
        walker.walk(semanticAnalyzer, tree);

        // Mostrar resultado del análisis semántico
        if (!semanticAnalyzer.hasErrors()) {
            System.out.println("Análisis semántico exitoso.");
        }
    }
}
