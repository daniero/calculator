package net.daniero.calculator.syntax

interface Expression {
    fun evaluate(): Int
}

data class PlusExpression(private val left: Expression, private val right: Expression) : Expression {
    override fun evaluate() = left.evaluate() + right.evaluate()
}

data class IntConstant(private val value: Int) : Expression {
    override fun evaluate() = value
}