package com.example.kotlin_demoapp.exercise

fun main() {
    // String
    var a = 1
    val s1 = "a is $a"
    println(s1.replace('z','b'))

    a = 2
    val s2 = "${s1.replace("is", "was")}, but now is $a"
    println(s2)

    val text = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin("|") // default: |, you can set by giving parameter
    print(text)
}