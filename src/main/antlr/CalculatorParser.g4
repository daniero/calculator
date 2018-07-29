parser grammar CalculatorParser;

options { tokenVocab=CalculatorLexer; }

calculation : expression  #expressionCalculation
            | EOF         #emptyCalculation
            ;

term : INT #intLiteral ;

expression : left=expression op=(PLUS|MINUS) right=term #binaryExpression
           | term                                       #termExpression
           ;