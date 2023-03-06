package com.example.kotlin_demoapp.examples.matrix

abstract class  MatrixCalculation {
    fun matrixMultiplication(matrix1: Matrix, matrix2: Matrix): Matrix {
        val answer = Matrix(matrix1.numberRows(), matrix2.numberColumns(), 0)
        for (outerI in 0 until matrix1.numberRows()) {
            for (outerJ in 0 until matrix2.numberColumns()) {
                var temp = 0
                for (innerI in 0 until matrix1.numberColumns()) {
                    temp += matrix1.getElement(outerI, innerI) * matrix2.getElement(innerI, outerJ)
                }
                answer.setElement(outerI, outerJ, temp)
            }
        }
        return answer
    }
}