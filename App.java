import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.tree.Trees;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class App {

    public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromFileName("programaEjemplo.cpp");

        CPPSubsetLexer lexer = new CPPSubsetLexer(input);

        Logger.log("Tabla de análisis léxico:");
        lexer.reset();
        Token token;
        while ((token = lexer.nextToken()).getType() != Token.EOF) {
            String tipo = CPPSubsetLexer.VOCABULARY.getSymbolicName(token.getType());
            Logger.log(String.format("Línea %d\tToken: %-15s\tLexema: '%s'%n", token.getLine(), tipo, token.getText()));
        }

        lexer.reset();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CPPSubsetParser parser = new CPPSubsetParser(tokens);

        parser.removeErrorListeners();
        parser.addErrorListener(new DiagnosticErrorListener());

        ParseTree tree = parser.program();

        Logger.log("Arbol sintáctico:");
        Logger.log(tree.toStringTree(parser));

        exportParseTreeToDot(tree, parser, "arbol.dot");
        Logger.log("Archivo DOT generado como arbol.dot");

        ParseTreeWalker walker = new ParseTreeWalker();
        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
        Logger.log("Comenzando análisis semántico...");
        walker.walk(semanticAnalyzer, tree);

        if (!semanticAnalyzer.hasErrors()) {
            Logger.log("Análisis semántico exitoso.");

            IntermediateCodeGenerator generator = new IntermediateCodeGenerator();
            generator.visit(tree);
            List<String> code = generator.getCode();

            Logger.log("\nCódigo intermedio:");
            for (String line : code) {
                Logger.log(line);
            }

            Files.write(Paths.get("codigoIntermedio.txt"), code);
            Logger.log("\nArchivo 'codigoIntermedio.txt' generado.");

            CodeOptimizer optimizer = new CodeOptimizer(code);
            List<String> optimizedCode = optimizer.optimize();

            Logger.log("\nCódigo intermedio optimizado:");
            for (String line : optimizedCode) {
                Logger.log(line);
            }

            Files.write(Paths.get("codigoIntermedioOptimizado.txt"), optimizedCode);
            Logger.log("\nArchivo 'codigoIntermedioOptimizado.txt' generado.");
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
