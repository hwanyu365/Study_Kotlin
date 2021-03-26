package com.hwanyu365.hello.classes

import com.hwanyu365.hello.Worker

class Extensions: Worker {
    override fun run() {
        super.run()
        extFunctions()
        extProperties()
    }

    private fun extProperties() {
        println("\n== Extension properties ==")

        val x = 10
        println("x($x) vs  x.minusIndex(${x.minusIndex})")
    }

    private fun extFunctions() {
        println("\n== Extension functions ==")
        val list = mutableListOf(1, 2, 3)
        list.swap(1, 2)
        println(list)

        printClassName(Rectangle())
        println("sum? ${list.sumAll()}")

    }

    open class Shape

    class Rectangle: Shape()

    fun Shape.getName() = "Shape"

    fun Rectangle.getName() = "Rectangle"

    fun printClassName(s: Shape) {
        println(s.getName())
    }
}

// '<T> MutableList<T>' is receiver type
fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}

// Nullable receiver extension function
fun MutableList<Int>?.sumAll():Int {
    return if (this === null) {
        0
    } else {
        var sum = 0
        for (item in this) {
            sum += item
        }
        sum
    }
}

// extension properties
val Int.minusIndex: Int get() = this - 1

// Platform declaration clash: The following declarations have the same JVM signature
//    fun swap(target:MutableList<Int>, index1: Int, index2: Int) {
//        val tmp = target[index1] // 'this' corresponds to the list
//        target[index1] = target[index2]
//        target[index2] = tmp
//    }

