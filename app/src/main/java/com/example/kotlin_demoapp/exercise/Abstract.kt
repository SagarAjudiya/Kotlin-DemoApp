abstract class Shape {
    fun draw() {
        println("Abstract")
    }

    abstract fun display()
}
class Rectangle: Shape() {
    override fun display() {
        println("Inherited Class")
    }
}
interface SomeInterface {
    var a: Int

    fun display() {
        println("Interface Method")
    }
}
class SomeClass: SomeInterface {
    override var a: Int = 10
        get() = field
        set(value) {
            field = value
        }
}

fun main() {
    var rectangle = Rectangle()
    rectangle.display()

    val someClass = SomeClass()
    println(someClass.a)
}