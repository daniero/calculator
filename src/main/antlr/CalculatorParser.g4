parser grammar CalculatorParser;

options { tokenVocab=CalculatorLexer; }

calculation : expression  #expressionCalculation
            | EOF         #emptyCalculation
            ;

term : INT #intLiteral ;

expression : left=expression PLUS right=term #plusExpression
           | term                            #termExpression
           ;