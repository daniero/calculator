package net.daniero.calculator

import net.daniero.calculator.parser.CalculatorLexer
import net.daniero.calculator.parser.CalculatorParser
import net.daniero.calculator.parser.CalculatorParserBaseVisitor
import net.daniero.calculator.syntax.Addition
import net.daniero.calculator.syntax.Expression
import net.daniero.calculator.syntax.IntConstant
import org.antlr.v4.runtime.BufferedTokenStream
import org.antlr.v4.runtime.CharStreams

class Calculator {

    fun evaluate(input: String): Int {
        val lexer = CalculatorLexer(CharStreams.fromString(input))
        val parser = CalculatorParser(BufferedTokenStream(lexer))

        val expressionVisitor = ExpressionVisitor()
        val expression = expressionVisitor.visit(parser.calculation())

        return expression.evaluate()
    }
}

private class ExpressionVisitor : CalculatorParserBaseVisitor<Expression>() {

    override fun visitEmptyCalculation(ctx: CalculatorParser.EmptyCalculationContext): Expression {
        return IntConstant(0)
    }

    override fun visitIntLiteral(ctx: CalculatorParser.IntLiteralContext): Expression {
        return IntConstant(ctx.text.toInt())
    }

    override fun visitPlusExpression(ctx: CalculatorParser.PlusExpressionContext): Expression {
        val left = visit(ctx.left)
        val right = visit(ctx.right)

        return Addition(left, right)
    }
}
