// src/main/java/ar/edu/ubp/compilador/App.java
package ar.edu.ubp.compilador;

import ar.edu.ubp.compilador.ast.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class App {

    public static void main(String[] args) throws Exception {
        // =========================
        // FASE 1: Análisis Léxico
        // =========================
        Logger.success("[FASE 1] Análisis léxico iniciado");
        CharStream input = CharStreams.fromFileName("programaEjemplo.cpp");
        CPPSubsetLexer lexer = new CPPSubsetLexer(input);

        // — Primera salida: línea a línea
        lexer.reset();
        Token token;
        while ((token = lexer.nextToken()).getType() != Token.EOF) {
            String tipo = CPPSubsetLexer.VOCABULARY.getSymbolicName(token.getType());
            Logger.log(String.format("Línea %d Token: %-15s Lexema: '%s'",
                                     token.getLine(), tipo, token.getText()));
        }

        // — Segunda salida: tabla formateada
        lexer.reset();
        List<Token> tokens = new ArrayList<>();
        while ((token = lexer.nextToken()).getType() != Token.EOF) {
            tokens.add(token);
        }

        System.out.println();
        System.out.println("| Nº  | Lexema          | Token      | Tipo          | Línea |");
        System.out.println("|-----|-----------------|------------|---------------|-------|");
        int nro = 1;
        for (Token t : tokens) {
            String lex = t.getText();
            String sym = CPPSubsetLexer.VOCABULARY.getSymbolicName(t.getType());
            String cat = classifyToken(sym);
            int    ln  = t.getLine();
            System.out.printf("| %-3d | %-15s | %-10s | %-13s | %-5d |%n",
                              nro++, lex, sym, cat, ln);
        }
        System.out.println();

        Logger.success("[FASE 1] Análisis léxico completo\n");

        // ================================
        // FASE 2: Análisis Sintáctico
        // ================================
        Logger.success("[FASE 2] Análisis sintáctico iniciado");
        lexer.reset();
        CommonTokenStream stream = new CommonTokenStream(lexer);
        CPPSubsetParser parser = new CPPSubsetParser(stream);
        parser.removeErrorListeners();
        parser.addErrorListener(new DiagnosticErrorListener());

        ParseTree tree = parser.program();
        Logger.success("[FASE 2] Árbol sintáctico generado\n");

        Logger.success("[Visualización] Exportando árbol sintáctico como archivo DOT");
        exportParseTreeToDot(tree, parser, "arbol.dot");
        Logger.success("[Visualización] Archivo DOT generado como arbol.dot\n");

        // ================================
        // FASE 3: Análisis Semántico
        // ================================
        Logger.success("[FASE 3] Análisis semántico iniciado");
        ParseTreeWalker walker = new ParseTreeWalker();
        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
        walker.walk(semanticAnalyzer, tree);

        if (semanticAnalyzer.hasErrors()) {
            Logger.error("[FASE 3] Se encontraron errores semánticos");
            return;
        } else {
            Logger.success("[FASE 3] Análisis semántico exitoso\n");
        }

        // Imprimir tabla de símbolos
        Map<String, String> symbols = semanticAnalyzer.getSymbolTable();
        System.out.println("┌───────────────┬────────────┐");
        System.out.println("│ Nombre        │ Tipo       │");
        System.out.println("├───────────────┼────────────┤");
        symbols.keySet().stream().sorted()
               .forEach(name -> {
                   String type = symbols.get(name);
                   System.out.printf("│ %-13s │ %-10s │%n", name, type);
               });
        System.out.println("└───────────────┴────────────┘\n");

        // ====================================
        // FASE 4: Construcción de AST & Código Intermedio
        // ====================================
        Logger.success("[FASE 4] Construcción de AST iniciada");
        ASTBuilder builder = new ASTBuilder();
        ASTNode ast = builder.visit(tree);
        Logger.success("[FASE 4] AST construido correctamente\n");

        Logger.success("[FASE 4] Generación de código intermedio desde AST");
        IntermediateCodeGenerator icg = new IntermediateCodeGenerator();
        String intermediate = icg.generate(ast);
        List<String> code = Arrays.asList(intermediate.split("\\n"));

        Logger.log("\nCódigo intermedio:");
        code.forEach(Logger::log);
        Files.write(Paths.get("codigoIntermedio.txt"), code);
        Logger.success("\n[FASE 4] Código intermedio generado en 'codigoIntermedio.txt'\n");

        // ========================
        // FASE 5: Optimización
        // ========================
        Logger.success("[FASE 5] Optimización de código iniciada");
        CodeOptimizer optimizer = new CodeOptimizer(code);
        List<String> optimizedCode = optimizer.optimize();

        Logger.log("\nCódigo intermedio optimizado:");
        optimizedCode.forEach(Logger::log);
        Files.write(Paths.get("codigoIntermedioOptimizado.txt"), optimizedCode);
        Logger.success("\n[FASE 5] Código optimizado generado en 'codigoIntermedioOptimizado.txt'\n");

        // ================================
        // FASE 6: Salidas del Compilador
        // ================================
        Logger.success("[FASE 6] Archivos generados correctamente. Ejecución finalizada.");
    }

    // Clasifica el token para la columna "Tipo"
    private static String classifyToken(String sym) {
        switch (sym) {
            case "INT": case "CHAR": case "DOUBLE": case "VOID":
            case "IF": case "ELSE": case "FOR": case "WHILE":
            case "BREAK": case "CONTINUE": case "RETURN":
                return "Palabra clave";
            case "ID":
                return "Identificador";
            case "INTLIT": case "DOUBLELIT": case "CHARLIT":
                return "Literal";
            case "PLUS": case "MINUS": case "MUL": case "DIV": case "MOD":
            case "ASSIGN": case "EQ": case "NEQ": case "LT": case "LEQ":
            case "GT": case "GEQ": case "AND": case "OR": case "NOT":
                return "Operador";
            case "LPAREN": case "RPAREN": case "LBRACE":
            case "RBRACE": case "SEMI": case "COMMA":
                return "Símbolo";
            default:
                return "";
        }
    }

    // Exporta el árbol sintáctico a DOT
    public static void exportParseTreeToDot(ParseTree tree, Parser parser, String filename) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write("digraph G {\n  node [shape=box];\n");
        exportNode(tree, parser, writer, 0, new AtomicInteger(1));
        writer.write("}\n");
        writer.close();
    }

    private static int exportNode(ParseTree node, Parser parser,
                                  BufferedWriter writer, int id,
                                  AtomicInteger counter) throws Exception {
        String nodeName = "node" + id;
        String label    = escapeLabel(Trees.getNodeText(node, parser));
        writer.write("  " + nodeName + " [label=\"" + label + "\"];\n");
        for (int i = 0; i < node.getChildCount(); i++) {
            int childId = counter.getAndIncrement();
            writer.write("  " + nodeName + " -> node" + childId + ";\n");
            exportNode(node.getChild(i), parser, writer, childId, counter);
        }
        return id;
    }

    private static String escapeLabel(String text) {
        return text.replace("\"", "\\\"");
    }
}

