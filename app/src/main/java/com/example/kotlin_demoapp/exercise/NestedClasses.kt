package com.example.kotlin_demoapp.exercise

// Inner Classes
class Outer {
    var numberOuter: Int = 0
    inner class Inner {
        var numberOuter: Int = 1
        fun innerFunction() {
            println("Outer: ${this@Outer.numberOuter} Inner: ${this@Inner.numberOuter}")
        }
    }
}

fun main() {
    var outer = Outer()
    outer.Inner().innerFunction()
}