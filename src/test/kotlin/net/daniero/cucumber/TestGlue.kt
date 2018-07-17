package net.daniero.cucumber

import cucumber.api.java8.En
import net.daniero.calculator.Calculator
import org.junit.Assert.assertEquals
import kotlin.properties.Delegates

class TestGlue : En {
    private lateinit var calculator: Calculator
    private lateinit var input: String
    private var expectedResult: Int by Delegates.notNull()

    init {
        Given("^a new calculator$") {
            calculator = Calculator()
        }

        When("""^entering "([^"]*)"$""") { input: String ->
            this.input = input
        }

        Then("""^the result is (\d+)$""") { expected: Int ->
            this.expectedResult = expected

            val result = calculator.evaluate(input)

            assertEquals(expectedResult, result)
        }
    }
}