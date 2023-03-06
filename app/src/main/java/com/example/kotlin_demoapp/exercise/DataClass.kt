package com.example.kotlin_demoapp.exercise

data class DataClass(var name: String)

fun main() {
    val data = DataClass("Ruchit")
    val data2: DataClass
    data2 = data.copy()
    println(data2.name)
    println(data.toString())
}

