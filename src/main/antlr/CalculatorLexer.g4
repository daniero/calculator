lexer grammar CalculatorLexer;

WS : [ \t]+ -> skip ;

INT : '0'|[1-9][0-9]* ;

PLUS: '+' ;
