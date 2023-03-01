package com.example.kotlin_demoapp.exercise

sealed interface Status {
    fun status()
}

sealed class DeliveryStatus(var id: String = " ") : Status

object Preparing : DeliveryStatus() {
    override fun status() {
        println("Preparing")
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
    var dispatch1 = Dispatch("ID1")
    var dispatch2 = Dispatch("ID2") // can have multiple instances
    var preparing1 = Preparing
    // var preparing2 = Preparing() // can not create multiple instance of singleton class
    dispatch1.id = "10"
    println(dispatch1.id)

    displayStatus(dispatch1)
}