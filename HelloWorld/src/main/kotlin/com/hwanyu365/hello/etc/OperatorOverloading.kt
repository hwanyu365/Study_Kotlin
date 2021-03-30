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
        comparisonOperators()
        etcOperators()
    }

    private fun etcOperators() {
        println("\n== In Operator ==")
        val list = mutableListOf(1, 2, 3)
        println("1 in list = ${list.contains(1)} = ${1 in list}")

        println("\n== Indexed access operator ==")
        println("get item = ${list[0]} = ${list.get(0)}")
        print("before: " + list.joinToString(prefix = "[", postfix = "]") { "$it" })
        list[0] = 8
        list.set(1, 6)
        println(", after: " + list.joinToString(prefix = "[", postfix = "]") { "$it" })

        println("\n== Equality and inequality operators ==")
        var a: Int? = 3
        var b: Int? = 5
        println("$a == $b = ${a?.equals(b) ?: (b === null)}")
        println("$a != $b = ${!(a?.equals(b) ?: (b === null))}")

        println("\n== Advanced ==")
        val point = Point(10, 20)
        println(-point)

        // else
        // provideDelegate, getValue and setValue operator
    }

    private fun comparisonOperators() {
        println("\n== Comparison operators ==")
        val a = 3
        val b = 5
        println("$a > $b = ${a.compareTo(b) > 0}")
        println("$a < $b = ${a.compareTo(b) < 0}")
        println("$a >= $b = ${a.compareTo(b) >= 0}")
        println("$a <= $b = ${a.compareTo(b) <= 0}")
    }

    private fun binaryOperators() {
        println("\n== Binary Operators ==")
        val i = 3
        val i2 = 5
        println("i + i2 = ${i.plus(i2)}")
        println("i - i2 = ${i.minus(i2)}")
        println("i * i2 = ${i.times(i2)}")
        println("i / i2 = ${i.div(i2)}")
        println("i % i2 = ${i.rem(i2)}")
        println("i..i2 = ${i.rangeTo(i2)}")
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



    data class Point(val x: Int, val y: Int)

    operator fun Point.unaryMinus() = Point(-x, -y)
}