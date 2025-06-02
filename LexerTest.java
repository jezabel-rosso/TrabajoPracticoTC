import org.antlr.v4.runtime.*;

public class LexerTest {
    public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromFileName("programaEjemplo.cpp");
        CPPSubsetLexer lexer = new CPPSubsetLexer(input);

        Token token;
        while ((token = lexer.nextToken()).getType() != Token.EOF) {
            String tokenName = lexer.getVocabulary().getSymbolicName(token.getType());
            System.out.println("TOKEN: " + tokenName + " -> '" + token.getText() + "'");
        }
    }
}
