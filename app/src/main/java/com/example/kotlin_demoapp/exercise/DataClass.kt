package com.example.kotlin_demoapp.exercise

data class DataClass(var name: String)

fun main() {
    var data = DataClass("Ruchit")
    var data2: DataClass
    data2 = data.copy()
    println(data2.name)
    println(data.toString())
}

