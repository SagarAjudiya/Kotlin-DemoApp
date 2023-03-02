package com.example.kotlin_demoapp.exercise

import android.icu.number.IntegerWidth

fun main() {
    // let
    val a = mutableListOf(1,2,3,4)
    println(a.let { it + 6 }.let { it + 20 })
    println(a)

    // run
    val b = 10
    println(b.run {
        this + 2
    })
    println(b)

    // also
    println(b.also { println("PRINT1") }.also { println(it + 10) })

    // apply - works with object reference
    println(b.apply { this + 2 })
    println(b)

    // with
    println(with(b) {
        this + 2
    })
}