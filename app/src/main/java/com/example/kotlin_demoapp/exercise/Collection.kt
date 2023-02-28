package com.example.kotlin_demoapp.exercise


fun main() {
    // Immutable
    // List
    val array = listOf(1,3,5,'c')
    println(array)
    // Set
    val set = setOf(1,2,1,4)
    println(set)
    // Map
    val map = mapOf(9 to "C", 7 to "B", 9 to "A", 10 to "B")
    // map[11] = "eleven" //error
    println(map)

    // Mutable
    // List
    val mutableArray = mutableListOf(1,24,5,6)
    mutableArray.add(15)
    mutableArray[1] = 10
    println(mutableArray)
    println(mutableArray::class.simpleName)

    val mutableArrayList = arrayListOf(1,2,3,4)
    mutableArrayList.add(10)
    println(mutableArrayList)
    println(mutableArrayList::class.simpleName)

    // Set
    val mutableSet = mutableSetOf(1,2,1,10,7)
    mutableSet.add(15)
    println(mutableSet)
    println(mutableSet::class.simpleName)

    val linkedSet = linkedSetOf(1,2,1,10,7)
    linkedSet.remove(10)
    linkedSet.add(15)
    println(linkedSet)
    println(linkedSet::class.simpleName)

    val mutableHashSet = hashSetOf(1,2,1,10,7)
    mutableHashSet.remove(10)
    mutableHashSet.add(15)
    println(mutableHashSet)
    println(mutableHashSet::class.simpleName)

    // Map
    val mutableMap = mutableMapOf(9 to "nine", 10 to "ten", 2 to "two")
    mutableMap[11] = "eleven"
    println(mutableMap)
    println(mutableMap::class.simpleName)

    val mutableHashMap = hashMapOf(9 to "nine", 10 to "ten", 2 to "two")
    mutableHashMap[11] = "eleven"
    println(mutableHashMap)
    println(mutableHashMap::class.simpleName)
}