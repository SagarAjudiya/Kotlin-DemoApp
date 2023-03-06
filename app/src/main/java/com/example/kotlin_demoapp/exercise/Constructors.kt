package com.example.kotlin_demoapp.exercise

class Name constructor(val firstName: String) {
    var lastName: String
    constructor(firstName: String, lastName: String) : this(firstName) {
        this.lastName = lastName
        println("Secondary")
    }
    init {
        lastName = "default"
        println("Init Block")
    }
}

fun main() {
    val ruchit = Name("Ruchit")
    println(ruchit.lastName)
}