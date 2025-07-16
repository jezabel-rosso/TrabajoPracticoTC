lexer grammar CPPSubsetLexer;

@lexer::package { ar.edu.ubp.compilador }

// Palabras clave
INT      : 'int';
CHAR     : 'char';
DOUBLE   : 'double';
VOID     : 'void';
IF       : 'if';
ELSE     : 'else';
FOR      : 'for';
WHILE    : 'while';
BREAK    : 'break';
CONTINUE : 'continue';
RETURN   : 'return';

// Operadores
PLUS     : '+';
MINUS    : '-';
MUL      : '*';
DIV      : '/';
MOD      : '%';
ASSIGN   : '=';
EQ       : '==';
NEQ      : '!=';
LT       : '<';
LEQ      : '<=';
GT       : '>';
GEQ      : '>=';
AND      : '&&';
OR       : '||';
NOT      : '!';

// Delimitadores
LPAREN   : '(';
RPAREN   : ')';
LBRACE   : '{';
RBRACE   : '}';
SEMI     : ';';
COMMA    : ',';

// Identificadores y literales
ID        : [a-zA-Z_][a-zA-Z_0-9]*;
INTLIT    : [0-9]+;
DOUBLELIT : [0-9]+ '.' [0-9]*;
CHARLIT   : '\'' . '\'';

// Espacios y comentarios
WS             : [ \t\r\n]+ -> skip;
LINE_COMMENT   : '//' ~[\r\n]* -> skip;
BLOCK_COMMENT  : '/*' .*? '*/' -> skip;

// Detección de errores
ERROR_CHAR : . {System.err.println("Carácter inválido: " + getText());};
