parser grammar CPPSubsetParser;

options {
  tokenVocab = CPPSubsetLexer; // Usa los tokens definidos en el lexer
}

// Reglas sintácticas (comienzan en minúscula)

program        : (functionDecl | variableDecl)* EOF;

functionDecl   : type ID LPAREN paramList? RPAREN block;

paramList      : param (COMMA param)*;
param          : type ID;

variableDecl   : type ID (ASSIGN expr)? SEMI;

type           : INT | DOUBLE | CHAR | VOID;

block          : LBRACE statement* RBRACE;

statement
    : variableDecl
    | assignment
    | ifStatement
    | loopStatement
    | returnStatement
    | block
    ;

assignment     : ID ASSIGN expr SEMI;

ifStatement    : IF LPAREN expr RPAREN block (ELSE block)?;

loopStatement  : forLoop | whileLoop;

forLoop        : FOR LPAREN assignment expr SEMI assignment RPAREN block;
whileLoop      : WHILE LPAREN expr RPAREN block;

returnStatement: RETURN expr? SEMI;

expr
    : left=expr op=('*'|'/') right=expr
    | left=expr op=('+'|'-') right=expr
    | left=expr op=('<' | '<=' | '>' | '>=' | '==' | '!=') right=expr
    | left=expr op='&&' right=expr
    | left=expr op='||' right=expr
    | LPAREN expr RPAREN
    | ID
    | INTLIT
    | DOUBLELIT
    | CHARLIT
    ;

