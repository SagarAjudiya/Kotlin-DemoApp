package com.example.kotlin_demoapp.exercise

enum class DeliveryManager(ID: String?) {
    Preparing(null),
    Dispatch("37G23"),
    Delivered("SA231");
}

enum class Action {
    Wait {
        override fun switchAction() = Ready
    },
    Ready {
        override fun switchAction() = Wait
    };
    abstract fun switchAction(): Action
}

fun main() {
    var dispatch1 = DeliveryManager.Dispatch
    //var dispatch2 = DeliveryManager.Dispatch("H7985") // can not have multiple instance

    // Anonymous classes of enum constants
    var enumAction = Action.Wait
    enumAction= enumAction.switchAction()
    println(enumAction::class.simpleName)

    // all-values
    DeliveryManager.values().forEach { println(it) }
    // check if value is there otherwise throw error
    println(DeliveryManager.valueOf("Dispatch"))
}