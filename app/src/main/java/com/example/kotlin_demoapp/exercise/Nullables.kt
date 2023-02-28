package com.example.kotlin_demoapp.exercise

fun main() {
    var nullableInt: Int? = null
    if (nullableInt == null) {
        println("null")
    }
    // safe type casting
    var name = "Ruchit"
    var nameInt= name as? Int
    if (nameInt is Int)
        println("YES")
    else if (nameInt is Int?)
        println("YES?")
    else
        println("NO")

    // Elvis Operator
    var string: String? = null
    println(string?.length ?: 0)
}