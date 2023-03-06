package com.example.kotlin_demoapp.exercise

open class Vehicle(var type: String) {
    open fun display() {
        println("Vehicle")
    }
}

class SomeObject {
    private fun getObject() = object {
        var objectInfo = 10
    }

    fun printObject() {
        println(getObject().objectInfo)
    }
}

object SingleObject {
    var count = 0
    fun increment() {
        count++
    }
}

class CompanionClass {
    companion object {
        var objectProperty = 0
        fun objectFunction() {
            println("Inside Companion Function")
        }
    }
    object Some {
        var someProperty = 10
    }
}

fun main() {
    // Anonymous object
    val name = object {
        var firstName = "Ruchit"
        var lastName = "Kalathiya"
        override fun toString(): String {
            return "$firstName $lastName"
        }
    }
    println(name.toString())
    name.firstName = "Parth"
    println(name.toString())

    // Inherit Other class
    val honda = object : Vehicle("Bike") {
        var tyre: Int = 2
        override fun display() {
            println("Honda")
        }
    }
    honda.display()

    // Anonymous as return type
    val someObject = SomeObject()
    someObject.printObject()

    // Object Declaration
    SingleObject.increment()
    println(SingleObject.count)

    // Companion Object
    var companionClass = CompanionClass()
    CompanionClass.objectFunction()
    CompanionClass.Some.someProperty
}