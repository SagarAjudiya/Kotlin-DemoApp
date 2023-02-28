package com.example.kotlin_demoapp.exercise

fun main() {
    // Basic Operators
    println(5 / 2)
    println(5 / 2.0)
    println(5.0 % 2.3)
    println(5.toDouble())
    println(257.toByte())

    // Logical Operator
    println(5 < 0.2)
    //println(5 == 5.9) // can not equal Int and Double
    println(5 <= 9)
    println("AAA - " + 5.equals(5))


    // Bitwise Operator
    println(1 shl 2) // shift left
    println(2 shr 2) // shift right
    println(3 and 6) // and
    println(3 or 6) // or
    println(1 xor 0) // xor
}
