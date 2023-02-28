package com.example.kotlin_demoapp.exercise

open class BaseClass
class ChildClass: BaseClass()

fun ChildClass.display() = println("Child")
fun BaseClass.display() = println("Base")

fun printDisplay(obj: ChildClass) {
    obj.display()
}

// does not override class method but overload
class Example {
    var demo: String = ""
        get() { return field }
        set(value) {
            field = value
        }
    fun exampleFunction() {
        println("Inside Class")
    }
}
fun Example.exampleFunction() = println("Extension")
fun Example.exampleFunction(number: Int) = println("Extension with number $number")
val Example.demo: String
    get() = demo + "add"

var Example.name: String
    get() = "field"
    set(value) {
        value
    }

fun main() {
    val someObject: ChildClass = ChildClass()
    printDisplay(someObject)

    val example = Example()
    example.demo
    example.exampleFunction()
    example.exampleFunction(10)
    example.name = "10"
    println(example.name)

    val nullNumber: Int? = null
    println(nullNumber.toString())

}