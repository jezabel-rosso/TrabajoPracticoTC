import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.tree.Trees;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class App {

    public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromFileName("programaEjemplo.cpp");

        CPPSubsetLexer lexer = new CPPSubsetLexer(input);

        // Mostrar tabla de análisis léxico
        System.out.println("Tabla de análisis léxico:");
        lexer.reset();
        Token token;
        while ((token = lexer.nextToken()).getType() != Token.EOF) {
            String tipo = CPPSubsetLexer.VOCABULARY.getSymbolicName(token.getType());
            System.out.printf("Línea %d\tToken: %-15s\tLexema: '%s'%n", token.getLine(), tipo, token.getText());
        }
        System.out.println();

        // Volver a crear el lexer para el parser
        lexer.reset();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CPPSubsetParser parser = new CPPSubsetParser(tokens);

        parser.removeErrorListeners();
        parser.addErrorListener(new DiagnosticErrorListener());

        // Generar árbol sintáctico desde la regla inicial 'program'
        ParseTree tree = parser.program();

        System.out.println("Arbol sintáctico:");
        System.out.println(tree.toStringTree(parser));

        // Exportar a archivo DOT
        exportParseTreeToDot(tree, parser, "arbol.dot");
        System.out.println("Archivo DOT generado como arbol.dot");

        // Análisis semántico
        ParseTreeWalker walker = new ParseTreeWalker();
        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
        walker.walk(semanticAnalyzer, tree);

        if (!semanticAnalyzer.hasErrors()) {
            System.out.println("Análisis semántico exitoso.");
        }
    }

    public static void exportParseTreeToDot(ParseTree tree, Parser parser, String filename) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write("digraph G {\n");
        writer.write("  node [shape=box];\n");
        exportNode(tree, parser, writer, 0, new AtomicInteger(1));
        writer.write("}\n");
        writer.close();
    }

    private static int exportNode(ParseTree node, Parser parser, BufferedWriter writer, int id, AtomicInteger counter) throws IOException {
        String nodeName = "node" + id;
        String label = escapeLabel(Trees.getNodeText(node, parser));
        writer.write("  " + nodeName + " [label=\"" + label + "\"];\n");

        for (int i = 0; i < node.getChildCount(); i++) {
            int childId = counter.getAndIncrement();
            String childName = "node" + childId;
            writer.write("  " + nodeName + " -> " + childName + ";\n");
            exportNode(node.getChild(i), parser, writer, childId, counter);
        }

        return id;
    }

    private static String escapeLabel(String text) {
        return text.replace("\"", "\\\"");
    }
}
