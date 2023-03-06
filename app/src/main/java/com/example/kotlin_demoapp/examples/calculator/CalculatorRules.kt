package com.example.kotlin_demoapp.examples.calculator

abstract class CalculatorRules: Arithmetic {
    var result: Int = 0
        get() = field.also { println("Result: $field") }
        set(value) {
            field = value
        }
}