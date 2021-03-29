package com.hwanyu365.hello.etc

import com.hwanyu365.hello.Worker

class OperatorOverloading : Worker {

    /**
     * unary
     * - = .unaryMinus()
     * + = .unaryPlus()
     * ! = .not()
     *
     * Increments and decrements
     */
    override fun run() {
        super.run()

        unaryOperators()
        incrementsAndDecrements()
        binaryOperators()
        val point = Point(10, 20)
        println(-point)
    }

    private fun unaryOperators() {
        println("== Unary Operators ==")
        val i = 3
        val b = true
        println("i = ${i.unaryPlus()}")
        println("i = ${i.unaryMinus()}")
        println("b = ${b.not()}")
    }

    private fun incrementsAndDecrements() {
        println("\n== Increments and decrements ==")
        val i = 3
        println("i = ${i.inc()}")
        println("i = ${i.dec()}")
    }

    private fun binaryOperators() {
        println("== Binary Operators ==")
        val i = 3
        val i2 = 5
        println("i + i2 = ${i.plus(i2)}")
        println("i - i2 = ${i.minus(i2)}")
        println("i * i2 = ${i.times(i2)}")
        println("i / i2 = ${i.div(i2)}")
        println("i % i2 = ${i.rem(i2)}")
        println("i..i2 = ${i.rangeTo(i2)}")
    }

    data class Point(val x: Int, val y: Int)

    operator fun Point.unaryMinus() = Point(-x, -y)
}