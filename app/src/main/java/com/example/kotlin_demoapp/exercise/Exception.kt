package com.example.kotlin_demoapp.exercise

class UserdefinedException(message: String): Exception(message)

fun someFunction(number: Int) {
    if (number == 0)
        throw UserdefinedException("Number is 0")
}

fun main() {
    try {
        println(15 / 2)
        someFunction(0)
    } catch (e: UserdefinedException) {
        println("UserDefinedException")
    } catch (e: Exception){
        println("Exception")
    } finally {
        println("Run this")
    }

    val divide = try { 15/0 } catch (e: Exception) { null }
    println(divide)

    // Nothing
    fun fail(message: String) {
        throw UserdefinedException(message)
    }
    var nullName: String? = null
    val s = nullName ?: fail("Name is NULL")

}