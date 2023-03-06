package com.example.kotlin_demoapp.exercise

interface Base {
    fun someFunction()
}

class BaseImplement: Base {
    override fun someFunction() {
        println("Base Implementation")
    }
}

class Derived(base: Base): Base by base

fun main() {
    val baseImplement = BaseImplement()
    val derived = Derived(baseImplement)
    derived.someFunction()

    val name: String by lazy {
        "Hello"
    }
}