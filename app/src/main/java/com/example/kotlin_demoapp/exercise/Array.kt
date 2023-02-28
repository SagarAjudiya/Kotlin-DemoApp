package com.example.kotlin_demoapp.exercise

fun main() {
    // Array
    var arrayNumber = arrayOf(1,2,3,4,5)
    println(arrayNumber[4])
    // Array of nulls
    var a = arrayOfNulls<Int?>(5)
    println(a.size)
    println(a[2])

    var square = Array(5) { index -> index * index }
    square.forEach {
        println(it)
    }

    var string = CharArray(5) {'1'}
    string.forEachIndexed { index, item ->
        println("Index $index  |  item $item")
        string[index] = '2'
    }
    string.forEach {
        println(it)
    }
    // .indices and .withIndex
    var numbers = arrayOf("one","two", "three")
    for (index in numbers.indices) {
        println(index)
    }
    for ((index,item) in numbers.withIndex()) {
        println("$index = $item")
    }

    // Type checks
    if (string is CharArray) {
        println("CharArray")
    }
}