package com.example.kotlin_demoapp.exercise

sealed interface Status {
    fun status()
}

sealed class DeliveryStatus(var id: String = " ") : Status {
    fun method() {
        println("HEllo")
    }

}

object Preparing : DeliveryStatus() {
    override fun status() {
        println("Preparing")
        method()
    }
}
class Dispatch(ID: String) : DeliveryStatus() {
    override fun status() {
        println("Dispatch")
    }
}
class Delivered(ID: String, feedback: String) : DeliveryStatus() {
    override fun status() {
        println("Delivered")
    }
}

fun displayStatus(status: DeliveryStatus) {
    when(status) {
        is Preparing -> status.status()
        is Dispatch -> status.status()
        is Delivered -> status.status()
    }
}

fun main() {
    val dispatch1 = Dispatch("ID1")
    var dispatch2 = Dispatch("ID2") // can have multiple instances
    var preparing1 = Preparing
    // var preparing2 = Preparing() // can not create multiple instance of singleton class
    dispatch1.id = "10"
    println(dispatch1.id)

    displayStatus(dispatch1)
}