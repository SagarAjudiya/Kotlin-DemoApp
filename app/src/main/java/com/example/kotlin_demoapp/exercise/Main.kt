package com.example.kotlin_demoapp.exercise

fun main() {

    print("Print without newline")
    println("Print with newline")

    // Unit
    var unit: Unit = Unit
    println("Hello " + unit)
    // Unit as return type
    fun hello() = println("Return type is Unit")
    hello()

    // Variables
    val one = 1
    val two: Int = 2

    // Variable Types
    val item = 1_000_000_000
    fun giveTypes(item: Any): String =
        when (item) {
            is Int -> "Int"
            is Long -> "Long"
            is Float -> "Float"
            is Double -> "Double"
            is Char -> "Character"
            is String -> "String"
            is Boolean -> "Boolean"
            else -> "Unknown"
        }
    println("Type of $item is ${giveTypes(item)}")
}