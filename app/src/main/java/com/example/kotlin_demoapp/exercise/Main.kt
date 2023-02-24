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
    val two: Int
    two = 2

}