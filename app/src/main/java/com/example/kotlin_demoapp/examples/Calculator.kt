package com.example.kotlin_demoapp.examples

class Calculator : CalculatorRules() {
    override fun addition(value1: Int, value2: Int) {
        result = value1 + value2
    }

    override fun substraction(value1: Int, value2: Int) {
        result = value1 - value2
    }

    override fun multiplication(value1: Int, value2: Int) {
        result = value1 * value2
    }
}

fun main() {
    var calculator = Calculator()
    calculator.addition(5,10)
    calculator.result
    calculator.multiplication(5,10)
    calculator.result
}