parser grammar CPPSubsetParser;

@parser::package { ar.edu.ubp.compilador }

options {
  tokenVocab = CPPSubsetLexer;
}

// Reglas sintácticas

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

forLoop        : FOR LPAREN (variableDecl | assignment) expr SEMI assignment RPAREN block;
whileLoop      : WHILE LPAREN expr RPAREN block;

returnStatement: RETURN expr? SEMI;

// Expr con etiquetas

expr
    : left=expr op=('*'|'/') right=expr                                # mulDivExpr
    | left=expr op=('+'|'-') right=expr                                # addSubExpr
    | left=expr op=('<' | '<=' | '>' | '>=' | '==' | '!=') right=expr  # compExpr
    | left=expr op='&&' right=expr                                     # andExpr
    | left=expr op='||' right=expr                                     # orExpr
    | ID LPAREN (expr (COMMA expr)*)? RPAREN                           # functionCallExpr
    | LPAREN expr RPAREN                                               # parenExpr
    | ID                                                               # idExpr
    | INTLIT                                                           # intLiteralExpr
    | DOUBLELIT                                                        # doubleLiteralExpr
    | CHARLIT                                                          # charLiteralExpr
    ;
