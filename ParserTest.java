import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class ParserTest {
    public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromFileName("programaEjemplo.cpp");

        CPPSubsetLexer lexer = new CPPSubsetLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CPPSubsetParser parser = new CPPSubsetParser(tokens);

        // Opcional: para ver errores más claros
        parser.removeErrorListeners();
        parser.addErrorListener(new DiagnosticErrorListener());

        // Iniciar desde la regla 'program'
        ParseTree tree = parser.program();

        // Mostrar el árbol (versión textual)
        System.out.println("Arbol sintactico:");
        System.out.println(tree.toStringTree(parser));
    }
}
