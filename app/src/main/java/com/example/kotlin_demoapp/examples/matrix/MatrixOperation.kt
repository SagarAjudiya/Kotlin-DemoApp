package com.example.kotlin_demoapp.examples.matrix

interface MatrixOperation {
    fun numberRows(): Int
    fun numberColumns(): Int
    fun setElement(row: Int, column:Int, element: Int)
    fun getElement(row: Int, column:Int): Int
    fun printMatrix()
}