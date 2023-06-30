package com.example.kotlin_demoapp.tagb.base_classes

sealed class Result
class Success<T>(val model: T) : Result()
class Failure(val message: String) : Result()