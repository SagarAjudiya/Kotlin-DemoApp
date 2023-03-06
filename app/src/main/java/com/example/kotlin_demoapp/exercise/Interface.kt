package com.example.kotlin_demoapp.exercise

// Functional Interface / SAM Interface
fun interface NumberChecker {
    fun check(number: Int): Boolean
}
private val checkNumber = NumberChecker { it % 2 == 0 }

fun a() {

}

fun main() {
    println(checkNumber.check(10))
    a()
}