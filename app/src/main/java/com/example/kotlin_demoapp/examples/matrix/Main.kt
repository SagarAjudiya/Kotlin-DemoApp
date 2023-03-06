package com.example.kotlin_demoapp.examples.matrix

import com.example.kotlin_demoapp.examples.matrix.Matrix
import com.example.kotlin_demoapp.examples.matrix.MatrixCalculation

class Main: MatrixCalculation(){
    val mat1 = Matrix(3,3,1)
    val mat2 = Matrix(3,1,2)
    val ans = matrixMultiplication(mat1,mat2)
}

fun main() {
    var mainObj = Main()
    mainObj.ans.printMatrix()
}