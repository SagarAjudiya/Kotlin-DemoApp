package com.example.kotlin_demoapp.exercise

open class Shape {
    open var area: Int = 0
}

class Rectangle(var width: Int, var length: Int): Shape(){
    override var area: Int = super.area
        get() = width * length
    var parimeter = (width + length) * 2

}

open class Person {
    lateinit var name: String
    var first = "Ruchit"
    var second = "Kalathiya"

    fun display() {
        if (this::name.isInitialized)
            println(name)
        else {
            println("Hello")
        }
    }
}


fun main() {
    // Rectangle Class
    val rectangle = Rectangle(10, 20)
    println("Perimeter: " + rectangle.parimeter)
    println("Area: " + rectangle.area)
    // Person Class
    var ruchit = Person()
    ruchit.display()
}