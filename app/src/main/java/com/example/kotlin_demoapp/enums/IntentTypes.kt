package com.example.kotlin_demoapp.enums

enum class IntentTypes(val activity: Class<*>) {
    Implicit(com.example.kotlin_demoapp.activity.intent.ImplicitIntent::class.java),
    Explicit(com.example.kotlin_demoapp.activity.intent.ExplicitIntent::class.java)
}