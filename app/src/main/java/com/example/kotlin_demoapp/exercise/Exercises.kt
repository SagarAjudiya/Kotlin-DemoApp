package com.example.kotlin_demoapp.exercise

import kotlin.math.abs

fun levelCost(array: IntArray, maxJump: Int): Int {
    var newArary = ArrayList<Int>()
    var cost = 0
    for(index in 1 until array.count()) {
        if (array[index] != array[index - 1]) {
            newArary.add(array[index - 1])
        } else {
            cost++
        }
        if (index == array.count() - 1) {
            newArary.add(array[index])
        }
    }
    for (index in 1 until newArary.count()) {
        if (abs(newArary[index] - newArary[index - 1]) > maxJump) return -1
        cost += 2 * abs(newArary[index] - newArary[index - 1])
    }
    return cost
}

fun main() {
    var array: IntArray = intArrayOf(1, 1, 2, 2, 5, 2, 1, 1)
    println(levelCost(array, 3))
}