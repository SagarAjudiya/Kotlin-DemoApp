package com.example.kotlin_demoapp.examples.matrix

//Create a class called 'Matrix' containing constructor that initializes the number of rowsand number of columns of a new Matrix object.
//The Matrix class has the following information:
//1.number of rows of matrix
//2 number of columns of matrix
//3 - elements of matrix in the form of 2D array
//The Matrix class has methods for each of the following:
//1 - get the number of rows
//2 - get the number of columns
//3 - set the elements of the matrix at given position (i,j)
//4 - adding two matrices. If the matrices are not addable, "Matrices cannot be added"will be displayed.
//5 - multiplying the two matrices

class Matrix(val row: Int, val column: Int, element: Int): MatrixOperation {
    var matrix = List<MutableList<Int>>(row) {
        MutableList<Int>(column) { element }
    }

    override fun numberRows(): Int = this.row

    override fun numberColumns(): Int = this.column

    override fun setElement(row: Int, column:Int, element: Int) {
        matrix[row][column] = element
    }

    override fun getElement(row: Int, column: Int): Int {
        return matrix[row][column]
    }

    override fun printMatrix() {
        for (i in 0 until row ){
            for (j in 0 until column) {
                print("${getElement(i, j)} ")
            }
            println()
        }
    }
}