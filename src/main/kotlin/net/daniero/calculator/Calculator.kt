package net.daniero.calculator

import net.daniero.calculator.parser.CalculatorLexer
import net.daniero.calculator.parser.CalculatorParser
import net.daniero.calculator.parser.CalculatorParserBaseVisitor
import net.daniero.calculator.syntax.Addition
import net.daniero.calculator.syntax.Expression
import net.daniero.calculator.syntax.IntConstant
import net.daniero.calculator.syntax.Subtraction
import org.antlr.v4.runtime.BufferedTokenStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.Token

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
        val value = ctx.text
        return IntConstant(value.toInt())
    }

    override fun visitBinaryExpression(ctx: CalculatorParser.BinaryExpressionContext): Expression {
        val left = visit(ctx.left)
        val right = visit(ctx.right)

        val operator = findBinaryOperator(ctx.op)

        return operator(left, right)
    }

    private fun findBinaryOperator(token: Token): (Expression, Expression) -> Expression {
        return when (token.type) {
            CalculatorLexer.PLUS -> ::Addition
            CalculatorLexer.MINUS -> ::Subtraction
            else -> throw IllegalArgumentException("Unknown binary operator")
        }
    }
}
