package net.daniero.calculator.syntax

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert.*

object ExpressionTest : Spek({
    describe("evaluate()") {
        given("an int constant") {
            val intConstant: Expression = IntConstant(64)

            it("returns the int value") {
                assertEquals(64, intConstant.evaluate())
            }
        }

        given("a nested expression of addition and subtraction") {
            val expression =
                    Addition(
                            Subtraction(
                                    IntConstant(8),
                                    IntConstant(1)),
                            IntConstant(4))

            it("evaluates it") {
                assertEquals(11, expression.evaluate())
            }
        }
    }
})
