package com.example.kotlin_demoapp.exercise

fun main() {
    // For
    var arrayNumber = listOf(1, 2, 3, 4, 5)
    for (number in arrayNumber) {
        println(number)
    }

    // While
    var index = 0
    while (index < arrayNumber.count()) {
        println(arrayNumber[index])
        index++
    }

    // Do While
    println("Index $index")
    do{
        print("$index ")
        index--
    } while (index > 0)
    println()

    // When Expression
    var item = 5.0
    fun giveType(item: Any): String =
    when (item) {
        is Int -> "Int"
        is Long -> "Long"
        is String -> "String"
        is Double -> "Double"
        else -> "Unknown"
    }
    println("Type of $item is ${giveType(item)}")

    // Ranges
    for (index in 1..5) {
        print("$index ")
    }
    println()
    for (index in 1..10 step 2) {
        print("$index ")
    }
    println()
    for (index in 5 downTo 1) {
        print("$index ")
    }
    println()
    for (index in 5 downTo 1 step 2) {
        print("$index ")
    }
    println()
    for (index in 1 until 10 step 3) {
        print("$index ")
    }

    // check if variable is in Range
    println(if (5 in 1..10) "Yes" else "No")

    // Nested loop jumps with labels
    loop@ for(i in 1..10){
        for(j in 1..10) {
            print(j)
            if (j == 5) break@loop
        }
    }
}